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
    int sequenceCount = in.nextInt();
    Sequence[] sequences = new Sequence[sequenceCount];
    for (int s = 0; s < sequenceCount; s++) {
      sequences[s] = readSequence(s);
    }

    int desiredSum = Sequence.sameSum;
    if (desiredSum == Integer.MAX_VALUE) {
      out.println("NO");
    } else {
      out.println("YES");
      for (var idx : Sequence.sums.get(desiredSum)) {
        out.println(idx + " " + sequences[idx-1].getRemoved(desiredSum));
      }
    }
    
    in.close();
    out.close();
  }

  private static Sequence readSequence(int idx) {
    int size = in.nextInt();
    int[] arr = new int[size];
    for (int i = 0; i < size; i++) {
      arr[i] = in.nextInt();
    }

    return new Sequence(arr, idx);
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

class Sequence {
  static Map<Integer, List<Integer>> sums = new HashMap<Integer, List<Integer>>();
  int idx, sum;
  int[] arr;
  private Map<Integer, Integer> pos;
  static int sameSum = Integer.MAX_VALUE;

  Sequence (int[] arr, int idx) {
    if (sameSum != Integer.MAX_VALUE) {
      return;
    }
    this.idx = idx;
    this.arr = arr;
    pos = new HashMap<Integer, Integer>();

    sum = 0;
    for (var num : arr) {
      sum += num;
    }

    for (int i = 0; i < arr.length; i++) {
      pos.put(arr[i], i+1);
      int diff = sum - arr[i];
      if (!sums.containsKey(diff)) {
        sums.put(diff, new ArrayList<Integer>());
      }
      if (sums.get(diff).isEmpty() || sums.get(diff).get(sums.get(diff).size()-1) != idx+1) {
        sums.get(diff).add(idx+1);
      }
      if (sums.get(diff).size() > 1) {
        sameSum = diff;
      }
    }
  }

  int getRemoved(int desiredSum) {
    return pos.get(sum - desiredSum);
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
