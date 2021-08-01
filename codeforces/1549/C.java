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


static int answer = 0;
public void run() {
    int nobleCount = in.nextInt();
    int friendships = in.nextInt();
    Noble[] nobles = new Noble[nobleCount];
    for (int i = 0; i < nobleCount; i++) {
        nobles[i] = new Noble(i);
    }
    int first, second;

    for (int f = 0; f < friendships; f++) {
        first = in.nextInt() - 1;
        second = in.nextInt() - 1;
        nobles[first].addFriend(second);
        nobles[second].addFriend(first);
    }

    int queries = in.nextInt();
    for (int q = 0; q < queries; q++) {
        switch(in.nextInt()) {
            case 1:
                first = in.nextInt() - 1;
                second = in.nextInt() - 1;
                nobles[first].addFriend(second);
                nobles[second].addFriend(first);
                break;
            case 2:
                first = in.nextInt() - 1;
                second = in.nextInt() - 1;
                nobles[first].removeFriend(second);
                nobles[second].removeFriend(first);
                break;
            default:
                out.println(answer);
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

class Noble {
    int pos, vulnerability;
    public Noble(int pos) {
        this.pos = pos;
        vulnerability = 0;
        Main.answer++;
    }

    public void addFriend(int pos) {
        if (pos > this.pos) {
            if (vulnerability == 0) {
                Main.answer--;
            }
            vulnerability++;
        }
    }

    public void removeFriend(int pos) {
        if (pos > this.pos) {
            vulnerability--;
            if (vulnerability == 0) {
                Main.answer++;
            }
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
