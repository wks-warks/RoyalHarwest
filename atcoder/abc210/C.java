// Author : RegalBeast

import java.io.*;
import java.awt.*;
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
    int candies = in.nextInt();
    int toChoose = in.nextInt();
    int[] colors = new int[candies];
    for (int i = 0; i < candies; i++) {
      colors[i] = in.nextInt();
    }

    int distinctAchievable = getDistinctAchievable(candies, toChoose, colors);
    out.println(distinctAchievable);

    in.close();
    out.close();
  }

  static int getDistinctAchievable(int candies, int toChoose, int[] colors) {
    Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
    for (int i = 0; i < toChoose; i++) {
      addToMap(freq, colors[i]);
    }
    int distinctAchievable = freq.size();

    for (int i = toChoose; i < candies; i++) {
      removeFromMap(freq, colors[i-toChoose]);
      addToMap(freq, colors[i]);
      distinctAchievable = Math.max(distinctAchievable, freq.size());
    }
    return distinctAchievable;
  }

  static void addToMap(Map<Integer, Integer> map, int num) {
    if (map.containsKey(num)) {
      map.put(num, map.get(num) + 1);
    } else {
      map.put(num, 1);
    }
  }

  static void removeFromMap(Map<Integer, Integer> map, int num) {
    if (map.get(num) == 1) {
      map.remove(num);
    } else {
      map.put(num, map.get(num) - 1);
    }
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
    if (st != null && st.hasMoreElements()) {
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