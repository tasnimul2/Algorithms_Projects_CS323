import java.io.FileWriter;
import java.io.IOException;

public class TreeNode {
    public String chStr;
    public int frequency;
    public String code;
    public TreeNode left;
    public TreeNode right;
    public TreeNode next;

    public String getChStr() {
        return chStr;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getCode() {
        return code;
    }


    public void setChStr(String chStr) {
        this.chStr = chStr;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setCode(String code) {
        this.code = code;
    }



    public TreeNode(String chStr, int frequency, String code,TreeNode left,TreeNode right, TreeNode next ){
        this.chStr = chStr;
        this.frequency = frequency;
        this.code = code;
        this.left = left;
        this.right = right;
        this.next = next;

    }
    public TreeNode(){

    }

    // format: (T’s chStr, T’s frequency, T’s code, T’s next chStr, T’s left’s chStr, T ‘s right’s chStr);
    //one print per text
    public void printNode(TreeNode t, FileWriter debugFile){
        try{
            if(this.left!= null&& this.right!=null && this.next != null) {// t.next.chStr != null && t.left.chStr != null && t.right.chStr != null
                debugFile.write("("+this.chStr + "," + this.frequency + "," + this.code +
                        "," + t.next.chStr + "," + t.left.chStr + "," + t.right.chStr +")\n");
            }else if(this.left!= null&& this.right!=null && this.next == null){
                debugFile.write("("+this.chStr + "," + this.frequency + "," + this.code +
                        "," + "null" + "," + t.left.chStr + "," + t.right.chStr +")\n");
            }else if(this.left!= null&& this.right==null){
                debugFile.write("("+this.chStr + "," + this.frequency + "," + this.code +
                        "," + t.next.chStr + "," + t.left.chStr + "," +")\n");
            }else if(this.left == null && this.right!=null){
                debugFile.write("("+this.chStr + "," + this.frequency + "," + this.code +
                        "," + t.next.chStr + ",NULL"  + "," + t.right.chStr +")\n");
            }else if(t.next != null){
                debugFile.write("("+this.chStr + "," + this.frequency + "," + this.code +
                        "," + t.next.chStr + "," + "null" + "," +"null" +")\n");
            }else{
                debugFile.write("("+this.chStr + "," + this.frequency + "," + this.code +
                        ",NULL,NULL,NULL" + ")\n");
            }

        }catch (IOException e){
            System.out.println("Error occurred in printNode");
        }catch (NullPointerException e){
            System.out.println("Null pointer exception in printNode");
        }

    }
}
