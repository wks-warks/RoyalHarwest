// Codeforces 41A
import java.util.Scanner;

public class CF41A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String word = SC.next();
        String translation = SC.next();
        boolean isCorrectlyTranslated = checkTranslationCorrectness(word, translation);
        out(isCorrectlyTranslated);
    }

    // Checks and tells(returns boolean) if the word is correctly translated
    static boolean checkTranslationCorrectness(String word, String translation) {
        if (word.length() != translation.length())
            return false;
        int lastIdx = word.length() - 1;
        for (int i = 0; i < word.length(); ++i) {
            char wordChar = word.charAt(i);
            char translationChar = translation.charAt(lastIdx - i);
            if (wordChar != translationChar)
                return false;
        }
        return true; // All characters correspond to the appropriately positioned translated characters
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}