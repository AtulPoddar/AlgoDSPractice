package KK.LinkedList;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.function.Function;

public class main {
    public static void main(String[] args) {
        var obj = new Test();

        var tt = obj.getClass();
        System.out.println(tt.toString());

        var ttt = obj instanceof Object;
        System.out.println(ttt);

        Function<Integer, Integer> t = (y) -> y;

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "Hello ")
            .thenApply((s) -> s + "World");
        //ExecutorService ttt  = Executors.newFixedThreadPool();

        HashSet<Integer> temppp = new HashSet<Integer>();
        temppp.add(1);

        HashMap<Integer, String> tr = new HashMap<Integer, String>();
        tr.put(2, "dd");
    }

    public <T extends Number> void tttttt(T value, List<?> vk) {

    }
}
