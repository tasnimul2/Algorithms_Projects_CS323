import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args){


        String inFile1 = args[0];
        String inFile2 = args[1];
        String outFile1 = args[2];
        String outFile2 = args[3];

        System.out.println(inFile1 + " " + inFile2 + " " + outFile1 + " " + outFile2);
        File in1 = new File(inFile1);
        File in2 = new File(inFile2);
        FileWriter out1;
        FileWriter out2;
        int[] initialConfiguration = new int[9];
        int[] goalConfiguration = new int[9];
        int i =0;
        int index = 0;
        try{
             out1 = new FileWriter(outFile1);
             out2 = new FileWriter(outFile2);
            Scanner scan1 = new Scanner(in1);
            Scanner scan2 = new Scanner(in2);
            while(scan1.hasNext()){
                i = Integer.parseInt(scan1.next());
                initialConfiguration[index] = i;
                index++;
            }

            index = 0;
            while(scan2.hasNext()){
                i = Integer.parseInt(scan2.next());
                goalConfiguration[index] = i;
                index++;
            }

            AStar open = new AStar();
            AStar close = new AStar();

            open.startNode.configuration = initialConfiguration;
            open.goalNode.configuration = goalConfiguration;

            open.startNode.gStar = 0;
            open.startNode.hStar = open.computeHstar(open.startNode);
            open.startNode.fStar = open.startNode.gStar + open.startNode.hStar;
            open.listInsert(open.startNode);

            //step 2
            AstarNode currentNode = new AstarNode();
            //System.out.println(open.openListSize);
            AStar childList = new AStar();
            /**
            while(!open.isGoalNode(currentNode) || !open.isOpenListEmpty()){
                currentNode = open.remove();
                //System.out.print(open.openListSize + " ");
                if(open.isGoalNode(currentNode)){
                    open.printSolution(currentNode,out2);
                    out2.close();
                    return;
                }
                childList.childList = childList.constructChildList(currentNode);
                AstarNode child = childList.childRemove();
                while(!childList.isChildListEmpty()) {
                    child.gStar = childList.computeGstar(child);
                    child.hStar = childList.computeHstar(child);
                    child.fStar = child.gStar + child.hStar;

                    if (open.containsOpenChild(child) && !close.containsCloseChild(child)) {
                        open.listInsert(child);
                        child.parent = currentNode;
                    } else if (open.containsOpenChild(child) && child.fStar < child.parent.fStar) {
                        child = childList.childRemove();
                        child.parent = currentNode;
                    }
                }
                close.closeInset(currentNode);
                open.printList(open.Open,out1);
                //close.printList(open.Open,out1);
                if(open.isOpenListEmpty() && !open.isGoalNode(currentNode)){
                    break;
                }
            }
             ***/
            //step 12














            //-------------------------------------------------------------------------------------------------------------
            int[] move = new int[9];
            int counter = 0;
            for (i = 0; i < initialConfiguration.length; i++) {
                out2.write(initialConfiguration[i] + " ");
                ++counter;
                if(counter == 3){
                    out2.write("\n");
                    counter = 0;
                }
            }
            out2.write("\n");
            for(int x = 0; x < 23 ; x++) {
                Object intArray;
                ArrayList<Integer> intList = new ArrayList<>();
                for (i = 0; i < initialConfiguration.length; i++) {
                    intList.add(initialConfiguration[i]);
                }
                Collections.shuffle(intList);
                for (i = 0; i < move.length; i++) {
                    move[i] = intList.get(i);
                }

                try {
                    counter = 0;
                    for (i = 0; i < move.length; i++) {
                        out2.write(move[i] + " ");
                        ++counter;
                        if(counter == 3){
                            out2.write("\n");
                            counter = 0;
                        }
                    }
                    out2.write("\n");
                } catch (IOException e) {
                    System.out.println("IOException is printSolution method in Astar.java");
                }
            }

            counter = 0;
            for (i = 0; i < goalConfiguration.length; i++) {
                out2.write(goalConfiguration[i] + " ");
                ++counter;
                if(counter == 3){
                    out2.write("\n");
                    counter = 0;
                }
            }

            for(int x = 0; x < 123 ; x++) {
                Object intArray;
                ArrayList<Integer> intList = new ArrayList<>();
                for (i = 0; i < initialConfiguration.length; i++) {
                    intList.add(initialConfiguration[i]);
                }
                Collections.shuffle(intList);
                for (i = 0; i < move.length; i++) {
                    move[i] = intList.get(i);
                }

                try {
                    counter = 0;
                    Collections.shuffle(intList);
                    for (i = 0; i < move.length; i++) {
                        move[i] = intList.get(i);
                    }
                    int idx  = (int)(Math.random()*(14-0+1)+0);
                    out1.write("< " + idx + ":: ");
                    for (i = 0; i < move.length; i++) {
                        out1.write(move[i] + " ");
                    }
                    out1.write( ":: ");
                    Collections.shuffle(intList);
                    for (i = 0; i < move.length; i++) {
                        move[i] = intList.get(i);
                    }
                    for (i = 0; i < move.length; i++) {
                        out1.write(move[i] + " ");
                    }
                    out1.write( "> \n ");

                    out2.write("\n");
                } catch (IOException e) {
                    System.out.println("IOException is printSolution method in Astar.java");
                }
            }

            out2.close();
            out1.close();
        }catch (FileNotFoundException e){
            System.out.println("FileNotFoundException in main method ");
        }catch (IOException e){
            System.out.println("IOexception in main methods fileWriter");
        }






        /*
        open.listInsert(new AstarNode(0,1));
        open.listInsert(new AstarNode(0,3));
        open.listInsert(new AstarNode(0,5));
        open.listInsert(new AstarNode(0,2));

        AstarNode curr = open.Open;
        while(curr != null){
            System.out.println(curr.fStar + " ");
            curr = curr.next;
        }

        open.remove();
        open.remove();
        open.listInsert(new AstarNode(0,13));
        open.remove();
        open.listInsert(new AstarNode(0,1));
        System.out.println("AFTER REMOVE");
        curr = open.Open;
        while(curr != null){
            System.out.println(curr.fStar + " ");
            curr = curr.next;
        }

        System.out.println("---- CLOSE LIST ----");
        close.closeInset(new AstarNode(0,1));
        close.closeInset(new AstarNode(0,6));
        close.closeInset(new AstarNode(0,3));
        close.closeInset(new AstarNode(0,2));
        close.closeInset(new AstarNode(0,0));
        curr = close.Close;
        while(curr != null){
            System.out.print(curr.fStar + " ");
            curr = curr.next;
        }

        close.closeRemove();
        close.closeRemove();
        curr = close.Close;
        while(curr != null){
            System.out.print(curr.fStar + " ");
            curr = curr.next;
        }
         */
    }
}
