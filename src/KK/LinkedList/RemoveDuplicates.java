package KK.LinkedList;

public class RemoveDuplicates {
    public static void main(String[] args) {
        LL obj = new LL();

        obj.InsertFirst(4);
        obj.InsertFirst(4);
        obj.InsertFirst(4);
        obj.InsertFirst(3);
        obj.InsertFirst(2);
        obj.InsertFirst(2);
        obj.InsertFirst(1);
        obj.InsertFirst(1);
        obj.InsertFirst(1);

        obj.Display();

        obj.RemoveDuplicates(obj.head);

        System.out.println();
        obj.Display();
    }
}
