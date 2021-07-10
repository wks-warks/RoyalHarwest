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
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int initSize = in.nextInt();
      int[] monoActions = new int[in.nextInt()];
      int[] polyActions = new int[in.nextInt()];
      
      for (int i = 0; i < monoActions.length; i++) {
        monoActions[i] = in.nextInt();
      }
      for (int j = 0; j < polyActions.length; j++) {
        polyActions[j] = in.nextInt();
      }

      int[] actions = getActions(initSize, monoActions, polyActions);
      for (var action : actions) {
        out.print(action + " ");
      }
      out.println();
    }

    in.close();
    out.close();
  }

  static int[] getActions(int progSize, int[] monoActions, int[] polyActions) {
    int[] actions = new int[monoActions.length + polyActions.length];
    int ai = 0;
    int mi = 0;
    int pi = 0;

    while (mi < monoActions.length && pi < polyActions.length) {
      if (monoActions[mi] == 0) {
        progSize++;
        actions[ai++] = 0;
        mi++;
        continue;
      } else if (polyActions[pi] == 0) {
        actions[ai++] = 0;
        progSize++;
        pi++;
        continue;
      }

      if (monoActions[mi] <= progSize) {
        if (monoActions[mi] == 0) {
          progSize++;
        }
        actions[ai++] = monoActions[mi++];
      } else if (polyActions[pi] <= progSize) {
        if (polyActions[pi] == 0) {
          progSize++;
        }
        actions[ai++] = polyActions[pi++];
      } else {
        return new int[]{-1};
      }
    }

    while (mi < monoActions.length) {
      if (monoActions[mi] <= progSize) {
        if (monoActions[mi] == 0) {
          progSize++;
        }

        actions[ai++] = monoActions[mi++];
      } else {
        return new int[]{-1};
      }
    }
    while (pi < polyActions.length) {
      if (polyActions[pi] <= progSize) {
        if (polyActions[pi] == 0) {
          progSize++;
        }

        actions[ai++] = polyActions[pi++];
      } else {
        return new int[]{-1};
      }
    }
    return actions;
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