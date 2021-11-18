// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.awt.Point;

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
    int n = in.nextInt();
    int c = in.nextInt();    
    List<List<Point>> points = new ArrayList<List<Point>>(c);
    for (int i = 0; i < c; i++) {
      points.add(new ArrayList<Point>());
    }

    for (int i = 0; i < n; i++) {
      Point pt = new Point(in.nextInt(), in.nextInt());
      points.get(in.nextInt()-1).add(pt);
    }
    for (var list : points) {
      Collections.sort(list, (a, b) -> a.x - b.x);
    }

    PriorityQueue<Point> pq = new PriorityQueue<Point>((a, b) -> a.x - b.x);
    for (var list : points) {
      Point point = null;
      for (var pt : list) {
        if (point == null) {
          point = pt;
        } else if (point.y == pt.x) {
          point.y = pt.y;
        } else {
          pq.add(point);
          point = pt;
        }
      }
      if (point != null) {
        pq.add(point);
      }
    }

    int remotes = countRemotes(pq, n);
    out.println(remotes);
    
    in.close();
    out.close();
  }

  private static int countRemotes(PriorityQueue<Point> pq, int n) {
    int remotes = 0;
    TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();

    while (!pq.isEmpty()) {
      Point pt = pq.poll();
      Integer floor = map.floorKey(pt.x);
      if (floor == null) {
        remotes++;
      } else {
        if (map.get(floor) == 1) {
          map.remove(floor);
        } else {
          map.put(floor, map.get(floor)-1);
        }
      }
      Integer prev = map.get(pt.y+1);
      if (prev == null) {
        prev = 0;
      }
      map.put(pt.y+1, prev+1);
    }

    return remotes;
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
