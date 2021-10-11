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
    int requests = in.nextInt();
    int hallSize = in.nextInt();
    boolean[][] occupancy = new boolean[hallSize][hallSize];

    for (int r = 0; r < requests; r++) {
      int demand = in.nextInt();
      int[] seats = getSeats(demand, occupancy);

      if (seats == null) {
        out.println(-1);
      } else {
        out.println(String.format("%d %d %d", seats[0], seats[1], seats[2]));
      }
    }
    
    in.close();
    out.close();
  }

  static int[] getSeats(int demand, boolean[][] occupancy) {
    int[] seats = new int[3];
    int minRemoteness = Integer.MAX_VALUE;
    int center = occupancy.length / 2;

    for (int row = 0; row < occupancy.length; row++) {
      for (int col = 0; col < occupancy[row].length; col++) {
        boolean available = getAvailability(row, col, demand, occupancy);
        
        if (available) {
          int remoteness = getRemoteness(row, col, demand, center);
          if (remoteness < minRemoteness) {
            seats[0] = row+1;
            seats[1] = col+1;
            seats[2] = col+demand;

            minRemoteness = remoteness;
          }
        }
      }
    }

    if (minRemoteness == Integer.MAX_VALUE) {
      return null;
    }
    for (int c = seats[1]-1; c <= seats[2]-1; c++) {
      occupancy[seats[0]-1][c] = true;
    }
    return seats;
  }

  static int getRemoteness(int row, int col, int demand, int center) {
    int rowDiff = Math.abs(row - center) * demand;
    int colDiff = 0;

    if (col <= center) {
      if (col + demand - 1 <= center) {
        colDiff = sumArithmeticProgression(center - (col + demand - 1), center - col, 1);
      } else {
        colDiff = sumArithmeticProgression(0, center - col, 1);
        colDiff += sumArithmeticProgression(0, col + demand - 1 - center, 1);
      }
    } else {
      colDiff = sumArithmeticProgression(col - center, col + demand - 1 - center, 1);
    }

    return rowDiff + colDiff;
  }

  static int sumArithmeticProgression(int start, int end, int jump) {
    int n = end - start + 1;
    return n * (start + end) / 2;
  }

  static boolean getAvailability(int row, int col, int demand, boolean[][] occupancy) {
    if (col + demand > occupancy[row].length) {
      return false;
    }

    for (int i = 0; i < demand; i++) {
      if (occupancy[row][col+i]) {
        return false;
      }
    }
    return true;
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
