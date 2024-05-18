package LinkedList;

public class RevLL2 {
    public static void main(String[] args) {
        LL obj = new LL();

        obj.InsertFirst(5);
        obj.InsertFirst(4);
        obj.InsertFirst(3);
        obj.InsertFirst(2);
        obj.InsertFirst(1);

        obj.Display();
        obj.RevLL2(obj.head, 1, 4);
        
        System.out.println();
        obj.Display();
    }
}
