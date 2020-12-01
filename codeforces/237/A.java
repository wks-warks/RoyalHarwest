//Codeforces 237A 
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map;

public class CF237A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int visitors = SC.nextInt();
        TreeMap<Integer, Integer> concurrentCount = new TreeMap<>();
        for (int v = 0; v < visitors; ++v) {
            int hour = SC.nextInt();
            int minute = SC.nextInt();
            int time = hour * 60 + minute;
            if (concurrentCount.containsKey(time)) {
                concurrentCount.put(time, concurrentCount.get(time)+1);
            }
            else {
                concurrentCount.put(time, 1);
            }
        }

        int maxConcurrent = 0;
        for (int value : concurrentCount.values()) {
            maxConcurrent = Math.max(maxConcurrent, value);
        }
        System.out.println(maxConcurrent);
    }
}
