package Recursion;

public class SkipCharFromString {
    public static void main(String[] args) {
        //System.out.println(skip1("atulatulluta", 'l'));

        //StringBuilder result = new StringBuilder();
        //skip2("hellllllloooolllioioil", 0, 'l', result);
        //System.out.println(result);

        System.out.println(skipString("poddaratulpoddarpoddaratuljkjj", "poddar"));
    }

    public static String skip1(String str, char skipChr) {
        if (str.length() == 0) {
            return "";
        }

        String charToAdd = str.charAt(0) == skipChr ? "" : Character.toString(str.charAt(0));

        return charToAdd + skip1(str.substring(1, str.length()), skipChr);
    }

    public static void skip2(String str, int index, char skipChar, StringBuilder returnStr) {
        if (index == str.length()) {
            return;
        }

        if (str.charAt(index) != skipChar) {
            returnStr.append(str.charAt(index));
        }

        skip2(str, index+1, skipChar, returnStr);
    }

    public static String skipString(String str, String skipStr) {
        if (str.length() == 0) {
            return "";
        }

        if (str.startsWith(skipStr)) {
            return skipString(str.substring(skipStr.length()), skipStr);
        }
        else {
            return str.charAt(0) + skipString(str.substring(1), skipStr);
        }
    }
}
