// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), String.join(
                                  "All that is gold does not glitter,",
                                  "Not all those who wander are lost;",
                                  "The old that is strong does not wither,",
                                  "Deep roots are not reached by the frost.",
                                  
                                  "From the ashes a fire shall be woken,",
                                  "A light from the shadows shall spring;",
                                  "Renewed shall be blade that was broken,",
                                  "The crownless again shall be king."
                                ), 1<<25).start();
  }

  public void run() {
    int stormtroopers = in.nextInt();
    int x0 = in.nextInt();
    int y0 = in.nextInt();

    int[][] posArr = new int[stormtroopers][2];
    for (int s = 0; s < stormtroopers; s++) {
      posArr[s][0] = in.nextInt();
      posArr[s][1] = in.nextInt();
    }

    int shotsNeeded = countShotsNeeded(x0, y0, posArr);
    out.println(shotsNeeded);
    in.close();
    out.close();
  }

  // static int countShotsNeeded(int x0, int y0, int[][] posArr) {
  //   Set<Double> slopes = new HashSet<Double>();
  //   for (var point : posArr) {
  //     slopes.add(Math.abs((point[1] - y0) / (double)(point[0] - x0)));
  //   }
  //   return slopes.size();
  // }

  static int countShotsNeeded(int x0, int y0, int[][] posArr) {
    Set<Pair<Integer, Integer>> slopes = new HashSet<Pair<Integer, Integer>>();
    for (var pos : posArr) {
      int yDiff = pos[1] - y0;
      int xDiff = pos[0] - x0;

      int g = gcd(yDiff, xDiff);
      yDiff /= g;
      xDiff /= g;
      slopes.add(new Pair<Integer, Integer>(yDiff, xDiff));
    }

    return slopes.size();
  }

  static int gcd(int num1, int num2) {
    if (num2 == 0) {
      return num1;
    }
    return gcd(num2, num1 % num2);
  }

  static PrintWriter Output() {
    return new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  }
  
  static PrintWriter Output(String fileName) {
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return pw;
  }
}

class Input {
  BufferedReader br;
  StringTokenizer st;
  public Input() {
    br = new BufferedReader(new InputStreamReader(System.in));
  }

  public Input(String fileName) {
    try {
      br = new BufferedReader(new FileReader(fileName));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public String next() {
    while (st == null || !st.hasMoreElements()) {
      try {
        st = new StringTokenizer(br.readLine());
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return st.nextToken();
  }

  public int nextInt() {
    return Integer.parseInt(next());
  }

  public long nextLong() {
    return Long.parseLong(next());
  }

  public Float nextFloat() {
    return Float.parseFloat(next());
  }

  public Double nextDouble() {
    return Double.parseDouble(next());
  }

  public String nextLine() {
    if (st.hasMoreElements()) {
      StringBuilder sb = new StringBuilder();
      while (st.hasMoreElements()) {
        sb.append(next());
      }
      return sb.toString();
    }

    String str = null;
    try {
      str = br.readLine();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return str;
  }

  public void close() {
    try {
      br.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}

class Pair<T1, T2> {
  private T1 first;
  private T2 second;
  public Pair(T1 first, T2 second) {
    setFirst(first);
    setSecond(second);
  }

  public T1 getFirst() {
    return first;
  }
  public T2 getSecond() {
    return second;
  }

  public void setFirst(T1 first) {
    this.first = first;
  }
  public void setSecond(T2 second) {
    this.second = second;
  }

  @Override
  public int hashCode() {
    final int prime = 7_243;
    int hash = first.hashCode() % prime;
    hash = (int) ((long) hash * second.hashCode() % prime);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Pair) {
      Pair<?, ?> other = (Pair<?, ?>) obj;
      return first.equals(other.getFirst()) && second.equals(other.getSecond());
    }
    return false;
  }
}
