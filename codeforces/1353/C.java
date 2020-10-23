// Codeforces 1353C
import java.util.Scanner;

public class CF1353C {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int boardSize = SC.nextInt();
            long movesToSolution = computeMovesToSolution(boardSize);
            System.out.println(movesToSolution);
        }
    }

    // Computes and returns number of moves required to move to desired solution state
    static long computeMovesToSolution(int boardSize) {
        int movesRequired = 0;
        /*
            Break the figure into frames
              2   2   2   2   2   
              2               2
              2   1   1   1   2
              2   1       1   2
              2   1   1   1   2
              2               2
              2   2   2   2   2
        
            Frame(i) shall have (2*i+1)*4-4 elements, each requiring i moves, i going from 1 to (n-1)/2
            Answer = Summation(8*i*i) over 1 to (n-1)/2 = 8 * (n-1)/2 * (n+1)/2 * (n) / 6 = (n-1)*n*(n+1)/3
        */
        return (long) (boardSize-1) * boardSize * (boardSize+1) / 3;
    }
}