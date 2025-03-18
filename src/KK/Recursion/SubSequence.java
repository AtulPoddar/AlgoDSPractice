package KK.Recursion;

import java.util.ArrayList;

public class SubSequence {
    public static void main(String[] args) {
        //var ls = subSeqList("", "rdx");
        //System.out.println(ls);

        var list = new ArrayList<String>();
        subSeqLst("", "rdx", list);
        System.out.println(list);
    }

    public static void subSeq(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }

        char ch = up.charAt(0);

        subSeq(p + ch, up.substring(1));
        subSeq(p, up.substring(1));
    }

    public static void subSeqLst(String p, String up, ArrayList<String> list) {
        if (up.isEmpty()) {
            list.add(p);
            return;
        }

        char ch = up.charAt(0);

        subSeqLst(p + ch, up.substring(1), list);
        subSeqLst(p, up.substring(1), list);
    }

    public static ArrayList<String> subSeqList(String p, String up) {
        if(up.isEmpty()) {
            var ls = new ArrayList<String>();
            ls.add(p);
            return ls;
        }

        char ch = up.charAt(0);

        var ls = new ArrayList<String>();

        ls.addAll(subSeqList(p + ch, up.substring(1)));
        ls.addAll(subSeqList(p, up.substring(1)));

        return ls;
    }
}
