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
                                ), 1<<25).start();
  }

  public void run() {
    String[] board = new String[] {
      in.next(),
      in.next(),
      in.next()
    };

    String result = getResult(board);
    out.println(result);

    in.close();
    out.close();
  }

  static String getResult(String[] board) {
    int xCount = countChars(board, 'X');
    int oCount = countChars(board, '0');
    if (oCount > xCount || xCount > oCount+1) {
      return "illegal";
    }

    int xWins = countWins(board, 'X');
    int oWins = countWins(board, '0');
    if (xWins > 0 &&  oWins > 0) {
      return "illegal";
    }

    if (xWins == 1) {
      if (xCount > oCount) {
        return "the first player won";
      } else {
        return "illegal";
      }
    }

    if (oWins == 1) {
      if (oCount == xCount) {
        return "the second player won";
      } else {
        return "illegal";
      }
    }

    if (xWins == 2) {
      boolean acceptable = legalWins(board, 'X');

      if (acceptable && xCount > oCount) {
        return "the first player won";
      }
      return "illegal";
    }

    if (oWins == 2) {
      boolean acceptable = legalWins(board, '0');

      if (acceptable && oCount == xCount) {
        return "the second player won";
      }
      return "illegal";
    }

    if (xCount + oCount == 9) {
      return "draw";
    }

    if (xCount > oCount) {
      return "second";
    }

    return "first";
  }

  static boolean legalWins(String[] board, char candidate) {
    List<List<Point>> winningPoints = getWinningPoints(board, candidate);

    for (int i = 0; i < winningPoints.get(0).size(); i++) {
      for (int j = 0; j < winningPoints.get(1).size(); j++) {
        if (winningPoints.get(0).get(i).x == winningPoints.get(1).get(j).x
        && winningPoints.get(0).get(i).y == winningPoints.get(1).get(j).y) {
          return true;
        }
      }
    }

    return false;
  }

  static List<List<Point>> getWinningPoints(String[] board, char candidate) {
    int[][] winningSets = new int[][] {
      {0, 0, 0, 1, 0, 2},
      {1, 0, 1, 1, 1, 2},
      {2, 0, 2, 1, 2, 2},
      {0, 0, 1, 0, 2, 0},
      {0, 1, 1, 1, 2, 1},
      {0, 2, 1, 2, 2, 2},
      {0, 0, 1, 1, 2, 2},
      {2, 0, 1, 1, 0, 2}
    };

    List<List<Point>> winningPoints = new ArrayList<List<Point>>();
    for (var set : winningSets) {
      if (board[set[0]].charAt(set[1]) == candidate && board[set[2]].charAt(set[3]) == candidate && board[set[4]].charAt(set[5]) == candidate) {
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(set[0], set[1]));
        points.add(new Point(set[2], set[3]));
        points.add(new Point(set[4], set[5]));
        winningPoints.add(points);
      }
    }

    return winningPoints;
  }

  static int countWins(String[] board, char candidate) {
    int[][] winningSets = new int[][] {
      {0, 0, 0, 1, 0, 2},
      {1, 0, 1, 1, 1, 2},
      {2, 0, 2, 1, 2, 2},
      {0, 0, 1, 0, 2, 0},
      {0, 1, 1, 1, 2, 1},
      {0, 2, 1, 2, 2, 2},
      {0, 0, 1, 1, 2, 2},
      {2, 0, 1, 1, 0, 2}
    };

    int count = 0;
    for (var set : winningSets) {
      if (board[set[0]].charAt(set[1]) == candidate && board[set[2]].charAt(set[3]) == candidate && board[set[4]].charAt(set[5]) == candidate) {
        count++;
      }
    }

    return count;
  }

  static int countChars(String[] board, char find) {
    int count = 0;

    for (var row : board) {
      for (var ch : row.toCharArray()) {
        if (ch == find) {
          count++;
        }
      }
    }

    return count;
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
