//Codeforces 822A 
import java.util.Scanner;

public class CF822A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int a = SC.nextInt();
        int b = SC.nextInt();
        int gcdFactorial = factorial(Math.min(a,b));
        System.out.println(gcdFactorial);
    }

    // Computes and returns Factorial of a number
    static int factorial(int n) {
        return (n == 1) ? 1 : n * factorial(n-1);
    }
}
