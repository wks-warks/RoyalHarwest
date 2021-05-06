import java.io.*;
import java.util.*;

public class Main implements Runnable {
  static Input in = new Input();
  static Output out = new Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), "RegalBeast", 1<<16).start();
  }

  public void run() {
    int numLen = in.nextInt();
    int changes = in.nextInt();
    String numStr = in.next();

    String ans = getAns(changes, numStr);
    out.println(ans);

    in.close();
    out.close();
  }

  static String getAns(int changes, String numStr) {
    if (changes == 0) {
      return numStr;
    }
    if (numStr.length() == 1) {
      return "0";
    }

    StringBuilder ans = new StringBuilder();
    if (numStr.charAt(0) != '1') {
      changes -= 1;
    }
    ans.append('1');

    for (int i = 1; i < numStr.length(); i++) {
      if (changes > 0 && numStr.charAt(i) != '0') {
        changes -= 1;
        ans.append('0');
      } else {
        ans.append(numStr.charAt(i));
      }
    }

    return ans.toString();
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
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public String next() {
    while (st == null || !st.hasMoreElements()) {
      try {
        st = new StringTokenizer(br.readLine());
      } catch (Exception ex) {
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
    if (st.hasMoreElements()) {
      StringBuilder sb = new StringBuilder();
      while (st.hasMoreElements()) {
        sb.append(next());
      }
      return sb.toString();
    }

    String str = null;
    try {
      str = br.readLine();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return str;
  }

  public void close() {
    try {
      br.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

class Output {
  PrintWriter pw;
  public Output() {
    pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  }

  public Output(String fileName) {
    try {
      pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void print(String s) {
    pw.print(s);
  }

  public void println(String s) {
    pw.println(s);
  }

  public void close() {
    pw.close();
  }
}