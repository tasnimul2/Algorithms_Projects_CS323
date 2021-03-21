import java.io.FileWriter;
import java.io.IOException;

public class TreeNode {
    private String chStr;
    private int frequency;
    private String code;
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

    // format: (T’s chStr, T’s frequency, T’s code, T’s next chStr, T’s left’s chStr, T ‘s right’s chStr);
    //one print per text
    public void printNode(TreeNode t, FileWriter debugFile){
        try{
            if(this.left!= null&& this.right!=null ) {//&& this.next!= null && t.next.chStr != null && t.left.chStr != null && t.right.chStr != null
                debugFile.write("(chStr: "+this.chStr + ",Frequency: " + this.frequency + ",code: " + this.code +
                        ",next chStr: " + t.next.chStr + ",Left chStr: " + t.left.chStr + ",Right chStr: " + t.right.chStr +")");
            }else if(this.left!= null&& this.right==null){
                debugFile.write("(chStr: "+this.chStr + ",Frequency: " + this.frequency + ",code: " + this.code +
                        ",next chStr: " + t.next.chStr + ",Left chStr: " + t.left.chStr + ",Right chStr: NULL" +")");
            }else if(this.left == null && this.right!=null){
                debugFile.write("(chStr: "+this.chStr + ",Frequency: " + this.frequency + ",code: " + this.code +
                        ",next chStr: " + t.next.chStr + ",Left chStr: NULL"  + ",Right chStr: " + t.right.chStr +")");
            }else if(t.next != null){
                debugFile.write("(chStr: "+this.chStr + ",Frequency: " + this.frequency + ",code: " + this.code +
                        ",next chStr: " + t.next.chStr + ",Left chStr: " + "null" + ",Right chStr: " +"null" +")");
            }else{
                debugFile.write("(chStr: "+this.chStr + ",Frequency: " + this.frequency + ",code: " + this.code +
                        ",next chStr: NULL,Left chStr:NULL,Right chStr: NULL" + ")");
            }

        }catch (IOException e){
            System.out.println("Error occurred in printNode");
        }

    }
}
