//Codeforces 320A 
import java.util.Scanner;

public class CF320A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String numString = SC.next();
        boolean isMagical = checkMagicality(numString);
        out(isMagical);
    }

    // Checks and returns whether the number passed is magical
    static boolean checkMagicality(String numString) {
        for (int i = 0; i < numString.length(); ++i) {
            char ch = numString.charAt(i);
            if (ch != '1' && ch != '4')
                return false;
            else if (ch == '4' && i > 1 && numString.charAt(i-1) ==  '4' && numString.charAt(i-2) == '4')
                return false;
        }
        return numString.charAt(0) != '4';
    }


    // Prints output corresponding to condition
    static void out(boolean condition) {
        System.out.println(condition ? "YES" : "NO");
    }
}
