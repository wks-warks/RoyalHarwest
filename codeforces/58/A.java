// Codeforces 58A
import java.util.Scanner;

public class CF58A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String word = SC.next();
        boolean helloInside = helloCheck(word);
        out(helloInside);
    }

    // Checks for h-e-l-l-o(not necessarily contiguous) inside given word
    static boolean helloCheck(String word) {
        String matchAgainst = "hello";
        int lettersFound = 0;
        for (int i = 0; i < word.length(); ++i) {
            if (lettersFound == 5) // found 'hello'
                break;

            boolean match = word.charAt(i) == matchAgainst.charAt(lettersFound);
            if (match)
                lettersFound += 1;
        }
        if (lettersFound == 5)
            return true;
        else
            return false;
    }

    // Prints output based on condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}