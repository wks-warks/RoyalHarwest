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
    int potions = in.nextInt();
    int[] boosts = new int[potions];
    for (int p = 0; p < potions; p++) {
      boosts[p] = in.nextInt();
    }

    int maxPotions = getMaxPotions(potions, boosts);
    out.println(maxPotions);
    in.close();
    out.close();
  }

  static int getMaxPotions(int potions, int[] boosts) {
    SortedMap<Integer, Integer> chosen = new TreeMap<Integer, Integer>();
    long bufferSum = 0;

    for (var boost : boosts) {
      if (bufferSum + boost >= 0) {
        bufferSum += boost;
        increment(chosen, boost);
      } else if (chosen.size() > 0 && boost > chosen.firstKey()) {
        bufferSum += replace(chosen, boost);
      }
    }
    
    int maxPotions = 0;
    for (var value : chosen.values()) {
      maxPotions += value;
    }
    return maxPotions;
  }

  static void increment(SortedMap<Integer, Integer> chosen, int value) {
    if (chosen.containsKey(value)) {
      chosen.put(value, chosen.get(value) + 1);
    } else {
      chosen.put(value, 1);
    }
  }

  static void decrement(SortedMap<Integer, Integer> chosen, int value) {
    if (chosen.get(value) == 1) {
      chosen.remove(value);
    } else {
      chosen.put(value, chosen.get(value) - 1);
    }
  }
  
  static int replace(SortedMap<Integer, Integer> chosen, int value) {
    int prevMin = chosen.firstKey();
    decrement(chosen, prevMin);
    increment(chosen, value);
    return value - prevMin;
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