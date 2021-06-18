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
      int red = in.nextInt();
      int blue = in.nextInt();
      int first = in.nextInt();
      int second = in.nextInt();

      int maxGifts = getMaxGifts(red, blue, first, second);
      out.println(maxGifts);
    }

    in.close();
    out.close();
  }

  static int getMaxGifts(int red, int blue, int first, int second) {
    int greaterAvailable = Math.max(red, blue);
    int lesserAvailable = Math.min(red, blue);
    int availableDiff = greaterAvailable - lesserAvailable;

    int greaterReduced = Math.max(first, second);
    int lesserReduced = Math.min(first, second);
    int reducedDiff = greaterReduced - lesserReduced;

    if (reducedDiff == 0) {
      return lesserAvailable / lesserReduced;
    }

    int tryGifts = availableDiff / reducedDiff;
    int maxGifts = Math.min(tryGifts, Math.min(greaterAvailable / greaterReduced, lesserAvailable / lesserReduced));
    greaterAvailable -= maxGifts * greaterReduced;
    lesserAvailable -= maxGifts * lesserReduced;

    int tryGiftPairs = lesserAvailable / (first + second);
    maxGifts += tryGiftPairs<<1;
    greaterAvailable -= (first + second) * tryGiftPairs;
    lesserAvailable -= (first + second) * tryGiftPairs;
    // out.println(String.format("%d %d %d", greaterAvailable, lesserAvailable, maxGifts));
    return maxGifts + remainingGifts(greaterAvailable, lesserAvailable, greaterReduced, lesserReduced);
  }

  static int remainingGifts(int greaterAvailable, int lesserAvailable, int greaterReduced, int lesserReduced) {
    if (greaterAvailable < greaterReduced || lesserAvailable < lesserReduced) {
      return 0;
    }
    int available1 = greaterAvailable - greaterReduced;
    int available2 = lesserAvailable - lesserReduced;
    return 1 + remainingGifts(available2, available1, greaterReduced, lesserReduced);
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