// Codeforces 977A
import java.util.Scanner;

public class CF977A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int n = SC.nextInt(); // Number
        int operations = SC.nextInt(); // No. of times Tanya subtracts from the number
        int result = afterSubtractions(n, operations);
        System.out.println(result);
    }

    // Computes result after Tanya performs subtractions
    static int afterSubtractions(int number, int operations) {
        if (operations == 0)
            return number;
        else if (number % 10 == 0)
            return afterSubtractions(number / 10, operations - 1);
        else
            return afterSubtractions(number - 1, operations - 1);
    }
}