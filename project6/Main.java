    import java.io.File;
    import java.io.FileNotFoundException;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.util.Scanner;
    public class Main{

    //------------------------------NODE CLASS------------------------------------------//
    public static class Node{
        public int jobId;
        public int jobTime;
        Node next;
        
        Node(int id,int time){
            this.jobId = id;
            this.jobTime = time;
        }

    }
//------------------------------SCHEDULE CLASS------------------------------------------//
    public static class Schedule{
        static int numNodes; // the total number of nodes in the input graph.
        static int numProcs; // the number of processors can be used.
        static int procUsed = 0;// number of processors used so far; initialized to 0.
        static int currentTime = 0;// initialize to 0.
        static int totalJobTime ;// the total job times of nodes in the graph.
        static int openSize = 0;

        /*an 1D array to store the job time of each node in the graph;
         to be dynamically allocated, size of numNodes +1; initialied to 0.*/
        static int jobTimeAry[] ;
        /**
         * - (int) adjMatrix [][] // a 2-D integer array, size numNodes+1 by numNodes+1,
         * // To represent the dependency graph; to be dynamically allocated, initialized to zero.
         * // We use this matrix for a lot of ways as follows:
         * // adjMatrix [0][0] to store number of nodes remain in the graph; initialize to numNodes;
         * // decreases by 1, when a node is deleted; So, to check if the graph is empty,
         * // just check if AdjMatrix[0][0] == 0, in O(1).
         * // adjMatrix [i][j] >= 1, means node i is a parent of node j; and node j is a dependent of node i.
         * // get it from inFile1.
         * // adjMatrix [0][j], we use row 0 to store the parent counts of node j.
         * //The parent counts of node j is the total count of none zero rows in j-th column.
         * // So if we want to know the parent counts of node j, we check AdjMatrix[0][j], in O(1).
         * // adjMatrix [i][0], we use column 0 to store the dependent counts of node i.
         * //The dependent counts of node i is the total count of none zero columns in i-th row.
         * // So if we want to know the dependent counts of node i, we check AdjMatrix [i][0] , in O(1)..
         * // adjMatrix [i][i], the diagonal, [i][i] to indicate the status of the node, where i = 1 to numNodes
         * // AdjMatrix [i][i] == 0 means node i is not in the graph.;
         * //AdjMatrix [i][i] == 1 means node i is in the graph;
         * //AdjMatrix [i][i] == 2 means node i is marked.**/
        static int adjMatrix[][];
        static int table[][];
        static Node open;

        // take care all member allocations, initialization, etc.
        public Schedule(){

        }

        //// read an edge <ni, nj> from inFile1 and load to adjMatrix.
        public static void loadMatrix(File inFile1){
            int i =0;
            int j = 0;
            try{
                Scanner scan = new Scanner(inFile1);
                scan.next();
                while(scan.hasNext()){
                    i = Integer.parseInt(scan.next());
                    j = Integer.parseInt(scan.next());
                    adjMatrix[i][j] = 1; //means i is adjacent to j in the graph
                }
            }catch (FileNotFoundException e){
                System.out.println("file not found for loadFile(). Make sure this file exists in folder");
            }
        }
        // read each pair <jobID, time> from inFile2 and load to jobTimeAry;
        // set jobTimeAry[jobID] = time; need to keep track of total job times;
        // returns totalJobTime
        public static int loadJobTimeAry (File inFile2){
            int jobID;
            int jobTime;
            int totalTime = 0;
            try{
                Scanner scan = new Scanner(inFile2);
                scan.next();
                while(scan.hasNext()){
                    jobID = Integer.parseInt(scan.next());
                    jobTime = Integer.parseInt(scan.next());
                    jobTimeAry[jobID] = jobTime;
                    totalTime+= jobTime;
                }
            }catch (FileNotFoundException e){
                System.out.println("file not found for loadFile(). Make sure this file exists in folder");
            }
            return totalTime;
        }

        /**
         * // Compute parent counts and store in adjMatrix[0][j],
         * // computes dependent counts and store in adjMatrix[i][0],
         * //set diagonal entries AdjMatrix[i][i] to 1,
         * //set AdjMatrix [0][0] to numNodes.
         * **/
        public static void setMatrix(){

            for(int i=0; i < numNodes +1 ; i++){
                for(int j = 0; j < numNodes + 1;j++){
                    if(adjMatrix[i][j] == 1){
                        adjMatrix[0][j]++;
                        adjMatrix[i][0]++;
                    }
                    if(j == i){
                        adjMatrix[i][i] = 1;
                    }
                }//end of inner for
            }//end of outer for
            adjMatrix[0][0] = numNodes;

        }

        // Print the entire content of adjMatrix with row and column indices in readable format.
        public static void printMatrix(FileWriter outFile2){

            try{
                for(int i = 0; i < numNodes+1; i++){
                    for(int j = 0; j < numNodes+1;j++){
                        outFile2.write(adjMatrix[i][j] + " ");
                    }
                    outFile2.write("\n");
                }
                outFile2.write("\n");
            }catch (IOException e){
                System.out.println("IOException in printMatrix");
            }



        }
        /**
         * // Check AdjMatrix [0][j] to find the next un-marked orphan node, j, i.e., AdjMatrix [0][j] == 0 &&
         * //AdjMatrix [j][j] == 1. If found, mark the orphan, i.e., set AdjMatrix[j][j]  2, then returns j;
         * // if no such j, returns -1.
         * **/
        public static int findOrphan(){
            for(int j = 0; j < numNodes+1; j++){
                if(adjMatrix[0][j] == 0 && adjMatrix[j][j] == 1){
                    adjMatrix[j][j] = 2;
                    return j;
                }
            }
            return -1;
        }
        /**
         * // on your own. Perform a linked list insertion.
         * // insert node into OPEN in the descending order by the # of dependents.**/
        public static void openInsert(Node node){
            Node temp = open;
            Node head = open;
            while(temp.next != null && adjMatrix[temp.next.jobId][0] > adjMatrix[node.jobId][0]){
                temp = temp.next;
            }
            node.next = temp.next;
            temp.next = node;
            openSize++;
            open = head;
        }
        //Job = remove the front node of OPEN after dummy node // newJob is a node!
        public static Node openRemove(){
            Node job = open.next;
            open.next = open.next.next;
            openSize--;
            return job;
        }
        public static boolean isOpenEmpty(){
            if(openSize ==0){
                return true;
            }
            return false;
        }

        /**
         * // print to outFile2, nodes in OPEN linked list
         * // Re-use codes similar to the printList method in your earlier projects.**/
        public static void printOpen(FileWriter outFile2){
            Node temp = open;
            Node head = open;
            try{
                while(temp != null){
                    outFile2.write(temp.jobId+"->");
                    temp= temp.next;
                }
                open = head;
                outFile2.write("\n");
            }catch (IOException e){
                e.getStackTrace();
                System.out.println("IOExeption in printOpen()");
            }

        }

        /**
         * // check Table [i][currentTime] to find the first i where Table [i][ currentTime] == 0
         * // where i = 0 to numProcs
         * // if found returns i, else returns -1, means no available processor.**/

        public static int getNextProc (int currentTime){
            //System.out.println("total job time"+totalJobTime);
            //System.out.println("current job time" + currentTime);
            for(int i = 0; i < numProcs+1  ; i++){
                if(table[i][currentTime] == 0){
                    return i;
                }
            }
            return -1;
        }

        //algo given
        public static void putJobOnTable (int availProc, int currentTime, int jobID, int jobTime) {
            int time = currentTime;
            int endTime = time + jobTime;
            while(time < endTime) {
                table[availProc][time] = jobID;
                time++;
            }
            //added this in to free used processors
            /*
            if(table[availProc][time] <= 0){
                procUsed--;
            }

             */
        }
        /**
         * // print the scheduleTable upto the currentTime slot to outFile1,
         * // On your own, see the format description given in the above.**/

        public static void printTable (FileWriter outFile1,int currentTime){
            try{
                outFile1.write("      ");
                for(int x = 0; x < totalJobTime; x++){
                    outFile1.write(" "+ x);
                }
                outFile1.write("\n");
                for(int x = 0; x <= totalJobTime; x++){
                    outFile1.write("--");
                }
                outFile1.write("\n");
                for(int i = 0; i < numProcs;i++){
                    outFile1.write("P("+i+") |");
                    for(int j = 0; j <= currentTime;j++){
                        outFile1.write(table[i][j]+"|");
                    }
                    outFile1.write("\n");
                }
                for(int x = 0; x <= totalJobTime; x++){
                    outFile1.write("--");
                }
                outFile1.write("\n");
            }catch (IOException e){
                System.out.println("IOException in printTable");
            }

        }

        /**
         * // on your own.
         * Check the followings:
         * (1) OPEN is empty.
         * (2) Graph is not empty. // you should know where to check
         * (3) all processors are available. // you should know where to check
         * if all 3 conditions in the above are true,
         * returns true
         * else returns false**/

        public static boolean checkCycle(){
            if(openSize == 0 && adjMatrix[0][0] != 0 && procUsed == 0){
                return true;
            }
            return false;
        }

        /**
         * // on your own
         * //if AdjMatrix [0][0] == 0 returns true, else returns false. When you printMatrix, pay attention to
         * // the content of AdjMatrix [0][0].**/
        public static boolean isGraphEmpty (){
            if(adjMatrix[0][0] == 0){
                return true;
            }
            return false ;
        }
        /**
         * // see algorithm steps below.
         * // When a job is done, we delete the job and its outgoing edges from the graph,as follows.
         * // 1) To delete a job in the graph is to set adjMatrix [jobID][ jobID] to 0 and decrease the
         * number of nodes in graph by 1, i.e., adjMatrix [0][0] --
         * // 2) To delete a job's outgoing edges is to decrease all its dependents the parent counts by 1.
         * Note: the job's dependents are those none zero adjMatrix [jobID][j] > 0, on jobID row
         * For example, if adjMatrix [jobID][j] > 0, then decrease adjMatrix [0][j] by 1; adjMatrix [0][j]-- **/
        public static void deleteJob(int id){
            adjMatrix[id][id] = 0; //delete job from the graph;
            adjMatrix[0][0]--;
            int j = 1;
            while(j < numNodes) {
                if (adjMatrix[id][j] > 0) { //if j is a dependent of job ID
                    adjMatrix[0][j]--; //decrease j's parent count by 1
                    //procUsed--; /** added this in to update used processes **/
                }
                j++;
            }
        }
    }


//------------------------------MAIN METHOD------------------------------------------//
    public static void main(String[] args){
        String input1 = args[0];
        String input2 = args[1];
        String processors = args[2];
        String output1= args[3];
        String output2 = args[4];
        File inFile1 = new File(input1);
        File inFile2 = new File(input2);

        try{
            FileWriter outFile1 = new FileWriter(output1);
            FileWriter outFile2 = new FileWriter(output2);

            Scanner scan = new Scanner(inFile1);
            Schedule.numNodes = Integer.parseInt(scan.next());
            Schedule.jobTimeAry = new int[Schedule.numNodes+1];
            Schedule.adjMatrix = new int[Schedule.numNodes+1][Schedule.numNodes+1];
            Schedule.numProcs = Integer.parseInt(processors);

            if(Schedule.numProcs <=0){
                System.out.println("need 1 or more processors");
                return;
            }else if(Schedule.numProcs > Schedule.numNodes){
                Schedule.numProcs = Schedule.numNodes; //means unlimited processors
            }

            Schedule.open = new Node(-9999,-9999);
            Schedule.currentTime = 0;
            Schedule.procUsed = 0;
            Schedule.loadMatrix(inFile1);
            Schedule.totalJobTime = Schedule.loadJobTimeAry(inFile2);
            Schedule.table = new int[Schedule.numProcs +1][Schedule.totalJobTime +1];
            Schedule.printTable(outFile1,Schedule.currentTime);
            Schedule.setMatrix();
            Schedule.printMatrix(outFile2);

            //while(!Schedule.isGraphEmpty()) {
                int jobId = 0;
                while (jobId != -1) {
                    jobId = Schedule.findOrphan(); //this is Step 3
                    if(jobId>0) {
                        Node newNode = new Node(jobId, Schedule.jobTimeAry[jobId]);
                        Schedule.openInsert(newNode);
                        Schedule.printOpen(outFile2); // debug print
                        //System.out.println("JOB ID: " + jobId);
                        //jobId = Schedule.findOrphan();
                    }
                }

                int availProc = 0;//step 6
                //repeat step 6 while availProc >= 0 && OPEN is not empty && procUsed < numProcs
                System.out.println(Schedule.isOpenEmpty());
                while (availProc >= 0 && !Schedule.isOpenEmpty() && Schedule.procUsed < Schedule.numProcs ) {
                    availProc = Schedule.getNextProc(Schedule.currentTime);
                    if(availProc >= 0) {
                        Node newJob = Schedule.openRemove();
                        Schedule.putJobOnTable(availProc, Schedule.currentTime, newJob.jobId, newJob.jobTime);
                        System.out.println("processors used:" + Schedule.procUsed);
                        if (availProc > Schedule.procUsed) {
                            Schedule.procUsed++;
                        }
                    }
                }

                Schedule.printTable(outFile1, Schedule.currentTime);
                boolean hasCycle = Schedule.checkCycle();
                if (hasCycle) {
                    System.out.println("There is a cycle in the graph!!!");
                }

                Schedule.currentTime++;



                System.out.println("Current Time:" + Schedule.currentTime);

                int proc = 0;
                System.out.println("proc"+ proc);
                System.out.println("proc used" + Schedule.procUsed);
                while (proc < Schedule.procUsed) {
                    System.out.println("enter while");
                    if (Schedule.table[proc][Schedule.currentTime] <= 0 && Schedule.table[proc][Schedule.currentTime - 1] > 0) {
                        // the processor, proc, just finished a job in the // previous time cycle.
                        System.out.println("inner proc"+ proc);
                        System.out.println("inner proc used" + Schedule.procUsed);
                        jobId = Schedule.table[proc][Schedule.currentTime - 1];
                        Schedule.deleteJob(jobId);
                        //Schedule.procUsed--;
                    }
                    Schedule.printMatrix(outFile2);
                    proc++;
                }
           //}

            Schedule.printTable(outFile1,Schedule.currentTime);
            outFile2.close();
            outFile1.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found in Main(). Make sure it is located in this folder and check for spelling errors");
        }catch(IOException e){
            System.out.println("IEexpection in main(). ");
        }






    }
    
}