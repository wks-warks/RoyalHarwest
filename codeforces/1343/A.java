// Codeforces 1343A
import java.util.Scanner;

public class CF1343A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int testCases = SC.nextInt();
        for (int tc = 0; tc < testCases; ++tc) {
            int wrappers = SC.nextInt();
            int candiesOnDay1 = computeCandiesDay1(wrappers);
            System.out.println(candiesOnDay1);
        }
    }

    // Computes and returns number of candies possibly eaten on day 1
    static int computeCandiesDay1(int wrappers) {
        // Any solution will be of the form (2^p-1)*integer (think binary- 0b11*x, 0b111*y, 0b1111*z etc)
        int candiesOnDay1 = 0;
        boolean answerFound = false;
        int check = 3; // 0b11: Initial check
        while (!answerFound) {
            if (wrappers % check == 0) {
                candiesOnDay1 = wrappers / check;
                answerFound = true;
            }
            check = (check<<1) + 1;
        }
        return candiesOnDay1;
    }
}