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
    int towns = in.nextInt();
    List<Pair<Integer, Integer>> positions = new ArrayList<Pair<Integer, Integer>>(towns);
    for (int t = 0; t < towns; t++) {
      Pair<Integer, Integer> pos = new Pair<Integer, Integer>(in.nextInt(), in.nextInt());
      positions.add(pos);
    }

    Set<Pair<Integer, Integer>> spellsRequired = getSpellsRequired(towns, positions);
    out.println(spellsRequired.size());

    in.close();
    out.close();
  }

  private static Set<Pair<Integer, Integer>> getSpellsRequired(int towns, List<Pair<Integer, Integer>> positions) {
    Set<Pair<Integer, Integer>> spellsRequired = new HashSet<Pair<Integer, Integer>>();
    for (int t1 = 0; t1 < towns; t1++) {
      for (int t2 = 0; t2 < towns; t2++) {
        if (t1 == t2) {
          continue;
        }

        int xDiff = positions.get(t1).getFirst() - positions.get(t2).getFirst();
        int yDiff = positions.get(t1).getSecond() - positions.get(t2).getSecond();
        int g = GCD(Math.abs(xDiff), Math.abs(yDiff));
        xDiff /= g;
        yDiff /= g;
        spellsRequired.add(new Pair<Integer, Integer>(xDiff, yDiff));
      }
    }

    return spellsRequired;
  }

  private static int GCD(int num1, int num2) {
    if (num1 == 0) {
      return num2;
    }
    return GCD(num2 % num1, num1);
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


class Pair<T1, T2> {
  private T1 first;
  private T2 second;
  public Pair(T1 first, T2 second) {
    setFirst(first);
    setSecond(second);
  }

  public T1 getFirst() {
    return first;
  }
  public T2 getSecond() {
    return second;
  }

  public void setFirst(T1 first) {
    this.first = first;
  }
  public void setSecond(T2 second) {
    this.second = second;
  }

  @Override
  public int hashCode() {
    final int prime = 7_243;
    int hash = first.hashCode() % prime;
    hash = (int) ((long) hash * second.hashCode() % prime);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Pair) {
      Pair<?, ?> other = (Pair<?, ?>) obj;
      return first.equals(other.getFirst()) && second.equals(other.getSecond());
    }
    return false;
  }
}