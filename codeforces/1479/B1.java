// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    for (int t = 0; t < tests; ++t) {
      int elementCount = FR.nextInt();
      List<Integer> elements = new ArrayList<Integer>(elementCount);
      for (int e = 0; e < elementCount; e += 1) {
        elements.add(FR.nextInt());
      }

      int maxSegmentCount = getMaxSegmentCount(elements);
      solution.append(maxSegmentCount + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getMaxSegmentCount(List<Integer> elements) {
    Map<Integer, Queue<Integer>> nextOccurrence = new HashMap<Integer, Queue<Integer>>();
    nextOccurrence.put(0, new ArrayDeque<Integer>(1));
    nextOccurrence.get(0).add(Integer.MAX_VALUE);
    for (int i = 0; i < elements.size(); i += 1) {
      if (!nextOccurrence.containsKey(elements.get(i))) {
        nextOccurrence.put(elements.get(i), new ArrayDeque<Integer>());
      }
      nextOccurrence.get(elements.get(i)).add(i);
    }

    int segmentCount = 0;
    int prevBlack = 0;
    int prevWhite = 0;
    for (int element : elements) {
      nextOccurrence.get(element).remove();
      if (prevBlack == prevWhite) {
        if (element != prevBlack) {
          prevBlack = element;
          segmentCount += 1;
        }
      } else {
        segmentCount += 1;
        if (element == prevBlack) {
          prevWhite = element;
        } else if (element == prevWhite) {
          prevBlack = element;
        } else {
          int blackNext = Integer.MAX_VALUE;
          if (nextOccurrence.get(prevBlack).peek() != null) {
            blackNext = nextOccurrence.get(prevBlack).peek();
          }
          int whiteNext = Integer.MAX_VALUE;
          if (nextOccurrence.get(prevWhite).peek() != null) {
            whiteNext = nextOccurrence.get(prevWhite).peek();
          }
          if (blackNext < whiteNext) {
            prevBlack = element;
          } else {
            prevWhite = element;
          }
        }
      }
    }
    return segmentCount;
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