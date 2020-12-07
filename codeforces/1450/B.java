//Codeforces 1450 B 
import java.util.*;
 
public class CFB {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        int balls,attractPower, x, y, b, tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            balls = SC.nextInt();
            attractPower = SC.nextInt();
            Pair[] positions = new Pair[balls];
            for (b = 0; b < balls; ++b) {
                x = SC.nextInt();
                y = SC.nextInt();
                positions[b] = new Pair(x, y);
            }
            
            int minOperations = countMinOperations(attractPower, positions, balls);
            System.out.println(minOperations);
        }
    }
 
    // Computes and returns minimum number of operations required to get all balls at the same pos
    static int countMinOperations(int attractPower, Pair[] positions, int balls) {
        int[] distance = new int[balls];
        int minOperations = Integer.MAX_VALUE;
        for (int ballConsidered = 0; ballConsidered < balls; ++ballConsidered) {
            for (int i = 0; i < distance.length; ++i)
                distance[i] = Integer.MAX_VALUE;
            distance[ballConsidered] = 0;
            boolean[] isVisited = new boolean[balls];
            isVisited[ballConsidered] = true;
            Queue<Integer> nodeList = new PriorityQueue<Integer>();
            nodeList.add(ballConsidered);
            while (nodeList.size() > 0) {
                int idx = nodeList.poll();
                Pair presentPos = positions[idx];
                int presDistance = distance[idx];
                for (int i = 0; i < balls; ++i) {
                    if (isVisited[i])
                        continue;
                    if (Math.abs(positions[i].x - presentPos.x) + Math.abs(positions[i].y - presentPos.y) <= attractPower) {
                        distance[i] = presDistance + 1;
                        nodeList.add(i);
                        isVisited[i] = true;
                    }
                }
            }
            
            int maxDistance = -1;
            for (int i = 0; i < distance.length; ++i) {
                maxDistance = Math.max(maxDistance, distance[i]);
            }
            minOperations = maxDistance != Integer.MAX_VALUE ? Math.min(minOperations, maxDistance) : minOperations;
        }
        return minOperations == 1 ? minOperations : -1;
    }
}
 
class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
