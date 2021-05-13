import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AStar {

    AstarNode startNode;
    AstarNode goalNode;

    /** // Open = A sorted linked list with a dummy node.
     // It maintains an ordered list of nodes, w.r.t. the fStar value
     // Built your own linked list, you may not use Java built-in linked list.**/
    AstarNode Open;
    int openListSize = 0;

    /**
     * // a linked list with a dummy node, can be sorted or unsorted.
     * // It maintains a list; of nodes that already been processed **/
    AstarNode Close;

    AstarNode childList; // a linked list Stack for the expend node's children.

    public AStar(){
        Open = new AstarNode(-5000,-4999);
        Close = new AstarNode(-5000,-4999);
        childList = new AstarNode(-5000,-4999);
    }


    // equal to node's parent's gStar + 1 // one move
    public int computeGstar (AstarNode node){
        node.gStar = node.parent.gStar + 1;
        return node.gStar;
    }

    // count the total distance/moves of tiles from n to goalNode.
    public int computeHstar (AstarNode node){
        int manhattanDistance = 0; // sum of vertical distance  + horizontal distance
        for(int i = 0; i < 9; i++){
            if(node.configuration[i] != goalNode.configuration[i]){
                for(int j = 0; i < 9; i++){
                    if(node.configuration[j] == goalNode.configuration[i]){
                        manhattanDistance += ((Math.abs(i-j))/3 + (Math.abs(i-j))%3 );
                    }
                }
            }
        }
        return manhattanDistance;
    }

    // check to see if two configurations are identical.
    // if they are identical, returns true, otherwise returns false.
    public boolean match(int[] configuration1,int[] configuration2){
        int size = Math.min(configuration1.length,configuration2.length);
        for(int i = 0; i < size; i++){
            if(configuration1[i] != configuration2[i]){
                return  false;
            }
        }
        return true;
    }

    // to check if node's configuration is identical to goalNode's configuration.
    // you can call match () method, passing node's configuration with //goalNode's configuration.
    public boolean isGoalNode (AstarNode node){
        if(match(node.configuration,goalNode.configuration)){
            return true;
        }
        return false;
    }

    // insert node into OpenList, in ascending order w.r.t. fStar
    public void listInsert (AstarNode node){
        AstarNode curr = Open;
        AstarNode temp = Open;
        boolean inserted = false;
        if(isOpenListEmpty()){
            curr.next = node;
            openListSize++;
        }else{
            while(curr.next != null){
                if(node.fStar < curr.next.fStar){
                    AstarNode tmp2 = curr.next;
                    curr.next = node;
                    node.next = tmp2;
                    openListSize++;
                    inserted = true;
                    break;
                }
                curr = curr.next;
            }
            if(!inserted){
                curr.next = node;
                inserted = true;
            }
        }
        Open = temp;
    }

    // removes and returns the front node of OpenList after dummy.
    public AstarNode remove (){
        AstarNode deleted = Open.next;
        Open.next = Open.next.next;
        return deleted;
    }


    /**
     * // To avoid cycle; it starts from currentNode, call match () method
     * //to see if currentNode's configuration is identical to its parent's, and recursively call
     * // upward until reaches the startNode. If it matches with one of currentNode's ancestor, //returns
     * true, otherwise return false.**/
    public boolean checkAncestors (AstarNode currentNode){

        if(match(currentNode.configuration, startNode.configuration)){
            return false;
        }else if(match(currentNode.configuration,currentNode.parent.configuration)){
            return true;
        }
        checkAncestors(currentNode.parent);

        return false;
    }

    /**
     * // Constructs a linked list Stack for all children
     * // of currentNode (i.e., all moves from currentNode, but NOT one of the currentNode's ancestors.
     * // When finish, returns the linked list head. This method must call checkAncestors method!**/
    public AstarNode constructChildList (AstarNode currentNode){
        AstarNode curr = currentNode;
        while(curr.next != null){
            AstarNode dummy = curr;
            dummy.next = childList.next;
            childList.next = dummy;
            curr = curr.next;
        }
        return childList.next;
    }

    /**
     * // call printNode () to print each node in OpenList, including dummy //node, one
     * printNode per text line.**/
    public void printList (AstarNode listHead, FileWriter outFile1){
        while(listHead != null){
            listHead.printNode(outFile1);
            listHead = listHead.next;
        }
    }

    //// Print the solution to outFile2, make it pretty to look at.
    public void printSolution (AstarNode currentNode, FileWriter outFile2){

    }

    private boolean isOpenListEmpty(){
        return openListSize == 0;
    }



}
