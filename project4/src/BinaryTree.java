import java.io.FileWriter;
import java.io.IOException;

public class BinaryTree {
    public TreeNode root;

    //- constructor(s)
    public BinaryTree(){
        this.root = null;
    }
     // see algorithm below
    public void preOrderTraversal(TreeNode root, FileWriter debugFile){
        if(root == null){
            return;
        }
        root.printNode(root,debugFile);
        preOrderTraversal(root.left,debugFile);
        preOrderTraversal(root.right,debugFile);
    }

    //on your own
    public void inOrderTraversal(TreeNode root, FileWriter debugFile){
        if(root == null){
            return;
        }
        preOrderTraversal(root.left,debugFile);
        root.printNode(root,debugFile);
        preOrderTraversal(root.right,debugFile);
    }
     // on your own
    public void postOrderTraversal(TreeNode root, FileWriter debugFile){
        if(root == null){
            return;
        }
        preOrderTraversal(root.left,debugFile);
        preOrderTraversal(root.right,debugFile);
        root.printNode(root,debugFile);
    }
     // returns true if both node's left and right are null, return false otherwise.
    public boolean isLeaf(TreeNode node){
        if(node.left == null && node.right == null){
            return  true;
        }
        return false;
    }
}
