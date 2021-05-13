public class Main {
    public static void main(String[] args){
        System.out.println("hello world");
        AStar graph = new AStar();
        graph.listInsert(new AstarNode(0,1));
        graph.listInsert(new AstarNode(0,3));
        graph.listInsert(new AstarNode(0,5));
        graph.listInsert(new AstarNode(0,2));

        AstarNode curr = graph.Open;
        while(curr != null){
            System.out.println(curr.fStar + " ");
            curr = curr.next;
        }

        graph.remove();
        graph.remove();
        graph.listInsert(new AstarNode(0,13));
        graph.remove();
        graph.listInsert(new AstarNode(0,1));
        System.out.println("AFTER REMOVE");
        curr = graph.Open;
        while(curr != null){
            System.out.println(curr.fStar + " ");
            curr = curr.next;
        }

    }
}
