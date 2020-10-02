// Codeforces 236A
import java.util.Scanner;

public class CF236A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String name = SC.next();
        boolean isFemale = checkFemale(name);
        out(isFemale);
    }

    // Checks if the name is a female name
    static boolean checkFemale(String name) {
        int distinctChars = (int) name.chars().distinct().count();
        if (distinctChars % 2 == 0)
            return true;
        else
            return false;
    }

    // Prints output based on gender
    static void out(boolean isFemale) {
        if (isFemale)
            System.out.println("CHAT WITH HER!");
        else
            System.out.println("IGNORE HIM!");
    }
}