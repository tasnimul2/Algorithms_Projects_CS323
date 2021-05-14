import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        AStar graph = new AStar();
        String inFile1 = args[0];
        String inFile2 = args[1];
        String outFile1 = args[2];
        String outFile2 = args[3];

        System.out.println(inFile1 + " " + inFile2 + " " + outFile1 + " " + outFile2);
        File in1 = new File(inFile1);
        File in2 = new File(inFile2);
        int[] initialConfiguration = new int[9];
        int[] goalConfiguration = new int[9];
        int i =0;
        int index = 0;
        try{
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

            for(int j = 0; j < initialConfiguration.length; j++){
                System.out.print(initialConfiguration[j] + " ");
            }
            System.out.println();
            for(int j = 0; j < goalConfiguration.length; j++){
                System.out.print(goalConfiguration[j] + " ");
            }

        }catch (FileNotFoundException e){

        }

        /*

        graph.listInsert(new AstarNode(0,1));
        graph.listInsert(new AstarNode(0,3));
        graph.listInsert(new AstarNode(0,5));
        graph.listInsert(new AstarNode(0,2));

        AstarNode curr = graph.Open;
        while(curr != null){
            System.out.println(curr.fStar + " ");
            curr = curr.next;
        }

        graph.remove();
        graph.remove();
        graph.listInsert(new AstarNode(0,13));
        graph.remove();
        graph.listInsert(new AstarNode(0,1));
        System.out.println("AFTER REMOVE");
        curr = graph.Open;
        while(curr != null){
            System.out.println(curr.fStar + " ");
            curr = curr.next;
        }
        */

    }
}
