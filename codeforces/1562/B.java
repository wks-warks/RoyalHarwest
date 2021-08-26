// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), "King", 1<<25).start();
  }

  // static Set<Integer> primes;
  public void run() {
    // setPrimes(100_000);

    Set<Integer> unbreakable = new HashSet<Integer>();
    unbreakable.add(2);
    unbreakable.add(3);
    unbreakable.add(5);
    unbreakable.add(7);
    unbreakable.add(23);
    unbreakable.add(37);
    unbreakable.add(53);
    unbreakable.add(73);

    Set<Integer> base = new HashSet<Integer>();
    for (int i = 1; i < 100; i++) {
      if (!unbreakable.contains(i)) {
        base.add(i);
      }
    }

    int tests = in.nextInt();
    for (int t = 0; t < tests; t++) {
      int digits = in.nextInt();
      String num = in.next();
      Set<Integer> possibleNums = new HashSet<Integer>();
      
      for (int i = 0; i < digits; i++) {
        possibleNums.add(Integer.parseInt(num.substring(i, i+1)));
        for (int j = i+1; j < digits; j++) {
          possibleNums.add(Integer.parseInt(num.substring(i, i+1) + num.substring(j, j+1)));
        }
      }
      int minNum = Integer.MAX_VALUE;
      for (var option : possibleNums) {
        if (base.contains(option)) {
          minNum = Math.min(minNum, option);
        }
      }
      out.println(Integer.toString(minNum).length());
      out.println(minNum);
    }


    in.close();
    out.close();
  }

  // static void setPrimes(int upto) {
  //   primes = new HashSet<Integer>();
  //   boolean[] isComposite = new boolean[upto+1];
  //   for (int i = 2; (long) i * i <= upto; i++) {
  //     if (isComposite[i]) {
  //       continue;
  //     }
  //     for (int j = i * i; j <= upto; j += i) {
  //       isComposite[j] = true;
  //     }
  //   }

  //   for (int i = 2; i <= upto; i++) {
  //     if (!isComposite[i]) {
  //       primes.add(i);
  //     }
  //   }
  // }
  
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
