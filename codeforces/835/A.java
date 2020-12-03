//Codeforces 835A 
import java.util.Scanner;

public class CF835A {
    static final Scanner SC = new Scanner(System.in);
    
    static int characters, speed1, speed2, ping1, ping2;
    public static void main(String[] args) {
        characters = SC.nextInt();
        speed1 = SC.nextInt();
        speed2 = SC.nextInt();
        ping1 = SC.nextInt();
        ping2 = SC.nextInt();

        String result = getResult();
        System.out.println(result);
    }

    // Computes and returns result of the race
    static String getResult() {
        int time1 = 2 * ping1 + speed1 * characters;
        int time2 = 2 * ping2 + speed2 * characters;
        
        return time1 == time2 ? "Friendship" : time1 < time2 ? "First" : "Second";
    }
}
