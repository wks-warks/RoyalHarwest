// Codeforces 110A
import java.util.Scanner;

public class CF110A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        long number = SC.nextLong();
        boolean isNearlyLucky = checkNearlyLucky(number);
        out(isNearlyLucky);
    }

    // Checks and returns-true if the number is nearly lucky
    static boolean checkNearlyLucky(long number) {
        int luckyDigits = countLuckyDigits(number);
        boolean isNearlyLucky = checkLucky(luckyDigits);
        return isNearlyLucky;
    }
    
    // Counts number of '4' and '7' in the given number
    static int countLuckyDigits(long number) {
        int luckyDigits = 0;
        String numStr = Long.toString(number);
        for (int i = 0; i < numStr.length(); ++i)
            if (numStr.charAt(i) == '4')
                luckyDigits += 1;
            else if (numStr.charAt(i) == '7')
                luckyDigits += 1;
        return luckyDigits;
    }

    // Checks and tells whethe or not a given number is comprised only of 4s and 7s
    static boolean checkLucky(int number) {
        String numStr = Integer.toString(number);
        for (int i = 0; i < numStr.length(); ++i)
            switch(numStr.charAt(i)) { // i-th most significant digit in number
                case '4':
                case '7':
                    break;
                default:
                    return false;
            }
        return true; // all digits were either 4 or 7
    }

    // Prints output based on the condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}