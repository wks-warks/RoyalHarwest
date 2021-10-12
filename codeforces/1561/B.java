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
      int aw = in.nextInt();
      int bw = in.nextInt();
      
      int[] breaks = getBreaks(aw, bw);
      out.println(breaks.length);
      for (var val : breaks) {
        out.print(val + " ");
      }
      out.println();
    }

    in.close();
    out.close();
  }

  static int[] getBreaks(int aw, int bw) {
    Set<Integer> possible = new TreeSet<Integer>();
    int games = aw + bw;
    Set<Integer> borysServes = new HashSet<Integer>();
    borysServes.add(games>>1);
    borysServes.add((games+1)>>1);
    
    for (var bs : borysServes) {
      int as = games - bs;
      for (int bwa = 0; bwa <= bw; bwa++) {
        if (bwa > as) {
          break;
        }
        int bwb = bw - bwa;
        int awa = as - bwa;
        if (awa > aw) {
          continue;
        }
        int awb = aw - awa;
        if (bwb + awb <= bs && awa + bwa <= as) {
          possible.add(bs - bw + (bwa<<1));
        }
      }
    }

    int[] breaks = new int[possible.size()];
    int i = 0;
    for (var count : possible) {
      breaks[i++] = count;
    }
    return breaks;
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
