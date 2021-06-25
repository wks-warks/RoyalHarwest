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
    DSU driftSets = new DSU();
    int snowDrifts = in.nextInt();

    for (int s = 0; s < snowDrifts; s++) {
      int xCoordinate = in.nextInt();
      int yCoordinate = in.nextInt();

      int xCode = -xCoordinate;
      int yCode = yCoordinate;

      driftSets.makeSet(xCode);
      driftSets.makeSet(yCode);
      driftSets.unionSet(xCode, yCode);
    }

    int additionalNeeded = driftSets.computeAdditionalNeeded();
    out.println(additionalNeeded);

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

class DSU {
  Map<Integer, Integer> parents;
  Map<Integer, Integer> ranks;

  public DSU() {
    parents = new HashMap<Integer, Integer>();
    ranks = new HashMap<Integer, Integer>();
  }

  public void makeSet(int setCode) {
    if (parents.containsKey(setCode)) {
      return;
    }

    parents.put(setCode, setCode);
    ranks.put(setCode, 0);
  }

  public int findSet(int setCode) {
    if (parents.get(setCode).equals(setCode)) {
      return setCode;
    }
    parents.put(setCode, findSet(parents.get(setCode)));
    return parents.get(setCode);
  }

  public void unionSet(int first, int second) {
    int firstRep = findSet(first);
    int secondRep = findSet(second);

    if (firstRep != secondRep) {
      if (ranks.get(firstRep) < ranks.get(secondRep)) {
        parents.put(firstRep, secondRep);
      } else {
        parents.put(secondRep, firstRep);

        if (ranks.get(firstRep).equals(ranks.get(secondRep))) {
          ranks.put(firstRep, ranks.get(firstRep) + 1);
        }
      }
    }
  }

  public int computeAdditionalNeeded() {
    for (var key : parents.keySet()) {
      findSet(key);
    }
    return (new HashSet<Integer>(parents.values())).size() - 1;
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