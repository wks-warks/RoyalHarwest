// Codeforces 520A
import java.util.Scanner;

public class CF520A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int charsInString = SC.nextInt();
        String sentence = SC.next();
        boolean isPangram = checkPangram(sentence);
        out(isPangram);
    }

    // Checks and returns boolean whether or not the given string is a pangram
    static boolean checkPangram(String sentence) {
        boolean[] lettersFound = new boolean[26];
        // Assigning letters found
        for (int i = 0; i < sentence.length(); ++i) {
            char ch = sentence.charAt(i);
            if (Character.isLowerCase(ch))
                lettersFound[ch-97] = true;
            else
                lettersFound[ch-65] = true;
        }
        
        // Checking if any letter isn't found yet
        for (int i = 0; i < 26; ++i)
            if (!lettersFound[i])
                return false; // Some letter not found
        return true; // All letters found
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}