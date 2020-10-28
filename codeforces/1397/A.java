//Codeforces 1397A 
import java.util.Scanner;

public class CF1397A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int numStrings = SC.nextInt();
            String[] strings = new String[numStrings];
            for (int i = 0; i < numStrings; ++i)
                strings[i] = SC.next();
            boolean canEqualize = isEqualizingPossible(strings);
            out(canEqualize);
        }
    }

    // Checks and returns whether or not all the strings passed are composed of the same characters(same count as well)
    static boolean isEqualizingPossible(String[] strings) {
        int[] totalOccurrences = new int[26];
        for (int i = 0; i < strings.length; ++i) {
            int[] charOccurrences = characterCount(strings[i]);
            for (int j = 0; j < totalOccurrences.length; ++j)
                totalOccurrences[j] += charOccurrences[j];
        }
        for (int i = 0; i < totalOccurrences.length; ++i)
            if (totalOccurrences[i] % strings.length != 0)
                return false;
        return true; // Can share characters equally among all strings
    }

    // Returns an array with count of character occurrences in the string
    static int[] characterCount(String s) {
        int[] charCount = new int[26];
        for (int i = 0; i < s.length(); ++i)
            charCount[s.charAt(i)-'a'] += 1;
        return charCount;
    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}
