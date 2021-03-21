import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args){

        try{
            String nameInFile = args[0];
            File inFile = new File(nameInFile);
            String nameDebugFile = nameInFile.substring(0,nameInFile.length()-4)+"_DeBug.txt";
            FileWriter debugFile = new FileWriter(nameDebugFile);

            LinkedList list = new LinkedList();
            list.insertNewNode(new TreeNode("1",8,"-1",null,null,null));
            list.insertNewNode(new TreeNode("2",3,"-1",null,null,null));
            list.insertNewNode(new TreeNode("3",2,"-1",null,null,null));
            list.insertNewNode(new TreeNode("4",5,"-1",null,null,null));

            list.printList(list,debugFile);

            HuffmanCoding huffman = new HuffmanCoding();
            huffman.computeCharCounts(inFile);
            huffman.printCountAry(debugFile);
            LinkedList head = huffman.constructHuffmanLList(debugFile);
            BinaryTree tree = huffman.constructHuffmanBinTree(head,debugFile);
            huffman.constructCharCode(tree.root,"");
            debugFile.write("\n PRINTING LINKED LIST \n");
            head.printList(head,debugFile);
            debugFile.write("\n PREORDER TREE TRAVERSAL \n");
            tree.preOrderTraversal(tree.root,debugFile);
            debugFile.write("\n POSTORDER TREE TRAVERSAL \n");
            tree.postOrderTraversal(tree.root,debugFile);
            debugFile.write("\n INORDER TREE TRAVERSAL \n");
            tree.inOrderTraversal(tree.root,debugFile);

            huffman.userInterface(debugFile,tree.root);
            debugFile.close();
        }catch (IOException e){
            System.out.println("Exception in main method");
        }


    }
}
