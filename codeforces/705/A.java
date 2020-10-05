// Codeforces 705A
import java.util.Scanner;

public class CF705A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int layers = SC.nextInt();
        String feelings = getFeelings(layers);
        System.out.println(feelings);
    }

    // Gets Dr.Banner's feelings
    static String getFeelings(int layers) {
        StringBuilder sb = new StringBuilder("I hate");
        for (int lyr = 1; lyr < layers; ++lyr) {
            if (lyr % 2 == 0) // Decides feeling based on layer number
                sb.append(" that I hate");
            else
                sb.append(" that I love");
        }
        sb.append(" it");
        return sb.toString();
    }
}