import java.io.File;
import java.io.FileWriter;

public class HuffmanCoding {
    public int[] charCountAry = new int[256];// a 1-D array to store the character counts.
    public String[]charCode= new String[256]; // a 1-D array to store the Huffman code table,

    // computeCharCounts (...) // Read a character from input file, use (int) to get index, asci code of the character;
    // charCountAry[index]++. You should know how to do this method.
    public void computeCharCounts(File inFile){

    }

    /*
      printCountAry (...) // print the character count array to DebugFile, in the following format:
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

    }
    //algorithm given
    public void constructHuffmanLList(){

    }

    //algorithm given
    public void constructHuffmanBinTree(){

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