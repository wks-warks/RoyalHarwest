//Codeforces 1401B 
import java.util.Scanner;

public class CF1401B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        
        int zeroes, ones, twos;
        for (int t = 0; t < tests; ++t) {
            zeroes = SC.nextInt();
            ones = SC.nextInt();
            twos = SC.nextInt();
            Occurrences a = new Occurrences(zeroes, ones, twos);

            zeroes = SC.nextInt();
            ones = SC.nextInt();
            twos = SC.nextInt();
            Occurrences b = new Occurrences(zeroes, ones, twos);

            int maxSum = computeSum(a, b);
            System.out.println(maxSum);
        }
    }

    // Computes and returns the maximum obtainable sum value
    static int computeSum(Occurrences a, Occurrences b) {
        int sum, add = 2 * Math.min(a.twos, b.ones);
        a.twos -= add/2;
        b.ones -= add/2;
        sum = add;
        b.twos -= Math.min(b.twos, a.twos);
        a.ones -= Math.min(a.ones, b.zeroes);
        a.ones -= Math.min(a.ones, b.ones);
        sum -= 2 * Math.min(b.twos, a.ones);
        return sum;
    }
}

class Occurrences {
    int zeroes, ones, twos;
    public Occurrences(int zeroes, int ones, int twos) {
        this.zeroes = zeroes;
        this.ones = ones;
        this.twos = twos;
    }
}