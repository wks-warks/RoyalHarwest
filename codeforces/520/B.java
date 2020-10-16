// Codeforces 520B
import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

public class CF520B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int init = SC.nextInt();
        int target = SC.nextInt();
        int movesRequired = computeMovesRequired(init, target);
        System.out.println(movesRequired);
    }

    // Computes and returns minimum number of moves required to reach the target number from the initial number
    static int computeMovesRequired(int init, int target) {
        int[] movesToPoint = new int[10001]; // Stores number of moves required to reach any point from initial point
        boolean[] visited = new boolean[10001]; // Notes whether a given point has been visited earlier
        Deque<PointMovesPair> pointsInQueue = new LinkedList<PointMovesPair>();
        pointsInQueue.add(new PointMovesPair(init, 0));
        visited[init] = true;
        while (pointsInQueue.size() > 0) {
            PointMovesPair pointMoves = pointsInQueue.pop();
            int point = pointMoves.point;
            int moves = pointMoves.moves;
            PointMovesPair next1 = new PointMovesPair(point-1, moves+1);
            PointMovesPair next2 = new PointMovesPair(point*2, moves+1);
            if (next1.point > 0 && next1.point <= 10000)
                if (!visited[next1.point]){
                    pointsInQueue.add(next1);
                    movesToPoint[next1.point] = next1.moves;
                    visited[next1.point] = true;
                }
            if (next2.point > 0 && next2.point <= 10000)
                if (!visited[next2.point]) {
                    pointsInQueue.add(next2);
                    movesToPoint[next2.point] = next2.moves;
                    visited[next2.point] = true;
                }
        }
        return movesToPoint[target];
    }

}

class PointMovesPair {
    int point;
    int moves;
    public PointMovesPair(int point, int moves) {
        this.point = point;
        this.moves = moves;
    }
}
