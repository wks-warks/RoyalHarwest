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
    Bag bag = new Bag();

    int queries = in.nextInt();
    for (int q = 0; q < queries; q++) {
      switch (in.nextInt()) {
        case 1:
          bag.addBall(in.nextInt());
          break;
        case 2:
          bag.updateValues(in.nextInt());
          break;
        default:
          out.println(bag.throwBall());
      }
    }

    in.close();
    out.close();
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

class Bag {
  private TreeMap<Long, Integer> bag;
  private long netAddition;
  
  public Bag() {
    bag = new TreeMap<Long, Integer>();
    netAddition = 0;
  }

  public void addBall(int value) {
    add(value - netAddition);
  }

  public void updateValues(int addition) {
    netAddition += addition;
  }

  public long throwBall() {
    long value = bag.firstKey();
    remove(value);
    return value + netAddition;
  }

  private void add(long value) {
    if (bag.containsKey(value)) {
      bag.put(value, bag.get(value) + 1);
    } else {
      bag.put(value, 1);
    }
  }

  private void remove(long value) {
    int curr = bag.get(value);

    if (curr == 1) {
      bag.remove(value);
    } else {
      bag.put(value, curr-1);
    }
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
