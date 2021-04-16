import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args){
        String inFile = args[0];
        String treefle = args[1];
        String debugFile = args[2];
        binTree tree = new binTree();
        int num = tree.readCheck(inFile);
        try {
            FileWriter debug = new FileWriter(debugFile);
            FileWriter treeFile = new FileWriter(treefle);
            debug.write("Input Size: " + num);
            debug.write("\n");
            if(num < 0) {
                debug.write("Integers in the input file is not stored");
                return;
            }

            int[] inAry = new int[num];
            tree.loadAry(inFile,inAry);
            debug.write("ARRAY:");
            debug.write("\n");
            tree.printAry(inAry,debug);
            treeNode root = tree.buildBinTree(inAry,0,num-1);
            treeFile.write("PREORDER: \n");
            tree.preOrder(root,treeFile);
            treeFile.write("\n");

            treeFile.write("INORDER: \n");
            tree.inOrder(root,treeFile);
            treeFile.write("\n");

            treeFile.write("POSTORDER: \n");
            tree.postOrder(root,treeFile);


            treeFile.close();
            debug.close();
        }catch (IOException e){
            System.out.println("error in fileWriter");
        }


    }
}
