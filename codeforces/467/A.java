// Codeforces 467A
import java.util.Scanner;

public class CF467A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int numRooms = SC.nextInt(); // Number of rooms in total
        int[] occupants = new int[numRooms];
        int[] capacity = new int[numRooms];
        for (int r = 0; r < numRooms; ++r) {
            occupants[r] = SC.nextInt();
            capacity[r] = SC.nextInt();
        }
        int numPotentialRooms = computeNumPotentialRooms(numRooms, occupants, capacity);
        System.out.println(numPotentialRooms);
    }

    // Computes and returns number of rooms potentially occupiable by both George and Alex
    static int computeNumPotentialRooms(int numRooms, int[] occupants, int[] capacity) {
        int numPotentialRooms = 0;
        for (int r = 0; r < numRooms; ++r) {
            int vacancy = capacity[r] - occupants[r];
            if (vacancy >= 2) // Checks if room can accommodate two more persons 
                numPotentialRooms += 1;
        }
        return numPotentialRooms;
    }
}