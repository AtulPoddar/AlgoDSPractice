import java.util.HashMap;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) {
        System.out.println(ans("Sattisshh baabbbu"));
    }

    public static String ans(String input) {

        StringBuilder res = new StringBuilder();
        int i = 0;
        int count = 1;
        for (int j = 1; j < input.length(); j++) {
            if (input.charAt(i) == input.charAt(j)) {
                count++;
            }
            else {
                res.append(input.charAt(i));
                if (count > 1) {
                    res.append(count);
                }

                i = j;
                count = 1;
            }
        }

        if (count == 1) {
            res.append(input.charAt(i));
        }

        return res.toString();
        Executors.newFixedThreadPool(count)
    }

    public static String concat(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            Character index = input.charAt(i);
            if (map.containsKey(index)) {
                map.put(index, map.get(index) + 1);
            }
            else {

                if (map.entrySet().isEmpty()) {
                    map.put(index, 1);
                }
                else {
                    for (var entry : map.entrySet()) {
                        result.append(entry.getKey());
                        if (entry.getValue() > 1) {
                            result.append(entry.getValue());
                        }
                    }
                    
                    map.clear();
                    map.put(index, 1);
                }
            }
        }
        
        return result.toString();
    }
}
