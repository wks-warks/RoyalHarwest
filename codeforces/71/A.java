// Codeforces 71A
import java.util.Scanner;

public class CF71A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numWords = SC.nextInt(); // Number of Words
        for (int w = 0; w < numWords; ++w) {
            String word = SC.next();
            String abbreviation = abbreviate(word);
            System.out.println(abbreviation);
        }
    }

    // returns resultant word after performing abbreviation operation
    static String abbreviate(String word) {
        int len = word.length(); // word-length
        if (len <= 10) // no need to abbreviate
            return word;
        else {
            String abbreviation;
            int code = len - 2;
            abbreviation = word.substring(0, 1) + code + word.substring(len-1);
            return abbreviation;
        }
    }
}