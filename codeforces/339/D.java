// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main implements Runnable {
  static Input in = new Input();
  static Output out = new Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), "RegalBeast", 1<<27).start();
  }

  public void run() {
    int n = in.nextInt();
    int queryCount = in.nextInt();

    int arrLen = 1<<n;
    int[] arr = new int[arrLen];
    for (int i = 0; i < arrLen; i++) {
      arr[i] = in.nextInt();
    }

    Tree tree = new Tree(arr);
    for (int q = 0; q < queryCount; q++) {
      int idx = in.nextInt() - 1;
      int newValue = in.nextInt();
      tree.updateValue(idx, newValue);

      int rootValue = tree.getRootValue();
      out.println(Integer.toString(rootValue));
    }

    in.close();
    out.close();
  }
}

class Tree {
  private List<Node> leaves;
  private Node root;

  public Tree(int[] arr) {
    leaves = new ArrayList<Node>(arr.length);
    for (var value : arr) {
      leaves.add(new Node(value, 0));
    }

    build(leaves);
  }

  private void setPartners(List<Node> nodeList) {
    for (int i = 0; i < nodeList.size(); i += 2) {
      Node first = nodeList.get(i);
      Node second = nodeList.get(i+1);
      
      first.setPartner(second);
      second.setPartner(first);
    }
  }

  private void build(List<Node> nodeList) {
    if (nodeList.size() == 1) {
      root = nodeList.get(0);
      return;
    }

    setPartners(nodeList);
    List<Node> parentList = new ArrayList<Node>(nodeList.size()>>1);
    for (int i = 0; i < nodeList.size(); i += 2) {
      int parentValue = nodeList.get(i).getParentValue();
      int parentLevel = nodeList.get(i).getLevel() + 1;
      Node parent = new Node(parentValue, parentLevel); 
      parentList.add(parent);
      nodeList.get(i).setParent(parent);
      nodeList.get(i+1).setParent(parent);
    }

    build(parentList);
  }

  public void updateValue(int idx, int newValue) {
    leaves.get(idx).updateValue(newValue);
  }

  public int getRootValue() {
    return root.getValue();
  }
}

class Node {
  private int value, level;
  private Node partner;
  private Node parent;

  public Node(int value, int level) {
    setValue(value);
    setLevel(level);
  }

  private void setValue(int value) {
    this.value = value;
  }
  private void setLevel(int level) {
    this.level = level;
  }
  public void setPartner(Node partner) {
    this.partner = partner;
  }
  public void setParent(Node parent) {
    this.parent = parent;
  }

  public int getValue() {
    return value;
  }
  public int getLevel() {
    return level;
  }
  public Node getPartner() {
    return partner;
  }
  public Node getParent() {
    return parent;
  }
  public int getParentValue() {
    if ((level & 1) == 0) {
      return this.value | partner.getValue();
    } else {
      return this.value ^ partner.getValue();
    }
  }

  public void updateValue(int value) {
    setValue(value);
    
    if (partner != null) {
      int parentValue = getParentValue();
      parent.updateValue(parentValue);
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

class Output {
  PrintWriter pw;
  public Output() {
    pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  }

  public Output(String fileName) {
    try {
      pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void print(String s) {
    pw.print(s);
  }

  public void println(String s) {
    pw.println(s);
  }

  public void close() {
    pw.close();
  }
}