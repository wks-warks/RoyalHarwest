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
      int n = in.nextInt();
      int[] arr = new int[n];
      for (int i = 0; i < n; i++) {
        arr[i] = in.nextInt();
      }

      boolean possible = isPossible(arr);
      out.println(possible ? "YES" : "NO");
    }

    in.close();
    out.close();
  }

  static boolean isPossible(int[] arr) {
    DoublyLinkedList dll = new DoublyLinkedList();
    for (var value : arr) {
      dll.addNextNode(new Node(value));
    }

    dll.shrink();
    return dll.start.next == null;
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

class DoublyLinkedList {
  Node start;
  Node end;

  public DoublyLinkedList() {
    start = null;
    end = null;
  }

  public void addNextNode(Node next) {
    if (start == null) {
      start = end = next;
    } else {
      end.next = next;
      next.prev = end;
      end = next;
    }
  }

  public void shrink() {
    shrinkAsEnd(end);    
  }

  private void shrinkAsEnd(Node end) {
    while (end.prev != null && end.value > end.prev.value) {
      end.prev = end.prev.prev;
      if (end.prev != null) {
        end.prev.next = end;
      } else {
        start = end;
      }
    }

    // debug();

    if (end.prev != null) {
      shrinkAsWall(end);
    }
  }

  private void shrinkAsWall(Node wall) {
    Node potentialWallBreaker = wall.prev;
    while (potentialWallBreaker != null && potentialWallBreaker.value > wall.value) {
      potentialWallBreaker = potentialWallBreaker.prev;
    }

    if (potentialWallBreaker != null) {
      while (potentialWallBreaker.next != wall) {
        potentialWallBreaker.next = potentialWallBreaker.next.next;
        potentialWallBreaker.next.prev = potentialWallBreaker;
      }

      shrinkAsEnd(wall);
    }
  }

  private void debug() {
    Node node = start;
    System.out.println("DLL Values: ");
    while (node != null) {
      System.out.print(node.value + " ");
      node = node.next;
    }
    System.out.println();
  }
}

class Node {
  int value;
  Node prev, next;

  public Node(int value) {
    this.value = value;
    prev = null;
    next = null;
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
    if (st.hasMoreElements()) {
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