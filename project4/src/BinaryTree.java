import java.io.FileWriter;

public class BinaryTree {
    public TreeNode root;

    //- constructor(s)
    public BinaryTree(){

    }
     // see algorithm below
    public void preOrderTraversal(TreeNode root, FileWriter debugFile){
        if(root == null){
            return;
        }

    }

    //on your own
    public void inOrderTraversal(TreeNode root, FileWriter debugFile){

    }
     // on your own
    public void postOrderTraversal(TreeNode root, FileWriter debugFile){

    }
     // returns true if both node's left and right are null, return false otherwise.
    public boolean isLeaf(TreeNode node){

        return false;
    }
}
