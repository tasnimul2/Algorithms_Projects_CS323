
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
    public void constructHuffmanLList(FileWriter debugFile){
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
    }

    //algorithm given
    public void constructHuffmanBinTree(TreeNode head, FileWriter debugFile){
        TreeNode newNode = new TreeNode("",0,"",null,null,null);
        TreeNode temp = head;
        LinkedList list = new LinkedList();;
        while(head.next.next != null){
            newNode.setFrequency(head.next.getFrequency() + head.next.next.getFrequency());
            newNode.setChStr(head.next.getChStr()+ head.next.next.getChStr());
            newNode.left = head.next;
            newNode.right = head.next.next;
            newNode.next = null;
            list.insertNewNode(newNode);
            head.next = head.next.next;
            list.printList(debugFile);
        }
        BinaryTree tree = new BinaryTree(list.getListHead().next);
        head = temp;
    }

    // It will NOT output the codes to an out file,
    // instead the codes will be stored in the charCode array.
    //algorithm given
    public void constructCharCode(TreeNode t, String[] charCode){

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
