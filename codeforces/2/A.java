import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int rounds = scanner.nextInt();
    Map.Entry<String, Integer>[] results = new Map.Entry[rounds];
    
    for (int r = 0; r < rounds; r++) {
      String name = scanner.next();
      int points = scanner.nextInt();
      results[r] = new SimpleEntry<String, Integer>(name, points);
    }

    String winner = getWinner(results);
    System.out.println(winner);
  }

  static String getWinner(Map.Entry<String, Integer>[] results) {
    Map<String, Integer> playerPoints = new HashMap<String, Integer>();
    for (int r = 0; r < results.length; r++) {
      String player = results[r].getKey();
      int pointsUpdate = results[r].getValue();
      
      if (playerPoints.containsKey(player)) {
        playerPoints.put(player, playerPoints.get(player) + pointsUpdate);
      } else {
        playerPoints.put(player, pointsUpdate);
      }
    }

    int maxPoints = Integer.MIN_VALUE;
    for (var entry : playerPoints.entrySet()) {
      maxPoints = Math.max(maxPoints, entry.getValue());
    }

    Set<String> candidates = new HashSet<String>();
    for (var entry : playerPoints.entrySet()) {
      if (entry.getValue() == maxPoints) {
        candidates.add(entry.getKey());
      }
    }

    Queue<String> orderedCandidates = new LinkedList<String>();
    playerPoints = new HashMap<String, Integer>();
    for (int r = 0; r < results.length; r++) {
      String player = results[r].getKey();
      int pointsUpdate = results[r].getValue();

      int newPoints = (playerPoints.containsKey(player) ? playerPoints.get(player) + pointsUpdate : pointsUpdate);
      playerPoints.put(player, newPoints);

      if (newPoints >= maxPoints) {
        orderedCandidates.add(player);
      }
    }

    String winner = "";
    while (!candidates.contains(winner = orderedCandidates.poll()));
    return winner;
  }
}