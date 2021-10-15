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
    int tags = in.nextInt();
    int fruits = in.nextInt();
    
    int[] prices = new int[tags];
    for (int t = 0; t < tags; t++) {
      prices[t] = in.nextInt();
    }

    String[] types = new String[fruits];
    for (int f = 0; f < fruits; f++) {
      types[f] = in.next();
    }

    int min = getMin(prices, types);
    int max = getMax(prices, types);
    out.println(min + " " + max);
    
    in.close();
    out.close();
  }

  static int getMin(int[] prices, String[] fruits) {
    return getPrice(prices, fruits, -1);
  }

  static int getMax(int[] prices, String[] fruits) {
    return getPrice(prices, fruits, 1);
  }

  static int getPrice(int[] prices, String[] fruits, int order) {
    Map<String, Integer> freq = new HashMap<String, Integer>();
    for (var frt : fruits) {
      if (!freq.containsKey(frt)) {
        freq.put(frt, 1);
      } else {
        freq.put(frt, freq.get(frt)+1);
      }
    }

    Arrays.sort(prices);
    
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
    for (var me : freq.entrySet()) {
      pq.add(me.getValue());
    }

    int start = order == -1 ? 0 : prices.length-1;
    int inc = -order;
    int price = 0;
    for (int p = start; pq.size() > 0; p += inc) {
      int top = pq.poll();
      price += top * prices[p];
    }
    return price;
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
