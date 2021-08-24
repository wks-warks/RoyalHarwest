// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), "King", 1<<25).start();
  }

  public void run() {
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int permLen = in.nextInt();
      int[] perm = new int[permLen];
      for (int i = 0; i < permLen; i++) {
        perm[i] = in.nextInt();
      }

      int iterationsToSort = getIterationsToSort(perm, 0);
      out.println(iterationsToSort);
    }

    in.close();
    out.close();
  }

  static int getIterationsToSort(int[] perm, int iter) {
    if (isSorted(perm)) {
      return iter;
    }

    tourSort(perm, iter & 1);
    return getIterationsToSort(perm, iter+1);
  }

  static void tourSort(int[] arr, int start) {
    for (int i = start; i + 1 < arr.length; i += 2) {
      if (arr[i] > arr[i+1]) {
        arr[i] ^= arr[i+1];
        arr[i+1] ^= arr[i];
        arr[i] ^= arr[i+1];
      }
    }
  }

  static boolean isSorted(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] - i != 1) {
        return false;
      }
    }
    return true;
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
