// Codeforces 474A
import java.util.Scanner;
import java.util.HashMap;

public class CF474A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        char shift = SC.next().charAt(0);
        String errantText = SC.next();
        String correctedText = getCorrection(shift, errantText);
        System.out.println(correctedText);
    }

    // Returns corrected text based on provided values
    static String getCorrection(char shift, String errantText) {
        String[] rows = {
            "qwertyuiop",
            "asdfghjkl;",
            "zxcvbnm,./"
        };

        // Setting replacements
        HashMap<Character, Character> leftReplacement = new HashMap<>();
        for (int i = 0; i < 3; ++i)
            for (int j = 1; j < rows[i].length(); ++j)
                leftReplacement.put(rows[i].charAt(j), rows[i].charAt(j-1));
        HashMap<Character, Character> rightReplacement = new HashMap<>();
        for (int i = 0; i < 3; ++i)
            for (int j = 0; j < rows[i].length()-1; ++j)
                rightReplacement.put(rows[i].charAt(j), rows[i].charAt(j+1));
        
        // Building corrected text on the basis of replacements
        StringBuilder correctedText = new StringBuilder();
        if (shift == 'R')
            for (int i = 0; i < errantText.length(); ++i)
                correctedText.append(leftReplacement.get(errantText.charAt(i)));
        else
            for (int i = 0; i < errantText.length(); ++i)
                correctedText.append(rightReplacement.get(errantText.charAt(i)));
        
        return correctedText.toString();
    }
}