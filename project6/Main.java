    import java.io.File;
    import java.io.FileNotFoundException;
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
        static int procUsed ;// number of processors used so far; initialized to 0.
        static int currentTime ;// initialize to 0.
        static int totalJobTime ;// the total job times of nodes in the graph.

        /*an 1D array to store the job time of each node in the graph;
         to be dynamically allocated, size of numNodes +1; initialied to 0.*/
        static int jobTimeAry[]; 
        static int adjMatrix[][];
        static int Table[][];
        static Node open;

        // take care all member allocations, initialization, etc.
        public Schedule(){

        }

        //// read an edge <ni, nj> from inFile1and load to adjMatrix.
        public static void loadFile(File inFile1){
            
        }
        // read each pair <jobID, time> from inFile2 and load to jobTimeAry;
        // set jobTimeAry[jobID]  time; need to keep track of total job times;
        // returns totalJobTime
        public static int loadJobTimeAry (File inFile2){

            return 0;
        }
        /**
         * // Compute parent counts and store in adjMatrix[0][j],
         * // computes dependent counts and store in adjMatrix[i][0],
         * //set diagonal entries AdjMatrix[i][i] to 1,
         * //set AdjMatrix [0][0] to numNodes.
         * **/
        public static void setMatrix(){

        }
        // Print the entire content of adjMatrix with row and column indices in readable format.
        public static void printMatrix(File outFile2){

        }
        /**
         * // Check AdjMatrix [0][j] to find the next un-marked orphan node, j, i.e., AdjMatrix [0][j] == 0 &&
         * //AdjMatrix [j][j] == 1. If found, mark the orphan, i.e., set AdjMatrix[j][j]  2, then returns j;
         * // if no such j, returns -1.
         * **/
        public static int findOrphan(){

            return 0;
        }
        /**
         * // on your own. Perform a linked list insertion.
         * // insert node into OPEN in the descending order by the # of dependents.**/
        public static void openInsert(Node node){

        }

        /**
         * // print to outFile2, nodes in OPEN linked list
         * // Re-use codes similar to the printList method in your earlier projects.**/
        public static void printOpen(File outFile2){

        }

        /**
         * // check Table [i][currentTime] to find the first i where Table [i][ currentTime] == 0
         * // where i = 0 to numProcs
         * // if found returns i, else returns -1, means no available processor.**/

        public static int getNextProc (int currentTime){

            return 0;
        }

        //algo given
        public static void putJobOnTable (int availProc, int currentTime, int jobID, int jobTime) {

        }
        /**
         * // print the scheduleTable upto the currentTime slot to outFile1,
         * // On your own, see the format description given in the above.**/

        public static void printTable (File outFile1,int currentTime){

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
            return false;
        }

        /**
         * // on your own
         * //if AdjMatrix [0][0] == 0 returns true, else returns false. When you printMatrix, pay attention to
         * // the content of AdjMatrix [0][0].**/
        public static boolean isGraphEmpty (){
            return false;
        }
        /**
         * // see algorithm steps below.
         * // When a job is done, we delete the job and its outgoing edges from the graph,as follows.
         * // 1) To delete a job in the graph is to set adjMatrix [jobID][ jobID] to 0 and decrease the
         * number of nodes in graph by 1, i.e., adjMatrix [0][0] --
         * // 2) To delete a job's outgoing edges is to decrease all its dependents the parent counts by 1.
         * Note: the job's dependents are those none zero adjMatrix [jobID][j] > 0, on jobID row
         * For example, if adjMatrix [jobID][j] > 0, then decrease adjMatrix [0][j] by 1; adjMatrix [0][j]-- **/
        public static void deleteJob(){

        }


        
    }


//------------------------------MAIN METHOD------------------------------------------//
    public static void main(String[] args){
        String inFile1 = args[0];
        System.out.println(inFile1);
    }
    
}