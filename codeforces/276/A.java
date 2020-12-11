//Codeforces 276A 
import java.util.Scanner;

public class CF276A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int restaurants = SC.nextInt();
        int timeAvailable = SC.nextInt();
        int[][] restaurantInfo = new int[restaurants][2];
        int maxJoy = Integer.MIN_VALUE;
        for (int r = 0; r < restaurants; ++r) {
            restaurantInfo[r][0] = SC.nextInt();
            restaurantInfo[r][1] = SC.nextInt();
            maxJoy = Math.max(maxJoy, restaurantInfo[r][0] + Math.min(0, timeAvailable - restaurantInfo[r][1]));
        }
        System.out.println(maxJoy);
    }
}
