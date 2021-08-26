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
      in.nextInt();
      String str = in.next();
      int[] ans = getAns(str);
      for (var idx : ans) {
        out.print(idx + " ");
      }
      out.println();
    }

    in.close();
    out.close();
  }

  /*
    MY LOGIC: if all 1s, then choose 1, len-1 and 2, len. they work, for k=1

    if NOT all 1s, at least 1 zero exists.
    if even digits: zero sits in either half
      if zero in first half at idx
        choose idx, len and idx+1, len
        both would be >= half in length and would work for k = 1
      similarly for second half, choose 1, idx and 1, idx-1, would work for k=2
    if odd digits, zero in either half works the same
    else, zero in middle. in this case, 1, mid-1 and 1, mid works for k=1

    ORDERING MATTERS!
  */
  static int[] getAns(String str) {
    if (allOne(str)) {
      return new int[]{1, str.length()-1, 2, str.length()};
    }

    int mid = str.length()>>1;
    // 8: 4 0123 4567
    // 7: 3 012 3 456

    for (int i = 0; i < mid; i++) {
      if (str.charAt(i) == '0') {
        return new int[]{i+1, str.length(), i+2, str.length()};
      }
      if (str.charAt(str.length()-1-i) == '0') {
        return new int[]{1, str.length()-i, 1, str.length()-i-1};
      }
    }

    return new int[]{1, mid+1, 1, mid};
  }

  static boolean allOne(String str) {
    for (var ch : str.toCharArray()) {
      if (ch == '0') {
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
