package KK.Recursion;

public class Permutations {
    public static void main(String[] args) {
        perm("", "atul");
    }

    public static void perm(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }

        char ch = up.charAt(0);

        for(int i=0; i<p.length() + 1; i++) {
            String modProcessed = p.substring(0, i) 
                                    + ch 
                                    + (i == p.length() ? "" :p.substring(i));

            perm(modProcessed, up.substring(1));
        }
    }
}
