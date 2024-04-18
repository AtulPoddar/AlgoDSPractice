package Recursion;

public class KeypadCombination {
    public static void main(String[] args) {
        //combinations("abc", "def");
        comb2("", "12");
    }

    public static void combinations(String key1, String key2) {
        for(int i=0; i<key1.length(); i++) {
            var ch1 = Character.toString(key1.charAt(i));
            for(int j=0; j<key2.length(); j++) {
                var ch2 = Character.toString(key2.charAt(j));
                System.out.println(ch1 + ch2);
            }
        }
    }

    public static void comb2(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }

        int ch = up.charAt(0) - '0';    // -'0' converts "1" into 1
        for (int i = (ch-1)*4; i < ch*4; i++) {
            char ch2 = (char)('a' + i);     // 'a' + 0 = 'a', 'a' + 1 = 'b' etc.

            comb2(p + ch2, up.substring(1));
        }
    }
}
