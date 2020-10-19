// Codeforces 584A
import java.util.Scanner;

public class CF584A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int digits = SC.nextInt();
        int divisor = SC.nextInt();
        String strNum = getNumString(digits, divisor);
        System.out.println(strNum);
    }

    // Returns divisor (2-10) followed by trailing zeroes
    static String getNumString(int digits, int divisor) {
        if (Integer.toString(divisor).length() > digits)
            return "-1";
        else {
            StringBuilder number = new StringBuilder(Integer.toString(divisor));
            while (number.length() < digits)
                number.append('0');
            return number.toString();
        }
    }
}