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
    int points = in.nextInt();
    Set<Integer> positions = new HashSet<Integer>();
    for (int p = 0; p < points; p++) {
      positions.add(in.nextInt());
    }

    List<Integer> best = getBest(positions);
    out.println(best.size());
    for (var num : best) {
      out.print(num + " ");
    }
    
    in.close();
    out.close();
  }

  private static List<Integer> getBest(Set<Integer> positions) {
    int[] pows = new int[31];
    for (int i = 0; i < pows.length; i++) {
      pows[i] = 1<<i;
    }
    
    List<Integer> best = null;
    for (var val : positions) {
      for (int diff : pows) {
        if (positions.contains(val - diff) && positions.contains(val + diff)) {
          best = new ArrayList<Integer>(3);
          best.add(val - diff);
          best.add(val);
          best.add(val + diff);
          return best;
        } else if (positions.contains(val - diff)) {
          best = new ArrayList<Integer>(2);
          best.add(val - diff);
          best.add(val);
        } else if (positions.contains(val + diff)) {
          best = new ArrayList<Integer>(2);
          best.add(val);
          best.add(val + diff);
        }
      }
    }

    if (best != null) {
      return best;
    }
    for (var pos : positions) {
      best = new ArrayList<Integer>(1);
      best.add(pos);
      return best;
    }
    return null;
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
