package KK.LinkedList;

public class CyclicLL {
    public static void main(String[] args) {
        LL obj = new LL();

        obj.InsertFirst(0);
        obj.InsertFirst(1);
        obj.InsertFirst(2);
        obj.InsertFirst(3);
        obj.InsertFirst(4);

        System.out.println(obj.IsLLCyclic(obj.head));

        var temp = obj.CreateCyclicLL();
        System.out.println(obj.IsLLCyclic(temp));
        System.out.println(obj.GetCycleLength(temp));
        //var s = obj.FindCycleStart(temp);
    }
}
