//Codeforces 1041A 
import java.util.Scanner;

public class CF1041A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int remainingKeyboards = SC.nextInt();

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int k = 0; k < remainingKeyboards; ++k) {
            int value = SC.nextInt();
            max = Math.max(max, value);
            min = Math.min(min, value);
        }

        System.out.println(max-min+1 - remainingKeyboards);
    }
}
