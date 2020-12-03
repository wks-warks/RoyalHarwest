//Codeforces 1114A 
import java.util.Scanner;

public class CF1114A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int onlyGreen = SC.nextInt();
        int greenOrPurple = SC.nextInt();
        int any = SC.nextInt();
        int green = SC.nextInt();
        int purple = SC.nextInt();
        int black = SC.nextInt();
        boolean everyoneHappy = checkHappiness(onlyGreen, greenOrPurple, any, green, purple, black);
        System.out.println(everyoneHappy ? "YES" : "NO");
    }

    // Checks and returns whether a happy distribution is possible
    static boolean checkHappiness(int onlyGreen, int greenOrPurple, int any, int green, int purple, int black) {
        green -= onlyGreen;
        if (green < 0)
            return false;
        int take = Math.min(green, greenOrPurple);
        greenOrPurple -= take;
        green -= take;
        take = Math.min(purple, greenOrPurple);
        greenOrPurple -= take;
        purple -= take;
        if (greenOrPurple > 0)
            return false;
        else
            return green + purple + black >= any;
    }
}
