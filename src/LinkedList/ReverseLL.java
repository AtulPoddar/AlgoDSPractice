package LinkedList;

public class ReverseLL {
    public static void main(String[] args) {
        LL obj = new LL();

        obj.InsertFirst(5);
        obj.InsertFirst(4);
        obj.InsertFirst(3);
        obj.InsertFirst(2);
        obj.InsertFirst(1);

        obj.Display();

        obj.ReverseLL(obj.head);

        System.out.println();
        obj.Display();

        obj.InPlaceReverse(obj.head);

        System.out.println();
        obj.Display();
    }
}
