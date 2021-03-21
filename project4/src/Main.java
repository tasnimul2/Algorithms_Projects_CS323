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
            /*
            LinkedList list = new LinkedList();
            list.insertNewNode(new TreeNode("1",8,"-1",null,null,null));
            list.insertNewNode(new TreeNode("2",3,"-1",null,null,null));
            list.insertNewNode(new TreeNode("3",2,"-1",null,null,null));
            list.insertNewNode(new TreeNode("4",5,"-1",null,null,null));

            //list.printList(debugFile);
             */
            HuffmanCoding huffman = new HuffmanCoding();
            huffman.computeCharCounts(inFile);
            huffman.printCountAry(debugFile);
            debugFile.close();
        }catch (IOException e){
            System.out.println("Exception in main method");
        }


    }
}
