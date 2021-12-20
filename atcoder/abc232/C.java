// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.Point;

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
                                ), 1<<28).start();
  }

  static boolean isomorphic = false;
  public void run() {
    int n = in.nextInt();
    int m = in.nextInt();
    int[][] achords = new int[m][2];
    for (int i = 0; i < m; i++) {
      achords[i][0] = in.nextInt()-1;
      achords[i][1] = in.nextInt()-1;
    }
    int[][] bchords = new int[m][2];
    for (int i = 0; i <  m; i++) {
      bchords[i][0] = in.nextInt()-1;
      bchords[i][1] = in.nextInt()-1;
    }

    checkIsomorphism(n, achords, bchords);
    out.println(isomorphic ? "Yes" : "No");
    
    in.close();
    out.close();
  }

  private static void checkIsomorphism(int n, int[][] achords, int[][] bchords) {
    Set<Integer> achordSet = new HashSet<Integer>();
    for (var chord : achords) {
      achordSet.add(chord[0]*10 + chord[1]);
      achordSet.add(chord[1]*10 + chord[0]);
    }

    List<Integer> perm = new ArrayList<Integer>(n);
    checkIsomorphism(n, achordSet, bchords, perm);
  }

  private static void checkIsomorphism(int n, Set<Integer> achordSet, int[][] bchords, List<Integer> perm) {
    if (perm.size() == n) {
      for (var chord : bchords) {
        int encrypted = perm.get(chord[0]) * 10 + perm.get(chord[1]);
        if (!achordSet.contains(encrypted)) {
          return;
        }
      }
      isomorphic = true;
    } else {
      for (int i = 0; i < n; i++) {
        if (!perm.contains(i)) {
          perm.add(i);
          checkIsomorphism(n, achordSet, bchords, perm);
          perm.remove(perm.size()-1);
        }
      }
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
