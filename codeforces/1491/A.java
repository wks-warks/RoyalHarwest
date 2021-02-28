// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    // tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int listSize = FR.nextInt();
      int queryCount = FR.nextInt();
      List<Integer> list = new ArrayList<Integer>(listSize);
      for (int i = 0; i < listSize; i += 1) {
        list.add(FR.nextInt());
      }

      Askable askable = new Askable(list);

      for (int q = 0; q < queryCount; q += 1) {
        int type = FR.nextInt();
        if (type == 1) {
          askable.updateIdx(FR.nextInt() - 1);
        } else {
          solution.append(askable.getKthLargest(FR.nextInt()) + "\n");
        }
      }
    }
		PW.print(solution.toString());
    PW.close();
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreElements()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException  e) {
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e)  {
        e.printStackTrace();
      }
      return str;
    }
  }
}

class Askable {
  List<Integer> list;
  int oneCount;
  int zeroCount;
  public Askable(List<Integer> list) {
    this.list = new ArrayList<Integer>(list);
    oneCount = 0;
    zeroCount = 0;
    for (var element : list) {
      if (element == 1) {
        oneCount += 1;
      } else {
        zeroCount += 1;
      }
    }
  }

  public void updateIdx(int idx) {
    int presValue = list.get(idx);
    if (presValue == 1) {
      oneCount -= 1;
      zeroCount += 1;
    } else {
      oneCount += 1;
      zeroCount -= 1;
    }
    list.set(idx, 1 - presValue);
  }

  public int getKthLargest(int k) {
    if (k <= oneCount) {
      return 1;
    } else {
      return 0;
    }
  }
}