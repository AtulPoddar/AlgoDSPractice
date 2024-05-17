package LinkedList;

public class LLBubbleSort {
    public static void main(String[] args) {
        LL obj = new LL();

        obj.InsertFirst(1);
        obj.InsertFirst(2);
        obj.InsertFirst(3);
        obj.InsertFirst(4);
        obj.InsertFirst(5);

        obj.Display();
        obj.BBSort();

        System.out.println();
        obj.Display();
    }
}
