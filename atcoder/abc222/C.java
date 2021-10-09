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
    int half = in.nextInt();
    int rounds = in.nextInt();

    Player[] players = new Player[2*half];
    for (int p = 0; p < players.length; p++) {
      players[p] = new Player(p+1, in.next());
    }

    Comparator<Player> comp = Comparator.comparing(Player::getWins, Comparator.reverseOrder()).thenComparing(Player::getID);
    for (int r = 0; r < rounds; r++) {
      for (int i = 0; i < half; i++) {
        int first = i*2;
        int second = first+1;
        // out.println("idx " + first + " " + second);
        // out.println("ID " + players[first].getID() + " " + players[second].getID());
        char firstMove = players[first].getMove(r);
        char secondMove = players[second].getMove(r);

        if (firstMove == secondMove) {
          continue;
        }
        int winner = first;
        if (firstMove == 'G' && secondMove == 'P') {
          winner = second;
        }
        if (firstMove == 'C' && secondMove == 'G') {
          winner = second;
        }
        if (firstMove == 'P' && secondMove == 'C') {
          winner = second;
        }

        // if (r == 3) {
          // if (i == 0) {
            // out.println("ooo" + winner + " " + firstMove + " " + secondMove);
          // }
        // }
        players[winner].incrementWins();
      }

      Arrays.sort(players, comp);
      // for (var player : players) {
        // out.println(player.getID() + " " + player.getWins());
      // }
    }

    for (var player : players) {
      out.println(player.getID());
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

class Player {
  private int id, wins;
  private String moves;
  Player(int id, String moves) {
    this.id = id;
    this.wins = 0;
    this.moves = moves;
  } 

  Character getMove(int round) {
    return moves.charAt(round);
  }
  Integer getWins() {
    return wins;
  }
  Integer getID() {
    return id;
  }
  void incrementWins() {
    wins++;
  }
}
