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
    int fib = in.nextInt();
    printAnswerTutorial(fib);
    
    in.close();
    out.close();
  }

  static void printAnswerTutorial(int fib) {
    out.println("0 0 " + fib);
  }

  static void printAnswer(int fib) {
    int first, second, third;
    if (fib == 0) {
      first = second = third = 0;
    } else if (fib == 1) {
      first = second = 0;
      third = 1;
    } else {
      List<Integer> fibList = new ArrayList<Integer>();
      fibList.add(0);
      fibList.add(1);

      while (fibList.get(fibList.size()-1) < 1_000_000_000) {
        fibList.add(fibList.get(fibList.size()-1) + fibList.get(fibList.size()-2));
      }

      int idx = Search.findLastIdx(fibList, fib);
      first = fibList.get(idx-2);
      second = fibList.get(idx-2);
      third = fibList.get(idx-3);
    }

    out.println(first + " " + second + " " + third);
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

interface Assignment {
  public int assign(int comparison, int variable, int mid);
}

class Search {
  static Assignment leftCase, rightCase;
  // Assuming an ordered list, finds first (smallest idx) occurrence of requested value
  static <Type extends Comparable<Type>> int findFirstIdx(List<Type> list, Type value) {
    if (list.get(0).compareTo(list.get(list.size()-1)) > 0) {
      return findFirstIdxDesc(list, value);
    }
    rightCase = (comparison, variable, mid) -> {
      return comparison >= 0 ? mid - 1 : variable;
    };
    leftCase = (comparison, variable, mid) -> {
      return comparison >= 0 ? variable : mid + 1;
    };
    return find(list, value);
  }

  // Assuming a descending ordered list, finds first (smallest idx) occurrence of requested value
  private static <Type extends Comparable<Type>> int findFirstIdxDesc(List<Type> list, Type value) {
    rightCase = (comparison, variable, mid) -> {
      return comparison <= 0 ? mid - 1 : variable;
    };
    leftCase = (comparison, variable, mid) -> {
      return comparison <= 0 ? variable : mid + 1;
    };
    return find(list, value);
  }

  // Assuming an ordered list, finds last (largest idx) occurrence of requested value
  static <Type extends Comparable<Type>> int findLastIdx(List<Type> list, Type value) {
    if (list.get(0).compareTo(list.get(list.size()-1)) > 0) {
      return findLastIdxDesc(list, value);
    }
    leftCase = (comparison, variable, mid) -> {
      return comparison <= 0 ? mid + 1 : variable;
    };
    rightCase = (comparison, variable, mid) -> {
      return comparison <= 0 ? variable : mid - 1;
    };
    return find(list, value);
  }

  // Assuming a descending ordered list, finds last (largest idx) occurrence of requested value
  private static <Type extends Comparable<Type>> int findLastIdxDesc(List<Type> list, Type value) {
    leftCase = (comparison, variable, mid) -> {
      return comparison >= 0 ? mid + 1 : variable;
    };
    rightCase = (comparison, variable, mid) -> {
      return comparison >= 0 ? variable : mid - 1;
    };
    return find(list, value);
  }

  // Returns appropriate index with given value
  private static <Type extends Comparable<Type>> int find(List<Type> list, Type value) {
    int left, right, mid, comparison, idx;
    left = 0;
    right = list.size()-1;
    idx = -1;
    while (left <= right) {
      mid = (left + right) >> 1;
      comparison = list.get(mid).compareTo(value);
      if (comparison == 0) {
        idx = mid;
      }
      left = leftCase.assign(comparison, left, mid);
      right = rightCase.assign(comparison, right, mid);
    }
    return idx;
  }
}