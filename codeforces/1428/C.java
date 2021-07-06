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
      String s = in.next();
      int minSize = findMinSize(s);
      out.println(minSize);
    }

    in.close();
    out.close();
  }

  static int findMinSize(String s) {
    DoublyLinkedList<Character> ll = new DoublyLinkedList<Character>();
    for (var ch : s.toCharArray()) {
      ll.add(ch);
    }
    removeA(ll);
    removeB(ll);
    return ll.size;
  }

  static void removeA(DoublyLinkedList<Character> ll) {
    remove(ll, 'A', 'B');
  }

  static void removeB(DoublyLinkedList<Character> ll) {
    remove(ll, 'B', 'B');
  }

  static void remove(DoublyLinkedList<Character> ll, char first, char second) {
    Node<Character> pres = ll.end;
    while (pres != null) {
      if (pres.next != null) {
        if (pres.value == first && pres.next.value == second) {
          pres.next.remove(ll.start, ll.end);
          if (pres.prev == null) {
            ll.start = pres.next;
          }
          pres = pres.prev;
          if (pres != null) {
            pres.next.remove(ll.start, ll.end);
          }

          ll.size -= 2;
        } else {
          pres = pres.prev;
        }
      } else {
        pres = pres.prev;
      }
    }
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

class DoublyLinkedList<T> {
  Node<T> start, end;
  int size;
  public DoublyLinkedList() {
    start = null;
    end = null;
    size = 0;
  }

  public void add(T value) {
    Node node = new Node<T>(value);
    if (size == 0) {
      start = end = node;
    } else {
      end.next = node;
      end.next.prev = end;
      end = end.next;
    }
    size++;
  }
}

class Node<T> {
  T value;
  Node<T> next, prev;
  public Node(T value) {
    prev = null;
    next = null;
    this.value = value;
  }
  public void remove(Node<T> start, Node<T> end) {
    if (this.prev != null) {
      prev.next = this.next;
    } else {
      start = this.next;
    }

    if (this.next != null) {
      next.prev = this.prev;
    } else {
      end = this.prev;
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