// Codeforces 443A
import java.util.Scanner;

public class CF443A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String set = SC.nextLine();
        int distinctLetters = countDistinctLetters(set);
        System.out.println(distinctLetters);        
    }

    // Counts and returns number of distinct letters in the set
    static int countDistinctLetters(String set) {
        boolean[] letterFound = new boolean[26];
        int distinct = 0;
        for (int i = 0; i < set.length(); ++i) {
            char ch = set.charAt(i);
            if (Character.isLowerCase(ch) && !letterFound[ch-97]) {
                distinct += 1;
                letterFound[ch-97] = true;
            }
        }
        return distinct;
    }
}