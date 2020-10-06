// Codeforces 61A
import java.util.Scanner;

public class CF61A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String num1 = SC.next();
        String num2 = SC.next();
        String result = xorOf(num1, num2);
        System.out.println(result);
    }

    // Gives XOR-string of two String-numbers
    static String xorOf(String num1, String num2) {
        int len = num1.length(); // Same as num2.length()
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; ++i) {
            boolean sameBits = num1.charAt(i) == num2.charAt(i);
            if (sameBits)
                sb.append('0');
            else
                sb.append('1');
        }
        return sb.toString();
    }
}