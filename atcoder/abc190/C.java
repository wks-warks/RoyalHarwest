import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));
 
  
  static int[][] conditions;
  static int[][] personChoices;
  static Set<Integer> filledDishes;
  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    // tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int dishCount = FR.nextInt();
      int conditionCount = FR.nextInt();
      conditions = new int[conditionCount][2];
      for (int i = 0; i < conditionCount; ++i) {
        conditions[i][0] = FR.nextInt();
        conditions[i][1] = FR.nextInt();
      }
      int personCount = FR.nextInt();
      personChoices = new int[personCount][2];
      for (int i = 0; i < personCount; ++i) {
        personChoices[i][0] = FR.nextInt();
        personChoices[i][1] = FR.nextInt();
      }
      filledDishes = new HashSet<Integer>();
      int conditionsSatisfied  = getMaxSatisfaction(0);
      solution.append(conditionsSatisfied + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getMaxSatisfaction(int choiceToConsider) {
    if (choiceToConsider == personChoices.length) {
      int satisfaction = 0;
      for (var condition : conditions) {
        if (filledDishes.contains(condition[0]) && filledDishes.contains(condition[1])) satisfaction += 1;
      }
      return satisfaction;
    }
    int i = choiceToConsider;
    int maxSatisfaction = 0;
    boolean wentAhead = false;
    for (int j = 0; j < personChoices[i].length; j += 1) {
      if (filledDishes.contains(personChoices[i][j])) continue;
      wentAhead = true;
      filledDishes.add(personChoices[i][j]);
      maxSatisfaction = Math.max(maxSatisfaction, getMaxSatisfaction(i+1));
      filledDishes.remove(personChoices[i][j]);
    }

    return wentAhead ? maxSatisfaction : getMaxSatisfaction(i+1);
  }
 
  static class FastReader {
    BufferedReader br;
    StringTokenizer st;
  
    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }
  
    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException  e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }
  
    int nextInt() {
      return Integer.parseInt(next());
    }
  
    long nextLong() {
      return Long.parseLong(next());
    }
  
    double nextDouble() {
      return Double.parseDouble(next());
    }
 
    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e)  {
        e.printStackTrace();
      }
      return str;
    }
  }
}