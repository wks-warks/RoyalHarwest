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
    int cityCount = in.nextInt();
    int industrialCities = in.nextInt();
    int[][] roads = new int[cityCount-1][2];
    for (int r = 0; r < cityCount-1; r++) {
      int from = in.nextInt()-1;
      int to = in.nextInt()-1;
      roads[r][0] = Math.min(from, to);
      roads[r][1] = Math.max(from, to);
    }

    Kingdom kingdom = new Kingdom(cityCount, industrialCities, roads);
    out.println(kingdom.getHappinessSum(industrialCities));
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

class Kingdom {
  int cityCount, industrialCities;
  int[][] roads;
  City[] cities;
  public Kingdom(int cityCount, int industrialCities, int[][] roads) {
    this.cityCount = cityCount;
    this.industrialCities = industrialCities;
    this.roads = roads;
    cities = new City[cityCount];
    for (int c = 0; c < cityCount; c++) {
      cities[c] = new City(c);
    }

    for (var road : roads) {
      cities[road[0]].connect(cities[road[1]]);
      cities[road[1]].connect(cities[road[0]]);
    }
  }

  public long getHappinessSum(int industrialCities) {
    cities[0].depth = 0;
    cities[0].setChildren(cities[0]);
    PriorityQueue<City> pq = new PriorityQueue<City>(cityCount, (a, b) -> b.depth - b.subtreeSize - a.depth + a.subtreeSize);
    for (var city : cities) {
      pq.add(city);
    }

    long happinessSum = 0;
    while (industrialCities-->0) {
      City top = pq.poll();
      happinessSum += top.depth - top.subtreeSize;
    }

    return happinessSum;
  }
}

class City {
  int idx;
  Integer depth, subtreeSize;
  List<City> connectedTo;
  City parent;
  public City(int idx) {
    this.idx = idx;
    depth = null;
    subtreeSize = null;
    connectedTo = new ArrayList<City>();
    parent = null;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (getClass() == o.getClass() && this.idx == ((City) o).idx) {
      return true;
    }
    return false;
  }

  public int setChildren(City parent) {
    if (this.equals(parent)) {
      depth = 0;
    } else {
      depth = parent.depth + 1;
    }
    subtreeSize = 0;
    for (var connected : connectedTo) {
      if (connected.equals(parent)) {
        continue;
      }
      subtreeSize += connected.setChildren(this);
    }
    return subtreeSize+1;
  }

  public void connect(City city) {
    connectedTo.add(city);
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