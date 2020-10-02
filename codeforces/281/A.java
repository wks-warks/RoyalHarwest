// Codeforces 281A
import java.util.Scanner;

public class CF281A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String word = SC.next();
        String afterCapitalization = capitalize(word);
        System.out.println(afterCapitalization);
    }

    // Returns word after capitalizing first letter
    static String capitalize(String word) {
        String capitalizedWord;
        capitalizedWord = Character.toUpperCase(word.charAt(0)) + word.substring(1);
        return capitalizedWord;
    }
}