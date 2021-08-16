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
      int squares = in.nextInt();
      String colours = in.next();
      String output = getOutput(colours);
      out.println(output);
    }

    in.close();
    out.close();
  }

  static String getOutput(String colours) {
    StringBuilder output = new StringBuilder();

    char nextCh = getStartCh(colours);
    for (var ch : colours.toCharArray()) {
      switch(ch) {
        case 'B' :
          output.append('B');
          nextCh = 'R';
          break;
        case 'R':
          output.append('R');
          nextCh = 'B';
          break;
        default:
          output.append(nextCh);
          nextCh = (char) ('R' + 'B' - nextCh);
      }
    }

    return output.toString();
  }

  static char getStartCh(String str) {
    int qs = 0;
    while (qs < str.length() && str.charAt(qs) == '?') {
      qs++;
    }
    if (qs == str.length()) {
      return 'B';
    } else {
      boolean even = (qs & 1) == 0;
      return even ? str.charAt(qs) : ((char) ('R' + 'B' - str.charAt(qs)));
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
