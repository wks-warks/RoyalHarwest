//Codeforces 633A 
import java.util.Scanner;

public class CF633A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int a = SC.nextInt();
        int b = SC.nextInt();
        int c = SC.nextInt();
        boolean canBeat = checkNonNegSolution(a, b, c);
        System.out.println(canBeat ? "Yes" : "No");
    }

    // Checks and returns whether or not non-negative solutions for given diophantine equation ax + by = c exist
    static boolean checkNonNegSolution(int a, int b, int c) {
        int g = gcd(a,b);
        if (c % g > 0)
            return false;
        int[] solution = new int[2];
        exgcd(a, b, solution);
        solution[0] *= c/g;
        solution[1] *= c/g;
        boolean answer = existsInRange(a, b, solution);
        return answer;
    }

    // Checks and returns whether or not a solution in positive region exists
    static boolean existsInRange(int a, int b, int[] solution) {
        int g = gcd(a, b);
        return ((double)solution[1] * g / a >= LIG((double)solution[0]*g/b*-1));
    }

    // Performs extended gcd algorithm on given numbers and array of coefficients
    static int exgcd(int num1, int num2, int[] coeffs) {
        if (num2 == 0) {
            coeffs[0] = 1;
            coeffs[1] = 0;
            return num1;
        }
        int[] pass = new int[2];
        int g = exgcd(num2, num1 % num2, pass);
        coeffs[0] = pass[1];
        coeffs[1] = pass[0] - pass[1] * (num1 / num2);
        return g;
    }

    // Computes and returns least integer greater than decimal
    static int LIG(double decimal) {
        int num = (int) decimal;
        if (decimal > 0 && num != decimal)
            num += 1;
        return num;
    }

    // Returns gcd of two numbers
    static int gcd(int num1, int num2) {
        if (num1 == 0) return num2;
        else return gcd(num2 % num1, num1);
    }
}
