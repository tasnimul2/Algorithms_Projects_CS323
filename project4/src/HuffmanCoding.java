
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HuffmanCoding {
    public int[] charCountAry = new int[256];// a 1-D array to store the character counts.
    public String[]charCode= new String[256]; // a 1-D array to store the Huffman code table,

    public HuffmanCoding(){
        for(int i = 0; i<charCountAry.length;i++){
            charCountAry[i] = 0;
        }
    }

    // Read a character from input file, use (int) to get index, asci code of the character;
    // charCountAry[index]++. You should know how to do this method.
    public void computeCharCounts(File inFile){
        try{
            Scanner scan = new Scanner(inFile);
            while (scan.hasNextLine()){
                String textLine = scan.nextLine();
                for(int i = 0; i <textLine.length();i++){
                    int index = (int)textLine.charAt(i);
                    if(index >= 0 && index < 256){
                        charCountAry[index]++;
                    }//end of if
                }//end of for loop
            }//end of while
        }catch (FileNotFoundException e){
            System.out.println("exception in computeCharCounts");
        }
    }

    /*
    // print the character count array to DebugFile, in the following format:
    **** >>> (DO NOT print any characters that have zero count.)
    char1 count
    char2 count
    char3 count
    char4 count
    :
    :
    :
    * */
    public void printCountAry(FileWriter debugFile){
        try {
            debugFile.write("PRINTING CHARACTER COUNT ARRAY **** >>> \n");
            for(int i = 0; i < charCountAry.length;i++){
                if(charCountAry[i] != 0){
                    debugFile.write( "char"+i+" " + charCountAry[i] + "\n");
                }

            }
        }catch(IOException e){
            System.out.println("exception in printCountAry");
        }
    }

    //algorithm given
    public LinkedList constructHuffmanLList(FileWriter debugFile){
        LinkedList list = new LinkedList(); //creates a linked list with a dummy node (“dummy” ,0, , null, null, null)
        int index = 0;
        while(index < 256){
            if(charCountAry[index] > 0){
                char chr = (char) index;
                int prob = charCountAry[index];
                list.insertNewNode(new TreeNode(String.valueOf(chr),prob,"",null,null,null));
                list.printList(debugFile);
            }
            index++;
        }
        return list;
    }

    //algorithm given
    public BinaryTree constructHuffmanBinTree(LinkedList list,FileWriter debugFile){
        BinaryTree tree = new BinaryTree();
        try {
            debugFile.write("\n \n *** CONSTRUCTING HUFFMAN BINARY TREE \n");
            //TreeNode curr = list.getListHead();
            TreeNode newNode ;
            TreeNode tmp = new TreeNode();
            while(list.getListHead().next.next != null){
                newNode =  new TreeNode();
                newNode.chStr= list.getListHead().next.getChStr() + list.getListHead().next.next.chStr;
                newNode.frequency = list.getListHead().next.getFrequency() + list.getListHead().next.next.frequency;
                newNode.left = list.getListHead().next;
                newNode.right = list.getListHead().next.next;
                newNode.next = null;
                list.insertNewNode(newNode);
                tmp = newNode;
                list.getListHead().next = list.getListHead().next.next.next;
                list.printList(debugFile);
            }

            tree.root = tmp;
        }catch (IOException e){
            System.out.println("error in constructHuffmanBinTree");
        }catch (NullPointerException e){
            System.out.println("Null pointer exception in constructHuffmanBinTree");

        }

        return tree;
    }

    // It will NOT output the codes to an out file,
    // instead the codes will be stored in the charCode array.
    //algorithm given
    public void constructCharCode(TreeNode t, String inputCode){
        if(t.left == null && t.right == null){
            t.code = inputCode;
            int index = (int)t.chStr.charAt(0);
            charCode[index] = inputCode;
        }else{
            constructCharCode(t.left,inputCode+"0");
            constructCharCode(t.right,inputCode+"1");
        }

    }

    //algorithm given
    public void encode(File originalFile, FileWriter compressedFile){

    }

    //algorithm given

    public void decode(File compressedFile, FileWriter deCompressedFile){

    }

    //algorithm given
    public void userInterface(){

    }

}
