// Codeforces 116A
import java.util.Scanner;

public class CF116A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int stops = SC.nextInt();
        int[][] passengerMovement = new int[stops][2];
        // passengerMovement[s][0]: passengers exiting at stop s
        // passengerMovement[s][1]: passengers entering at stop s
        for (int s = 0; s < stops; ++s) {
            passengerMovement[s][0] = SC.nextInt();
            passengerMovement[s][1] = SC.nextInt();
        }

        int minCapacity = computeMinCapacityRequired(passengerMovement);
        System.out.println(minCapacity);
    }

    // Computes minimum required capacity for tram
    static int computeMinCapacityRequired(int[][] passengerMovement) {
        int passengers = 0;
        int required = 0;
        for (int s = 0; s < passengerMovement.length; ++s) {
            passengers += passengerMovement[s][1] - passengerMovement[s][0];
            required = Math.max(required, passengers);
        }
        return required;
    }
}