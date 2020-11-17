//Codeforces 439A 
import java.util.Scanner;

public class CF439A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int songs = SC.nextInt();
        int eventDuration = SC.nextInt();
        int[] songLengths = new int[songs];
        for (int s = 0; s < songs; ++s)
            songLengths[s] = SC.nextInt();
        int jokesCracked = computeJokesCracked(songs, eventDuration, songLengths);
        System.out.println(jokesCracked);
    }

    // Computes and returns max. jokes that can be cracked for the duration of the event
    static int computeJokesCracked(int songs, int eventDuration, int[] songLengths) {
        int timeConsumed = 10 * (songs-1);
        for (int s = 0; s < songs; ++s)
            timeConsumed += songLengths[s];
        if (timeConsumed > eventDuration)
            return -1;
        else
            return 2 * (songs-1) + (eventDuration - timeConsumed) / 5;
    }
}