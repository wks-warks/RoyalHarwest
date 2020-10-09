// Codeforces 339B
import java.util.Scanner;

public class CF339B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int houses = SC.nextInt();
        int tasks = SC.nextInt();
        int[] taskHouse = new int[tasks];
        for (int t = 0; t < tasks; ++t)
            taskHouse[t] = SC.nextInt();
        long timeTaken = computeTimeTaken(houses, taskHouse);
        System.out.println(timeTaken);
    }

    // Computes and returns time taken to complete all tasks
    static long computeTimeTaken(int houses, int[] taskHouse) {
        long time = 0;
        int at = 1;
        for (int t = 0; t < taskHouse.length; ++t) {
            if (taskHouse[t] < at)
                time += houses-at + taskHouse[t];
            else
                time += taskHouse[t] - at;
            at = taskHouse[t];
        }
        return time;
    }
}