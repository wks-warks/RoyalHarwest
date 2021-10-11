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
    int rows = in.nextInt();
    int cols = in.nextInt();
    char prez = in.next().charAt(0);

    String[] room = new String[rows];
    for (int r = 0; r < rows; r++) {
      room[r] = in.next();
    }

    int deputies = countDeputies(room, prez);
    out.println(deputies);
    
    in.close();
    out.close();
  }

  static int countDeputies(String[] room, char prez) {
    Set<Character> connected = new HashSet<Character>();
    connected.add(prez);

    for (int r = 0; r < room.length; r++) {
      for (int c = 0; c < room[r].length(); c++) {
        if (room[r].charAt(c) != prez) {
          continue;
        }

        if (r > 0 && room[r-1].charAt(c) != '.') {
          connected.add(room[r-1].charAt(c));
        }
        if (r + 1 < room.length && room[r+1].charAt(c) != '.') {
          connected.add(room[r+1].charAt(c));
        }

        if (c > 0 && room[r].charAt(c-1) != '.') {
          connected.add(room[r].charAt(c-1));
        }
        if (c + 1 < room[r].length() && room[r].charAt(c+1) != '.') {
          connected.add(room[r].charAt(c+1));
        }
      }
    }

    return connected.size() - 1;
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
