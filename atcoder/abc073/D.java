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
                                ), 1<<28).start();
  }

  public void run() {
    int towns = in.nextInt();
    int roads = in.nextInt();

    int stops = in.nextInt();
    int[] stopList = new int[stops];
    for (int s = 0; s < stops; s++) {
      stopList[s] = in.nextInt()-1;
    }

    Graph graph = new Graph(towns);
    for (int r = 0; r < roads; r++) {
      int first = in.nextInt()-1;
      int second = in.nextInt()-1;
      int len = in.nextInt();
      graph.connect(first, second, len);
    }
    
    int minTravel = graph.getMinTravel(stopList);
    out.println(minTravel);

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

class Graph {
  int nodes;
  int[][] distances;
  Graph (int nodes) {
    this.nodes = nodes;
    distances = new int[nodes][nodes];
    for (int i = 0; i < nodes; i++) {
      for (int j = i+1; j < nodes; j++) {
        distances[i][j] = distances[j][i] = Integer.MAX_VALUE;
      }
    }
  }

  void connect(int first, int second, int len) {
    distances[first][second] = distances[second][first] = len;
  }

  // Floyd-Warshall
  private void setDistances() {
    // Main.out.println();
    // for (int i = 0; i < nodes; i++) {
    //   for (int j = 0; j < nodes; j++) {
    //     Main.out.println((i+1) + " " + (j+1) + " " + distances[i][j]);
    //   }
    // }

    for (int i = 0; i < nodes; i++) {
      for (int first = 0; first < nodes; first++) {
        for (int second = first+1; second < nodes; second++) {
          int firstHalf = distances[first][i];
          int secondHalf = distances[i][second];
          
          if (Math.max(firstHalf, secondHalf) < Integer.MAX_VALUE) {
            distances[first][second] = distances[second][first] = Math.min(distances[first][second], firstHalf + secondHalf);
          }
        }
      }
    }

    // Main.out.println();
    // for (int i = 0; i < nodes; i++) {
    //   for (int j = 0; j < nodes; j++) {
    //     Main.out.println((i+1) + " " + (j+1) + " " + distances[i][j]);
    //   }
    // }
  }

  private int getDistance(int from, int to) {
    return distances[from][to];
  }

  int getMinTravel(int[] stopList) {
    setDistances();
    setPermutations(0, stopList.length-1);
    // Main.out.println();
    // for (var permutation : permutations) {
    //   for (var num : permutation) {
    //     Main.out.print(num + " ");
    //   }
    //   Main.out.println();
    // }
    int minTravel = Integer.MAX_VALUE;

    for (var permutation : permutations) {
      int travel = 0;
      int prev = -1;
      for (var idx : permutation) {
        travel += prev >= 0 ? getDistance(prev, stopList[idx]) : 0;
        prev = stopList[idx];
      }
      // Main.out.println(travel);
      minTravel = Math.min(minTravel, travel);
    }

    return minTravel;
  }

  private static List<Integer> permutation = new ArrayList<Integer>();
  private static List<List<Integer>> permutations = new ArrayList<List<Integer>>();
  private void setPermutations(int from, int to) {
    int len = to - from + 1;
    if (permutation.size() == len) {
      List<Integer> addition = new ArrayList<Integer>(len);
      for (var num : permutation) {
        addition.add(num);
      }
      permutations.add(addition);
      return;
    }

    for (int candidate = from; candidate <= to; candidate++) {
      if (!permutation.contains(candidate)) {
        permutation.add(candidate);
        setPermutations(from, to);
        permutation.remove(permutation.size()-1);
      }
    }
  }
}