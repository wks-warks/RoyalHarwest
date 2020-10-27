//Codeforces 9A 
import java.util.Scanner;

public class CF9A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int resultYakko = SC.nextInt();
        int resultWakko = SC.nextInt();
        String chanceDot = getDotChance(resultYakko, resultWakko);
        System.out.println(chanceDot);
    }

    // Checks and returns chance of Dot winning
    static String getDotChance(int resultYakko, int resultWakko) {
        int othersMax = Math.max(resultYakko, resultWakko);
        String chance;
        switch(othersMax) {
            case 6:
                chance = "1/6";
                break;
            case 5:
                chance = "1/3";
                break;
            case 4:
                chance = "1/2";
                break;
            case 3:
                chance = "2/3";
                break;
            case 2:
                chance = "5/6";
                break;
            default:
                chance = "1/1";
        }
        return chance;
    }
}
