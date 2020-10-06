// Codeforces 486A
import java.util.Scanner;

public class CF486A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        long n = SC.nextLong();
        long fn = f(n);
        System.out.println(fn);
    }

    // Computes value of f at n
    static long f(long n) {
        if (n % 2 == 0)
            return n/2;
        else {
            return f(n-1) - n;
        }
    }
}