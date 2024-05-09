package LinkedList;

public class main {
    public static void main(String[] args) {
        LL obj = new LL();

        obj.InsertFirst(11);
        obj.InsertFirst(13);
        obj.InsertFirst(16);
        obj.InsertFirst(18);

        //obj.InsertIndexRec1(33, 0, obj.head);

        obj.InsertIndexRec2(33, 0);

        obj.Display();
    }
}
