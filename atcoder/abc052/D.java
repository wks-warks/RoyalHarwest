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
      int towns = FR.nextInt();
      int perUnitCost = FR.nextInt();
      int teleportationCost = FR.nextInt();
      int[] townCoordinates = new int[towns];
      for (int i = 0; i < towns; i += 1) {
        townCoordinates[i] = FR.nextInt();
      }
      long fatigue = getFatigue(townCoordinates, perUnitCost, teleportationCost);
      solution.append(fatigue + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static long getFatigue(int[] townCoordinates, int perUnitCost, int teleportationCost) {
    long fatigue = 0L;
    int prev = townCoordinates[0];
    for (var coordinate : townCoordinates) {
      int distance = coordinate - prev;
      fatigue += Math.min((long)distance * perUnitCost, teleportationCost);
      prev = coordinate;
    }
    return fatigue;
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