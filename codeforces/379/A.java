// Codeforces 379A - Similar to 460A
import java.util.Scanner;

public class CF379A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int candles = SC.nextInt();
        int burntToUsable = SC.nextInt(); // Number of burnt candles that can be turned into one usable candle
        int hoursLit = computeMaxCandles(candles, burntToUsable, 0); // 0-used available, each candle can burn for 1hr
        System.out.println(hoursLit);
    }

    // Computes and returns total number of candles
    static int computeMaxCandles(int candles, int burntToUsable, int usedCandles) {
        if (candles == 0)
            return 0;
        else
            return candles + computeMaxCandles((candles+usedCandles)/burntToUsable, burntToUsable, (candles+usedCandles) % burntToUsable);
    }
}