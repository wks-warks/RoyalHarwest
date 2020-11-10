//Codeforces 1003A 
import java.util.Scanner;
import java.util.HashMap;

public class CF1003A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        HashMap<Integer, Integer> coinCount = new HashMap<>();
        int coins = SC.nextInt();
        int maxOccurrences = 1;
        for (int c = 0; c < coins; ++c) {
            int coinValue = SC.nextInt();
            if (coinCount.containsKey(coinValue)) {
                int count = coinCount.get(coinValue) + 1;
                coinCount.put(coinValue, count);
                maxOccurrences = Math.max(maxOccurrences, count);
            }
            else
                coinCount.put(coinValue, 1);
        }
        System.out.println(maxOccurrences);
    }
}
