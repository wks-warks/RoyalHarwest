// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

  static ThreadLocalRandom rand = ThreadLocalRandom.current();
  public void run() {
    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int nums = in.nextInt()<<1;

      int[] arr = new int[nums];
      for (int i = 0; i < nums; i++) {
        arr[i] = in.nextInt();
      }

      solve(arr);
    }
    in.close();
    out.close();
  }

  static void solve(int[] arr) {
    Set<Integer> remaining = new HashSet<Integer>();
    for (var num : arr) {
      remaining.add(num);
    }

    CircularLinkedList cll = new CircularLinkedList();
    int prevSize = Integer.MAX_VALUE;
    while (remaining.size() != 0) {
      if (remaining.size() == prevSize) {
        solve(arr);
        return;
      }
      prevSize = remaining.size();

      Set<Integer> toRemove = new HashSet<Integer>();
      for (var num : remaining) {
        if (rand.nextInt(10) > 4 && cll.addValue(num)) {
          toRemove.add(num);
        }
      }

      for (var num : toRemove) {
        remaining.remove(num);
      }
    }

    cll.print();
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

class CircularLinkedList {
  private Node first;
  private int size;
  public CircularLinkedList() {
    first = null;
    size = 0;
  }

  public boolean addValue(int value) {
    Node node = new Node(value);
    if (size == 0) {
      first = node;
      size = 1;

      return true;
    } else if (size == 1) {
      first.setNext(node);
      first.setPrev(node);
      node.setNext(first);
      node.setPrev(first);
      size += 1;

      return true;
    }

    boolean added = false;
    Node pos = first;
    int considered = 0;
    while (considered != size) {
      int posValue = pos.getValue();
      int nextValue = pos.getNext().getValue();
      
      if (posValue + nextValue != (value << 1)) {
        int prevValue = pos.getPrev().getValue();
        if (value + prevValue == (posValue << 1)) {
          return false;
        }
        int nnextValue = pos.getNext().getNext().getValue();
        if (value + nnextValue == (nextValue << 1)) {
          return false;
        }

        pos.insertAfter(new Node(value));
        size += 1;
        return true;
      }

      pos = pos.getNext();
      considered += 1;
    }
    return false;
  }

  public int getSize() {
    return size;
  }

  public void print() {
    Node pos = first;
    int considered = 0;
    while (considered != size) {
      Main.out.print(pos.getValue() + " ");
      pos = pos.getNext();
      considered++;
    }
    Main.out.println();
  }
}

class Node {
  private int value;
  private Node prev, next;

  public Node(int value) {
    this.value = value;
    prev = null;
    next = null;
  }

  public Node getPrev() {
    return prev;
  }
  public Node getNext() {
    return next;
  }
  public int getValue() {
    return value;
  }

  public void setPrev(Node prev) {
    this.prev = prev;
  }
  public void setNext(Node next) {
    this.next = next;
  }

  public void insertAfter(Node node) {
    node.setPrev(this);
    node.setNext(next);
    next.setPrev(node);
    setNext(node);
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