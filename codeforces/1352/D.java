//Codeforces 1352D 
import java.util.Scanner;

public class CF1352D {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int numberOfCandies = SC.nextInt();
            int[] candySizes = new int[numberOfCandies];
            for (int candy = 0; candy < numberOfCandies; ++candy) {
                candySizes[candy] = SC.nextInt();
            }
            Solution solution = getSolution(numberOfCandies, candySizes);
            print(solution);
        }
    }

    // Computes and returns solution for given case
    static Solution getSolution(int numberOfCandies, int[] candySizes) {
        int totalAlice, totalBob;
        int prevAlice, prevBob;
        totalAlice = totalBob = prevAlice = prevBob = 0;

        int moves = 0;
        int alicePointer = 0;
        int bobPointer = numberOfCandies-1;
        while (alicePointer <= bobPointer) {
            if (prevAlice == 0) {
                while (prevAlice <= prevBob && alicePointer <= bobPointer) {
                    prevAlice += candySizes[alicePointer++];
                }
                totalAlice += prevAlice;
                prevBob = 0;
                moves += 1;
            }
            else {
                while (prevBob <= prevAlice && alicePointer <= bobPointer) {
                    prevBob += candySizes[bobPointer--];
                }
                totalBob += prevBob;
                prevAlice = 0;
                moves += 1;
            }
        }
        
        return new Solution(moves, totalAlice, totalBob);
    }

    // Prints output corresponding to condition
    static void print(Solution solution) {
        System.out.println(solution.moves + " " + solution.totalAlice + " " + solution.totalBob);
    }
}

class Solution {
    int moves, totalAlice, totalBob;
    public Solution(int moves, int totalAlice, int totalBob) {
        this.moves = moves;
        this.totalAlice = totalAlice;
        this.totalBob = totalBob;
    }
}