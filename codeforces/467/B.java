//Codeforces 467B 
import java.util.Scanner;

public class CF467B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int soldierTypes = SC.nextInt();
        int rivals = SC.nextInt(); // Number of players other than Fedor
        int dfrncLimit = SC.nextInt(); // If the armies differ in upto these many soldier types, players can become friends
        int[] rivalArmies = new int[rivals];
        for (int r = 0; r < rivals; ++r)
            rivalArmies[r] = SC.nextInt();
        int fedorArmy = SC.nextInt();
        int potentialFriends = countPotentialFriends(fedorArmy, rivalArmies, dfrncLimit);
        System.out.println(potentialFriends);
    }

    // Computes and returns number of potential friends that Fedor can make
    static int countPotentialFriends(int fedorArmy, int[] rivalArmies, int dfrncLimit) {
        int potentialFriends = 0;
        for (int r = 0; r < rivalArmies.length; ++r)
            if (countSetBits(fedorArmy ^ rivalArmies[r]) <= dfrncLimit)
                potentialFriends += 1;
        return potentialFriends;
    }

    // Reference: https://www.geeksforgeeks.org/count-set-bits-in-an-integer/
    static int countSetBits(int n) 
    { 
        int count = 0; 
        while (n > 0) { 
            n &= (n - 1); 
            count++; 
        } 
        return count; 
    } // Utilizes Brian Kernighan's Algorithm
}
