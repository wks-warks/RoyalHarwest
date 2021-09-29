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

      List<List<Integer>> shifts = getShifts(a);
      out.println(shifts.size());
      for (var shift : shifts) {
        out.println(shift.get(0) + " " + shift.get(1) + " " + shift.get(2));
      }
    }

    in.close();
    out.close();
  }

  static List<List<Integer>> getShifts(int[] a) {
    TreeMap<Integer, Integer> elements = new TreeMap<Integer, Integer>();
    for (var num : a) {
      if (elements.containsKey(num)) {
        elements.put(num, elements.get(num)+1);
      } else {
        elements.put(num, 1);
      }
    }

    List<List<Integer>> shifts = new ArrayList<List<Integer>>();
    int pos = 0;
    while (elements.size() > 0) {
      int firstKey = elements.firstKey();
      if (elements.get(firstKey) == 1) {
        elements.remove(firstKey);
      } else {
        elements.put(firstKey, elements.get(firstKey)-1);
      }

      int currentPos = findPos(a, pos, firstKey);
      if (currentPos != pos) {
        int d = currentPos - pos;
        List<Integer> shift = new ArrayList<>(List.of(pos+1, a.length, d));
        shifts.add(shift);
        performShift(a, pos, d);
      }
      pos++;
    }

    return shifts;
  }

  static void performShift(int[] a, int from, int d) {
    int[] newValues = new int[a.length - from];
    for (int i = from; i < a.length; i++) {
      int prevIdx = i + d;
      if (i + d >= a.length) {
        prevIdx = prevIdx - a.length + from;
      }

      newValues[i-from] = a[prevIdx];
    }

    for (int i = 0; i < newValues.length; i++) {
      a[from+i] = newValues[i];
    }
  }

  static int findPos(int[] a, int from, int value) {
    int pos = from;
    while (a[pos] != value) {
      pos++;
    }
    return pos;
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