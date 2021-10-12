//Codeforces 1176A 
import java.util.Scanner;
import java.util.HashMap;

public class CF1176A {
    static final Scanner SC = new Scanner(System.in);
/*    
    // DP Solution - TLE
    static HashMap<Long, Integer> numberAndMoves;
    public static void main(String[] args) {
        numberAndMoves = new HashMap<Long, Integer>();
        numberAndMoves.put(1L, 0);

        StringBuilder solution = new StringBuilder();
        int queries = SC.nextInt();
        for (int q = 0; q < queries; ++q) {
            long n = SC.nextLong();
            int requiredMoves = computeRequiredMoves(n);
            solution.append(requiredMoves + "\n");
        }
        System.out.print(solution.toString());
    }

    // Computes and returns moves required to get number to 1
    static int computeRequiredMoves(long n) {
        if (numberAndMoves.containsKey(n)) {
            return numberAndMoves.get(n);
        }
        int requiredMoves = Integer.MAX_VALUE;
        if (n % 2 == 0) {
            if (computeRequiredMoves(n/2) != -1)
                requiredMoves = 1 + computeRequiredMoves(n/2);
        }
        if (n % 3 == 0) {
            if (computeRequiredMoves(2*n/3) != -1)
                requiredMoves = Math.min(requiredMoves, 1 + computeRequiredMoves(2*n/3));
        }
        if (n % 5 == 0) {
            if (computeRequiredMoves(4*n/5) != -1)
                requiredMoves = Math.min(requiredMoves, 1 + computeRequiredMoves(4*n/5));
        }
        numberAndMoves.put(n, requiredMoves != Integer.MAX_VALUE ? requiredMoves : -1);
        return numberAndMoves.get(n);            
    } 
*/
    public static void main(String[] args) {
        int queries = SC.nextInt();
        for (int q = 0; q < queries; ++q) {
            long n = SC.nextLong();
            int requiredMoves = computeRequiredMoves(n);
            System.out.println(requiredMoves);
        }
    }

    // Computes and returns minimum number of moves to get number to 1
    static int computeRequiredMoves(long n) {
        int moves = 0;
        while (n % 5 == 0) {
            moves += 1;
            n *= 4;
            n /= 5;
        }
        while (n % 3 == 0) {
            moves += 1;
            n *= 2;
            n /= 3;
        }
        while (n % 2 == 0) {
            moves += 1;
            n /= 2;
        }
        if (n != 1)
            return -1;
        else
            return moves;
    }
}
