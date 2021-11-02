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
    long num = in.nextLong();
    int minSteps = getMinSteps(num);
    out.println(minSteps);
    
    in.close();
    out.close();
  }

  private static int getMinSteps(long num) {
    String str = Long.toString(num);
    int minSteps = Math.min(
      getSteps(str, "25"),
      Math.min(
        getSteps(str, "50"),
        Math.min(
          getSteps(str, "75"),
          getSteps(str, "00")
        )
      )
    );
    
    return minSteps == Integer.MAX_VALUE ? -1 : minSteps;
  }

  private static int getSteps(String string, String endString) {
    String str = string;
    if (str.length() == 1) {
      return Integer.MAX_VALUE;
    }

    int terminalLastPos = getLastPos(str, endString.charAt(1));
    if (terminalLastPos == -1) {
      return Integer.MAX_VALUE;
    }

    int steps = str.length() - terminalLastPos - 1;
    for (int i = 1; i < string.length(); i++) {
      if (str.charAt(i) == '0' && terminalLastPos == 0) {
        steps++;
      }
    }
    // if (terminalLastPos == 0 && str.charAt(1) == '0') {
      // steps++;
      // for (int i = 1; i < )
    // }
    str = str.substring(0, terminalLastPos) + (terminalLastPos != str.length()-1 ? str.substring(terminalLastPos+1) : "") + Character.toString(endString.charAt(1));
    int subterminalLastPos = getLastPos(str.substring(0, str.length()-1), endString.charAt(0));
    if (subterminalLastPos == -1) {
      return Integer.MAX_VALUE;
    }

    int addition = str.length() - subterminalLastPos - 2;
    if (subterminalLastPos == 0 && str.charAt(1) == '0' && str.length() > 2) {
      addition++;
    }
    // out.println("SS" + steps +  " " + string + " " +  endString + " " + addition);
    return steps + addition;
  }

  private static int getLastPos(String str, char ch) {
    int idx = -1;
    for (int i = 0; i < str.length(); i++) {
      if (str.charAt(i) == ch) {
        idx = i;
      }
    }
    return idx;
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
