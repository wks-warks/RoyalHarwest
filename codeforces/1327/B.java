import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(br.readLine());

    StringBuilder solution = new StringBuilder();
    while (tests-->0) {
      int daughters = Integer.parseInt(br.readLine());
      List<PriorityQueue<Integer>> preferences = new ArrayList<PriorityQueue<Integer>>(daughters);

      for (int d = 0; d < daughters; d++) {
        preferences.add(new PriorityQueue<Integer>());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int preferenceCount = Integer.parseInt(st.nextToken());
        for (int p = 0; p < preferenceCount; p++) {
          int kingdom = Integer.parseInt(st.nextToken());
          preferences.get(d).add(kingdom);
        }
      }

      Result result = getResult(preferences);
      solution.append(result.toString() + "\n");
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(solution.toString());
    pw.close();
  }

  static Result getResult(List<PriorityQueue<Integer>> preferences) {
    Set<Integer> takenPrinces = new HashSet<Integer>();
    
    boolean optimal = true;
    int daughterIdx = -1;
    int kingdomIdx = -1;
    for (int d = 0; d < preferences.size(); d++) {
      int choice = -1;
      while (preferences.get(d).size() > 0 && takenPrinces.contains(choice = preferences.get(d).poll()));

      if ((choice == -1 || takenPrinces.contains(choice)) && optimal) {
        optimal = false;
        daughterIdx = d+1;
      } else {
        takenPrinces.add(choice);
      }
    }

    if (optimal) {
      return new Result();
    } else {
      kingdomIdx = getKingdomIdx(takenPrinces);
      return new Result(daughterIdx, kingdomIdx);
    }
  }

  static int getKingdomIdx(Set<Integer> takenPrinces) {
    int kingdomIdx = 1;
    while (takenPrinces.contains(kingdomIdx)) {
      kingdomIdx++;
    }
    return kingdomIdx;
  }
}

class Result {
  boolean optimal;
  int daughterIdx, kingdomIdx; // 1-based

  public Result() {
    optimal = true;
  }
  
  public Result(int daughterIdx, int kingdomIdx) {
    optimal = false;
    this.daughterIdx = daughterIdx;
    this.kingdomIdx = kingdomIdx;
  }

  @Override
  public String toString() {
    return optimal ? "OPTIMAL" : String.format("IMPROVE\n%d %d", daughterIdx, kingdomIdx);
  }
}