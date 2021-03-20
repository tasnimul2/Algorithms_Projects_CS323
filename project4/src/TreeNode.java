import sun.reflect.generics.tree.Tree;

import java.io.FileWriter;

public class TreeNode {
    public String chStr;
    public int frequency;
    public String code;
    public TreeNode left;
    public TreeNode right;
    public TreeNode next;

    public TreeNode(String chStr, int frequency, String code, TreeNode left, TreeNode right,TreeNode next){
        this.chStr = chStr;
        this.frequency = frequency;
        this.code = code;
        this.left = left;
        this.right = right;
        this.next = next;
    }

    // format: (T’s chStr, T’s frequency, T’s code, T’s next chStr, T’s left’s chStr, T ‘s right’s chStr);
    //one print per text
    public void printNode(TreeNode T, FileWriter debugFile){

    }
}
