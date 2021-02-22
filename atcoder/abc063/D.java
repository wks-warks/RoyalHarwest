// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int monsters = FR.nextInt();
    int coreDamage = FR.nextInt();
    int AOEDamage = FR.nextInt();
    List<Integer> monsterHealth = new ArrayList<Integer>(monsters);
    for (int m = 0; m < monsters; m += 1) {
      monsterHealth.add(FR.nextInt());
    }
    int explosions = getExplosionCount(monsterHealth, coreDamage, AOEDamage);
    solution.append(explosions + "\n");
		PW.print(solution.toString());
    PW.close();
  }

  static int getExplosionCount(List<Integer> monsterHealth, int coreDamage, int AOEDamage) {
    Collections.sort(monsterHealth);
    int left = 1;
    int right = LIG(monsterHealth.get(monsterHealth.size()-1), AOEDamage);
    int answer = -1;
    while (left <= right) {
      int mid = (left + right) >> 1;
      if (achievable(mid, monsterHealth, coreDamage, AOEDamage)) {
        answer = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return answer;
  }

  static boolean achievable(int explosions, List<Integer> monsterHealth, int coreDamage, int AOEDamage) {
    int left = 0;
    int right = monsterHealth.size() - 1;
    int extras = -1;
    int extraDamage = Math.min(explosions * AOEDamage, Integer.MAX_VALUE);
    while (left <= right) {
      int mid = (left + right) >> 1;
      if (monsterHealth.get(mid) <= extraDamage) {
        extras = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    long actualCount = 0;
    for (int i = extras + 1; i < monsterHealth.size(); i += 1) {

      actualCount += LIG(monsterHealth.get(i) - extraDamage, coreDamage - AOEDamage);
    }
    return actualCount <= explosions;
  }


  static int LIG(int numerator, int denominator) {
    return (numerator + denominator - 1) / denominator;
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