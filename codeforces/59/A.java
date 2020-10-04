// Codeforces 59A
import java.util.Scanner;

public class CF59A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String word = SC.next();
        String correctedWord = performCorrection(word);
        System.out.println(correctedWord);
    }

    // Corrects the word to the desirable from as per the question
    static String performCorrection(String word) {
        int lowerCase = 0;
        int upperCase = 0;
        for (int i = 0; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (Character.isLowerCase(ch))
                lowerCase += 1;
            else
                upperCase += 1;
        }
        if (upperCase > lowerCase)
            return word.toUpperCase();
        else
            return word.toLowerCase();
    }
}