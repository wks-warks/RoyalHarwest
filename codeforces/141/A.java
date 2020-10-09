// Codeforces 141A
import java.util.Scanner;

public class CF141A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String guest = SC.next();
        String host = SC.next();
        String jumbled = SC.next();
        boolean isJokeAmusing = checkAmusing(guest, host, jumbled); // Are the letters in the first two strings same as the ones used in the last string?
        out(isJokeAmusing);
    }
    
    // Checks and returns boolean whether or not the given joke is amusing
    static boolean checkAmusing(String guest, String host, String jumbled) {
        int[] letterCount = new int[26];

        // Counting characters in jumbled pile
        for (int i = 0; i < jumbled.length(); ++i) {
            char ch = jumbled.charAt(i);
            letterCount[ch-65] += 1;
        }

        // Removing characters from jumbled pile to see whether host, guest names can be rewritten
        for (int i = 0; i < guest.length(); ++i) {
            char ch = guest.charAt(i);
            letterCount[ch-65] -= 1;
        }
        for (int i = 0; i < host.length(); ++i) {
            char ch = host.charAt(i);
            letterCount[ch-65] -= 1;
        }

        // If letterCount[i] < 0 => Letter missing in jumbled pile. If letterCount[i] > 0 => Additional letter found in jumbled pile
        for (int i = 0; i < 26; ++i)
            if (letterCount[i] != 0)
                return false;
        return true; // Exactly same letters from host,guest names found in jumbled pile

    }

    // Prints output corresponding to condition
    static void out(boolean condition) {
        if (condition)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}