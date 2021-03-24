import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Collections;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(br.readLine());

    StringTokenizer st;
    StringBuilder output = new StringBuilder();
    for (int t = 0; t < tests; t += 1) {
      int playerCount = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());

      List<Integer> playerTokens = new ArrayList<Integer>(playerCount);
      for (int e = 0; e < playerCount; e += 1) {
        playerTokens.add(Integer.parseInt(st.nextToken()));
      }

      setProbableWinners(playerTokens);

      output.append(winners.size() + "\n");
      for (var winnerIdx : winners) {
        output.append(winnerIdx + " ");
      }
      output.append("\n");
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static Set<Integer> winners;
  static void setProbableWinners(List<Integer> playerTokens) {
    winners = new TreeSet<Integer>();

    Map<Integer, List<Integer>> tokenFrequency = getTokenFrequency(playerTokens);
  
    long totalSum = getSum(playerTokens);
    long prevSum = 0;
    int prevCount = -1;

    for (var entry : tokenFrequency.entrySet()) {
      int tokenCount = entry.getKey();
      List<Integer> indices = entry.getValue();
      long sumHenceForth = totalSum - prevSum;

      if (sumHenceForth < prevCount) {
        break;
      } else {
        for (var idx : indices) {
          winners.add(idx);
        }
      }

      prevCount = tokenCount;
      prevSum += (long) indices.size() * tokenCount;
    }
  }

  static Map<Integer, List<Integer>> getTokenFrequency(List<Integer> playerTokens) {
    Map<Integer, List<Integer>> tokenFrequency = new TreeMap<Integer, List<Integer>>(Collections.reverseOrder());
    
    for (int i = 0; i < playerTokens.size(); i += 1) {
      int tokens = playerTokens.get(i);
      tokenFrequency.putIfAbsent(tokens, new ArrayList<Integer>());
      tokenFrequency.get(tokens).add(i+1);
    }

    return tokenFrequency;
  }

  static long getSum(List<Integer> list) {
    long sum = 0;

    for (var element : list) {
      sum += element;
    }

    return sum;
  }
}