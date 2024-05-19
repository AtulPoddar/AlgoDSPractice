package LinkedList;

public class IsPalindrome {
    public static void main(String[] args) {
        LL obj = new LL();

        obj.InsertFirst(1);
        obj.InsertFirst(55);
        obj.InsertFirst(2);
        obj.InsertFirst(3);
        obj.InsertFirst(3);
        obj.InsertFirst(2);
        obj.InsertFirst(1);

        obj.Display();
        System.out.println();
        System.out.println(obj.IsPalindrome(obj.head));
        obj.Display();
    }
}
