// Author : RegalBeast

import java.io.*;
import java.util.*;

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
    int sellers = in.nextInt();
    int money = in.nextInt();
    
    int[][] itemPrices = new int[sellers][];
    for (int s = 0; s < sellers; s++) {
      int items = in.nextInt();
      itemPrices[s] = new int[items];

      for (int i = 0; i < items; i++) {
        itemPrices[s][i] = in.nextInt();
      }
    }

    List<Integer> approachableSellers = getApproachableSellers(itemPrices, money);
    out.println(approachableSellers.size());
    for (var seller : approachableSellers) {
      out.print(seller + " ");
    }
    out.println();

    in.close();
    out.close();
  }

  static List<Integer> getApproachableSellers(int[][] itemPrices, int money) {
    List<Integer> approachableSellers = new ArrayList<Integer>();

    for (int s = 0; s < itemPrices.length; s++) {
      int cheapest = Integer.MAX_VALUE; 
      for (var cost : itemPrices[s]) {
        cheapest = Math.min(cheapest, cost);
      }

      if (cheapest < money) {
        approachableSellers.add(s+1);
      }
    }

    return approachableSellers;
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