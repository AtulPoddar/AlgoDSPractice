package LinkedList;

public class ReverseKGrp {
    public static void main(String[] args) {
        LL obj = new LL();

        obj.InsertFirst(10);
        obj.InsertFirst(9);
        obj.InsertFirst(8);
        obj.InsertFirst(7);
        obj.InsertFirst(6);
        obj.InsertFirst(5);
        obj.InsertFirst(4);
        obj.InsertFirst(3);
        obj.InsertFirst(2);
        obj.InsertFirst(1);

        //obj.Display();
        //System.out.println();
        //obj.ReverseKGroup(obj.head, 4);
        //obj.Display();

        obj.Display();
        System.out.println();
        obj.ReverseAlternateKGroup(obj.head, 2);
        obj.Display();
    }
}
