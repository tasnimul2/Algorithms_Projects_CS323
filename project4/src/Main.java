import java.io.File;
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
            list.insertNewNode(new TreeNode("1",0,"-1",null,null,null));
            list.insertNewNode(new TreeNode("2",0,"-1",null,null,null));
            list.insertNewNode(new TreeNode("3",0,"-1",null,null,null));
            list.insertNewNode(new TreeNode("4",0,"-1",null,null,null));

            list.printList(debugFile);
            debugFile.close();
        }catch (IOException e){
            System.out.println("Exception in main method");
        }


    }
}
