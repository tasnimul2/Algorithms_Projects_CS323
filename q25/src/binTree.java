import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

public class binTree {
    treeNode root;
    int num;
    int[] inAry;

    int readCheck(String inFile){
        int i = 0;
        int prev = Integer.MIN_VALUE;
        int count = 0;
        try {

            File input = new File(inFile);
            Scanner scan = new Scanner(input);

            while(scan.hasNext()) {
                i = Integer.parseInt(scan.next());
                if (i > prev) {
                    prev = i;
                    count++;
                } else {
                    return -1;
                }


            }
        }catch (FileNotFoundException e){
            System.out.println("File not found. Make sure name is typed correctly");
        }
        return count;
    }

    void loadAry(String inFile, int[] inAry){
        int i = 0;
        try {
            Stack<Integer> temp = new Stack<>();
            File input = new File(inFile);
            Scanner scan = new Scanner(input);
            while(scan.hasNext()) {
                i = Integer.parseInt(scan.next());
                temp.push(i);
            }
            for(int j =0; j < inAry.length;j++){
                inAry[j] = temp.pop();
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found. Make sure name is typed correctly");
        }

    }
    void printAry(int[] inAry, FileWriter debug){
        try {
            for(int i =0; i < inAry.length; i++){
                debug.write(inAry[i]+ "  ");
            }
        }catch (IOException e){
            System.out.println("error while printing inAry");
        }
    }
    treeNode buildBinTree(int[] inAry, int leftIndex,int rightIndex){

        //requirements say "until left index is less or equal to right index
        //but leftIndex is always smaller from the start and it will hit
        // base case, so i changed it to leftIndex > rightIndex
        if(leftIndex > rightIndex){
            return null;
        }

        int rootLocation = (leftIndex + rightIndex) / 2;
        treeNode rootNode = new treeNode(inAry[rootLocation]);
        rootNode.left = buildBinTree(inAry,leftIndex,rootLocation-1);
        rootNode.right = buildBinTree(inAry,rootLocation+1,rightIndex);

        return rootNode;
    }

    void preOrder(treeNode root, FileWriter treeFile){
        if(root == null){
            return;
        }
        try {
            treeFile.write(root.data + "\n");
        }catch (IOException e){
            System.out.println("error in preorder");
        }
        preOrder(root.left,treeFile);
        preOrder(root.right,treeFile);
    }

    void inOrder(treeNode root, FileWriter treeFile){
        if(root == null){
            return;
        }

        inOrder(root.left,treeFile);
        try {
            treeFile.write(root.data + "\n");
        }catch (IOException e){
            System.out.println("error in preorder");
        }
        inOrder(root.right,treeFile);
    }

    void postOrder(treeNode root, FileWriter treeFile){
        if(root == null){
            return;
        }

        postOrder(root.left,treeFile);
        postOrder(root.right,treeFile);
        try {
            treeFile.write(root.data + "\n");
        }catch (IOException e){
            System.out.println("error in preorder");
        }
    }

}
