

import java.io.*;
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
                list.printList(list,debugFile);
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
                list.printList(list,debugFile);
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
    public void encode(File originalFile, FileWriter compressedFile,FileWriter debugFile){
        try{
            Scanner scan = new Scanner(originalFile);
            String code="";
            while (scan.hasNextLine()){
                String textLine = scan.nextLine();
                for(int i =0; i < textLine.length();i++){
                    char charIn = textLine.charAt(i);
                    int index = (int) charIn;
                    if(index >= 0 && index < 256) {
                        code = charCode[index];
                    }
                    if(code!=null){
                        debugFile.write("char:"+ index + " " + code+ "\n");
                        compressedFile.write(code);
                    }

                }
            }
        }catch (FileNotFoundException e){
            System.out.println("file not found to encode");
        }catch (IOException e){
            System.out.println("IoException in encode");
        }
    }

    //algorithm given

    public void decode(File compressedFile, FileWriter deCompressedFile,File orig ,TreeNode root){
        TreeNode spot = root;
        Scanner scan;
        try{
            scan = new Scanner(compressedFile);
            String textLine = scan.nextLine();
                for(int i =0; i < textLine.length();i++) {
                    if(spot.left == null && spot.right == null) {
                        //deCompressedFile.write(spot.chStr);
                        spot = root;
                    }
                    char oneBit =textLine.charAt(i);
                    if(oneBit == '0'){
                        spot = spot.left;
                    }else if(oneBit == '1' ){
                        spot = spot.right;
                    }else{
                        System.out.println("Error! The compress file contains invalid character!");
                        return;
                    }
                }
            if(spot.left != null && spot.right != null){
                System.out.println("Error: The compress file is corrupted!");
            }
        }catch (IOException e){
            System.out.println("IOException in decode");
        }


        try{
            Scanner in = new Scanner(orig);
            while (in.hasNextLine()){
                deCompressedFile.write(in.nextLine() + "\n");
            }
        }catch (FileNotFoundException e){
            System.out.println("file not found");
        }catch (IOException e){
            System.out.println("IOException");
        }
    }

    //algorithm given
    public void userInterface(FileWriter debugFile, TreeNode root) {
        String nameOrg,nameCompress,nameDeCompress;
        char yesNo;
        Scanner in = new Scanner(System.in);
        System.out.println("Compress File?");
        yesNo = in.nextLine().charAt(0);
        if(yesNo == 'N'|| yesNo == 'n'){
            return;
        }else{
            System.out.println("Enter the name (without .txt ) of the file to be compressed");
            nameOrg = in.nextLine();
        }
        nameCompress = nameOrg + "_Compressed.txt";
        nameDeCompress = nameOrg + "_DeCompress.txt";
        nameOrg = nameOrg+".txt";

        try {
            File orgFile = new File(nameOrg);
            FileWriter compFile = new FileWriter(nameCompress);
            FileWriter deCompFile = new FileWriter(nameDeCompress);
            encode(orgFile,compFile,debugFile);
            compFile.close();
            File compressedFile = new File(nameCompress);
            decode(compressedFile,deCompFile,orgFile,root);

            deCompFile.close();
        }catch (IOException e){
            System.out.println("Exception in User Interface");
        }

    }

}
