import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));
 
  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    // tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int chemicalCount = FR.nextInt();
      int aRatio = FR.nextInt();
      int bRatio = FR.nextInt();
      int[][] itemAndCost = new int[chemicalCount][3]; // 0: aQty, 1: bQty, 2: cost
      for (int i = 0; i < chemicalCount; ++i) {
        for (int j = 0; j < 3; ++j) {
          itemAndCost[i][j] = FR.nextInt();
        }
      }
      int moneyRequired = getMoneyRequired(itemAndCost, aRatio, bRatio);
      solution.append(moneyRequired + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getMoneyRequired(int[][] itemAndCost, int aRatio, int bRatio) {
    int[][] dp = new int[itemAndCost.length*10+1][itemAndCost.length*10+1]; // dp[aQty][bQty] = cost
    for (int i = 0; i < dp.length; ++i) {
      for (int j = 0; j < dp.length; ++j) {
        dp[i][j] = Integer.MAX_VALUE;        
      }
    }
    dp[0][0] = 0;

    for (var item : itemAndCost) {
      int aQty = item[0];
      int bQty = item[1];
      int cost = item[2];
      for (int i = dp.length-aQty-1; i >= 0; i -= 1) {
        for (int j = dp.length-bQty-1; j >= 0; j -= 1) {
          if (dp[i][j] != Integer.MAX_VALUE) {
            dp[i+aQty][j+bQty] = Math.min(dp[i+aQty][j+bQty], dp[i][j] + cost);
          }
        }
      }
    }
    
    int answer = Integer.MAX_VALUE;
    for (int a = aRatio, b = bRatio; a < dp.length && b < dp.length; a += aRatio, b += bRatio) {
      answer = Math.min(answer, dp[a][b]);
    }
    return answer == Integer.MAX_VALUE ? -1 : answer;
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