package LinkedList;

public class ReverseHop {
    public static void main(String[] args) {
        LL obj = new LL();

        obj.InsertFirst(7);
        obj.InsertFirst(6);
        obj.InsertFirst(5);
        obj.InsertFirst(4);
        obj.InsertFirst(3);
        obj.InsertFirst(2);
        obj.InsertFirst(1);

        obj.Display();
        System.out.println();
        obj.ReverseHop(obj.head);
        System.out.println();
        obj.Display();
    }
}
