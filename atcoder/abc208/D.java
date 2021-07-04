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
    int cities = in.nextInt();
    int roads = in.nextInt();
    Graph graph = new Graph(cities);

    for (int r = 0; r < roads; r++) {
      DirectedRoad road = new DirectedRoad(in.nextInt()-1, in.nextInt()-1, in.nextInt());
      graph.addRoad(road);
    }

    long answer = graph.getAnswer();
    out.println(answer);

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

class Graph {
  int cities;
  int[][] distanceMatrix;
  public Graph(int cities) {
    this.cities = cities;
    distanceMatrix = new int[cities][cities];
    for (int i = 0; i < cities; i++) {
      for (int j = 0; j < cities; j++) {
        distanceMatrix[i][j] = i == j ? 0 : Integer.MAX_VALUE;
      }
    }
  }

  public void addRoad(DirectedRoad road) {
    distanceMatrix[road.from][road.to] = road.cost;
  }

  public long getAnswer() {
    long answer = 0;
    for (int phase = 0; phase < cities; phase++) {
      for (int i = 0; i < cities; i++) {
        for (int j = 0; j < cities; j++) {
          if (distanceMatrix[i][phase] < Integer.MAX_VALUE && distanceMatrix[phase][j] < Integer.MAX_VALUE) {
            distanceMatrix[i][j] = Math.min(distanceMatrix[i][j], distanceMatrix[i][phase] + distanceMatrix[phase][j]);
          }
        }
      }
      answer += getSum();
    }
    return answer;
  }

  private long getSum() {
    long sum = 0;
    for (int i = 0; i < distanceMatrix.length; i++) {
      for (int j = 0; j < distanceMatrix.length; j++) {
        if (distanceMatrix[i][j] != Integer.MAX_VALUE) {
          sum += distanceMatrix[i][j];
        }
      }
    }
    return sum;
  }
}

class DirectedRoad {
  int from, to, cost;
  public DirectedRoad(int from, int to, int cost) {
    this.from = from;
    this.to = to;
    this.cost = cost;
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