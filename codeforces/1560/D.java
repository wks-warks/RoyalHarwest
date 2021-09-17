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

  static String[] powersOfTwo;
  public void run() {
    powersOfTwo = getPowersOfTwo();

    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      String num = in.next();

      int minMoves = getMinMoves(num);
      out.println(minMoves);
    }

    in.close();
    out.close();
  }

  static int getDistance(String first, String second) {
    // int leadingZeroes = 0;
    int prefixLength = 0;
    
    for (var ch : first.toCharArray()) {
      // if (prefixLength == 0 && ch == '0') {
        // leadingZeroes++;
      // }

      if (ch == second.charAt(prefixLength)) {
        prefixLength++;

        if (prefixLength == second.length()) {
          break;
        }
      }
    }

    int distance = second.length() - prefixLength + first.length() - (prefixLength /*+ leadingZeroes*/);
    // System.out.println(distance);
    return distance;
  }

  static int getMinMoves(String num) {
    int minMoves = Integer.MAX_VALUE;
    
    for (var pow : powersOfTwo) {
      minMoves = Math.min(minMoves, getDistance(num, pow));
    }

    return minMoves;
  }

  static String[] getPowersOfTwo() {
    String[] powersOfTwo = new String[60];

    for (int i = 0; i < 60; i++) {
      powersOfTwo[i] = Long.toString(1L << i);
    }

    return powersOfTwo;
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
