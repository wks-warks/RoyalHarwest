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
      int operationCount = in.nextInt();
      String[] operations = new String[operationCount];
      for (int op = 0; op < operationCount; op++) {
        operations[op] = in.nextLine();
      }

      long hahaCount = getHahaCount(operations);
      out.println(hahaCount);
    }

    in.close();
    out.close();
  }

  static long getHahaCount(String[] operations) {
    Map<String, Long> hahaCount = new HashMap<String, Long>();
    Map<String, String> start = new HashMap<String, String>();
    Map<String, String> end = new HashMap<String, String>();

    String lastVariable = "";
    for (var op : operations) {
      StringTokenizer tokenizer = new StringTokenizer(op);
      String key = tokenizer.nextToken();
      lastVariable = key;

      String opType = tokenizer.nextToken();

      if (opType.equals(":=")) {
        assign(key, hahaCount, start, end, tokenizer.nextToken());
      } else {
        String var1 = tokenizer.nextToken();
        tokenizer.nextToken(); // +
        String var2 = tokenizer.nextToken();
        addAndAssign(key, var1, var2, hahaCount, start, end);
      }
    }
    return hahaCount.get(lastVariable);
  }

  static void addAndAssign(String key, String var1, String var2, Map<String, Long> hahaCount, Map<String, String> start, Map<String, String> end) {
    long newCount = hahaCount.get(var1) + hahaCount.get(var2) + getHahaCount(end.get(var1) + start.get(var2));
    hahaCount.put(key, newCount);
    if (start.get(var1).length() == 3) {
      start.put(key, start.get(var1));
    } else {
      String str = start.get(var1) + start.get(var2);
      if (str.length() >= 3) {
        start.put(key, str.substring(0, 3));
      } else {
        start.put(key, str);
      }
    }
    if (end.get(var2).length() == 3) {
      end.put(key, end.get(var2));
    } else {
      String str = end.get(var1) + end.get(var2);
      if (str.length() >= 3) {
        end.put(key, str.substring(str.length()-3));
      } else {
        end.put(key, str);
      }
    }
  }

  static void assign(String key, Map<String, Long> hahaCount, Map<String, String> start, Map<String, String> end, String value) {
    long count = getHahaCount(value);
    hahaCount.put(key, count);

    if (value.length() < 4) {
      start.put(key, value);
      end.put(key, value);
    } else {
      start.put(key, value.substring(0, 3));
      end.put(key, value.substring(value.length()-3));
    }
  }
  
  static long getHahaCount(String str) {
    long hahaCount = 0;
    for (int i = str.length()-4; i >= 0; i--) {
      if (str.substring(i, i+4).equals("haha")) {
        hahaCount++;
      }
    }
    return hahaCount;
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