//Codeforces 731A 
import java.util.Scanner;

public class CF731A {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        String name = SC.next();
        int rotationsNeeded = computeRotationsNeeded(name);
        System.out.println(rotationsNeeded);
    }

    // Computes and returns minimum number of rotations required to print the name
    static int computeRotationsNeeded(String name) {
        int rotationsNeeded = 0;
        int pointer = 0;
        for (int i = 0; i < name.length(); ++i) {
            int destination = name.charAt(i) - 'a';
            int difference = Math.abs(destination-pointer);
            int additionalRotations = Math.min(difference, 26-difference);
            rotationsNeeded += additionalRotations;
            pointer = destination;
        }
        return rotationsNeeded;
    }
}
