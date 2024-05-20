package LinkedList;

public class RotateLL {
    public static void main(String[] args) {
        LL obj = new LL();

        obj.InsertFirst(5);
        obj.InsertFirst(4);
        obj.InsertFirst(3);
        obj.InsertFirst(2);
        obj.InsertFirst(1);

        obj.Display();
        System.out.println();
        obj.RotateLL(obj.head, 6);
        obj.Display();
    }
}
