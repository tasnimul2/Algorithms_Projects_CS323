import java.io.FileWriter;

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

        TreeNode dummy = new TreeNode("DUMMY",-1,"DUMMY",null,null,null);
        listHead = dummy;
        listTail = dummy;
        listTail.next = null;
        listHead.next = null;
        this.size = 0;
    }

    // Insert a newNode into the linked list, in ascending order. Re-use your
    // old code from your previous project.
    public void insertNewNode(TreeNode node){
        if(isEmpty()) {
            listHead.next = node;
            listTail = node;
            size++;
        }else{

            listTail.next = node;
            listTail = node;
            size++;
        }
    }

    // Call printNode for every node on the list from listHead to the end of the list
    public void printList(FileWriter debugFile){
        TreeNode curr = listHead;
        TreeNode temp = listHead;

        while(curr != null){
            curr.printNode(curr,debugFile);
            curr = curr.next;
        }
        curr = temp;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }
}
