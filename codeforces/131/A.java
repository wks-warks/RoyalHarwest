// Codeforces 131A
import java.util.Scanner;

public class CF131A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String word = SC.next();
        String processedWord = process(word);
        System.out.println(processedWord);
    }

    // Process the word to make appropriate changes w.r.t. capitalization
    static String process(String word) {
        boolean firstCheck = checkIfAllCaps(word);
        boolean secondCheck = checkIfAllCapsExceptFirst(word);
        if (firstCheck || secondCheck)
            return reverseCase(word);
        else
            return word;
    }

    // Checks if all letters in the string are upper case and returns true if so
    static boolean checkIfAllCaps(String word) {
        char firstChar = word.charAt(0);
        boolean firstUpper = Character.isLowerCase(firstChar);
        boolean othersUpper = checkIfAllCapsExceptFirst(word);
        return firstUpper && othersUpper; // Returns true only if all the characters are upperCase ones, returns false otherwise
    }

    // Checks if all letters in the string apart from the first one are uppercase
    static boolean checkIfAllCapsExceptFirst(String word) {
        for (int i = 1; i < word.length(); ++i) {
            char ch = word.charAt(i);
            if (Character.isLowerCase(ch))
                return false;
        }
        return true; // None of the considered characters were lowerCase ones
    }

    // Returns the passed string with reversed case
    static String reverseCase(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            boolean isUpper = Character.isUpperCase(ch);
            if (isUpper)
                sb.append(Character.toLowerCase(ch));
            else
                sb.append(Character.toUpperCase(ch));
        }
        return sb.toString();
    }
}