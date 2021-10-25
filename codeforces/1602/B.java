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
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int n = in.nextInt();

      int[] a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = in.nextInt();
      }

      List<List<Integer>> stages = getStages(a);

      int queries = in.nextInt();
      for (int q = 0; q < queries; q++) {
        int idx = in.nextInt()-1;
        int steps = Math.min(in.nextInt(), stages.size()-1);
        out.println(stages.get(steps).get(idx));
      }

    }
    
    in.close();
    out.close();
  }

  static List<List<Integer>> getStages(int[] a) {
    List<List<Integer>> stages = new ArrayList<List<Integer>>();
    stages.add(new ArrayList<Integer>(a.length));
    for (var num : a) {
      stages.get(0).add(num);
    }

    while (isUnresolved(a)) {
      stages.add(new ArrayList<Integer>(a.length));
      Map<Integer, Integer> freq = getFreq(a);
      for (int i = 0; i < a.length; i++) {
        a[i] = freq.get(a[i]);
        stages.get(stages.size()-1).add(a[i]);
      }
    }

    // for (var list : stages) {
    //   for (var num : list) {
    //     out.print(num + " ");
    //   }
    //   out.println();
    // }

    return stages;
  }

  static boolean isUnresolved(int[] a) {
    Map<Integer, Integer> freq = getFreq(a);
    for (var entry : freq.entrySet()) {
      if (!entry.getKey().equals(entry.getValue())) {
        return true;
      }
    }
    return false;
  }

  static Map<Integer, Integer> getFreq(int[] a) {
    Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
    for (var num : a) {
      Integer prev = freq.get(num);
      if (prev == null) {
        freq.put(num, 1);
      } else {
        freq.put(num, prev + 1);
      }
    }
    return freq;
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
