import java.io.File;
import java.io.FileWriter;

public class AStar {

    AstarNode startNode;
    AstarNode goalNode;

    /** // Open = A sorted linked list with a dummy node.
     // It maintains an ordered list of nodes, w.r.t. the fStar value
     // Built your own linked list, you may not use Java built-in linked list.**/
    AstarNode Open;

    /**
     * // a linked list with a dummy node, can be sorted or unsorted.
     * // It maintains a list; of nodes that already been processed **/
    AstarNode Close;

    AstarNode childList; // a linked list Stack for the expend node's children.

    public AStar(){}

    // equal to node's parent's gStar + 1 // one move
    public int computeGstar (AstarNode node){

        return 0;
    }

    // count the total distance/moves of tiles from n to goalNode.
    public int computeHstar (AstarNode node){

        return 0;
    }

    // check to see if two configurations are identical.
    // if they are identical, returns true, otherwise returns false.
    public boolean match(int[] configuration1,int[] configuration2){

        return false;
    }

    // to check if node's configuration is identical to goalNode's configuration.
    // you can call match () method, passing node's configuration with //goalNode's configuration.
    public boolean isGoalNode (AstarNode node){
        return false;
    }

    // insert node into OpenList, in ascending order w.r.t. fStar
    public void listInsert (AstarNode node){

    }

    // removes and returns the front node of OpenList after dummy.
    public AstarNode remove (AstarNode OpenList){

        return new AstarNode();
    }


    /**
     * // To avoid cycle; it starts from currentNode, call match () method
     * //to see if currentNode's configuration is identical to its parent's, and recursively call
     * // upward until reaches the startNode. If it matches with one of currentNode's ancestor, //returns
     * true, otherwise return false.**/
    public boolean checkAncestors (AstarNode currentNode){
        return false;
    }

    /**
     * // Constructs a linked list Stack for all children
     * // of currentNode (i.e., all moves from currentNode, but NOT one of the currentNode's ancestors.
     * // When finish, returns the linked list head. This method must call checkAncestors method!**/
    public AstarNode constructChildList (AstarNode currentNode){

        return  new AstarNode();
    }

    /**
     * // call printNode () to print each node in OpenList, including dummy //node, one
     * printNode per text line.**/
    public void printList (AstarNode listHead, FileWriter outFile1){

    }

    //// Print the solution to outFile2, make it pretty to look at.
    public void printSolution (AstarNode currentNode, FileWriter outFile2){

    }



}
