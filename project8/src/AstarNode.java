import java.io.FileWriter;
import java.io.IOException;

public class AstarNode {
    int[] configuration = new int[9];
    int gStar = 0; // # moves so far from initial state to current state
    int hStar = 0; // the estimated moves from the currentNode to the goal stateNode
    int fStar; // is gStar + hStar
    AstarNode parent = null; //points to its parent node; initially point to null
    AstarNode next = null;

    //constructor
    public AstarNode(){}

    public  AstarNode(int gStar, int hStar){
        this.gStar = gStar;
        this.hStar = hStar;
        this.fStar = gStar + hStar;
    }

    /**
     * print only node's fStar, configuration, and parent's configuration, in one text line.
     * For example: if node's fStar is 9, its configuration is 6 3 4 8 7 0 5 2 1
     * and its parent's configuration is 6 3 4 8 7 1 5 2 0
     * Then, print <9:: 6 3 4 8 7 0 5 2 1 :: 6 3 4 8 7 1 5 2 0>**/
    public void printNode(FileWriter output){
        try{
            output.write("< " + fStar + ":: ");
            for(int i = 0; i < configuration.length;i++){
                output.write(configuration[i] + " ");
            }
            output.write( ":: ");
            for(int i = 0; i < parent.configuration.length;i++){
                output.write(parent.configuration[i] + " ");
            }
            output.write( "> \n ");

        }catch (IOException e){
            System.out.println("IOExeption in AstarNode class PrintNode");
        }

    }
}
