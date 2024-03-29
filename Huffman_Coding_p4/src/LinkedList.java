
import java.io.FileWriter;
import java.io.IOException;

public class LinkedList {
    private TreeNode listHead; // point to a dummy node!
    private TreeNode listTail; //to improve insertion time.
    private int size;

    // create a new linked list where listHead point to a dummy node.

    public TreeNode getListHead() {
        return listHead;
    }

    public void setListHead(TreeNode listHead) {
        this.listHead = listHead;
    }

    public TreeNode getListTail() {
        return listTail;
    }

    public void setListTail(TreeNode listTail) {
        this.listTail = listTail;
    }

    public int getSize() {
        return size;
    }


    public LinkedList(){

        TreeNode dummy = new TreeNode("DUMMY",0,"",null,null,null);
        listHead = dummy;
        listTail = dummy;
        listTail.next = null;
        listHead.next = null;
        this.size = 0;
    }

    // Insert a newNode into the linked list, in ascending order. Re-use your
    // old code from your previous project.
    public void insertNewNode(TreeNode node){
        TreeNode curr = listHead;
        TreeNode temp = listHead;
        if(isEmpty()) {
            listHead.next = node;
            listTail = node;
            this.size++;
        }else{
            while(curr.next != null){
                if(node.getFrequency() < curr.next.getFrequency()){
                    TreeNode tmp2 = curr.next;
                    curr.next = node;
                    node.next = tmp2;
                    this.size++;
                    break;
                }
                curr = curr.next;
            }
            curr = temp;
        }
    }

    // Call printNode for every node on the list from listHead to the end of the list
    public void printList(LinkedList list,FileWriter debugFile){
        TreeNode curr = list.getListHead();
        TreeNode temp = list.getListHead();
        try {
            debugFile.write("***PRINTING LINKED LIST*** \n");
            while (curr != null) {
                curr.printNode(curr, debugFile);
                curr = curr.next;
            }
            curr = temp;
        }catch (IOException e){
            System.out.println("error in printList");
        }
    }

    public boolean isEmpty(){
        if(listHead.next == null){
            return true;
        }
        return false;
    }

}
