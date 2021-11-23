// Author : RegalBeast
// Jay Tanay Baba Ki

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
    String s = in.next();
    
    int maxK = getMaxK(s);
    out.println(maxK);
    
    in.close();
    out.close();
  }

  private static int getMaxK(String s) {
    int left = 1;
    int right = s.length();
    int answer = 1;
    while (left <= right) {
      int mid = (left + right) >> 1;
      boolean possible = isPossible(s.toCharArray(), mid);
      if (possible) {
        answer = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return answer;
  }

  private static boolean isPossible(char[] sarr, int k) {
    int flip = 0;
    int optional = 0;
    Queue<Integer> queue = new ArrayDeque<Integer>();

    for (int i = 0; i < sarr.length; i++) {
      int num = sarr[i] - '0';
      int state = num ^ flip;
      if (state != 0) {
        if (optional == 0) {
          if (i+k > sarr.length) {
            return false;
          }
          queue.add(i+k-1);
          flip ^= 1;
        }
      }
      Integer top = queue.peek();
      if (top != null && top == i) {
        queue.poll();
        flip ^= 1;
      }
      if (i == k-1) {
        optional = 1;
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
