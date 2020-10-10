// Codeforces 1352A
import java.util.Scanner;
import java.util.Vector;

public class CF1352A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt(); // Number of testCases
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            Vector<Integer> summands = getSummandsVector(n);
            out(summands);
        }
    }

    // Gets Vector of Round-Numbered summands
    static Vector<Integer> getSummandsVector(int n) {
        Vector<Integer> summands = new Vector<>();
        int div = 10;
        while (n > 0) {
            int addend = n % div;
            if (addend != 0)
                summands.add(addend);
            n -= addend;
            div *= 10;
        }
        return summands;
    }

    // Prints output corresponding to summands-vector
    static void out(Vector<Integer> v) {
        System.out.println(v.size());
        for (int i = 0; i < v.size(); ++i)
            System.out.print(v.get(i) + " ");
        System.out.println();
    }
}