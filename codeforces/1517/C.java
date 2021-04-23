// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int n = FR.nextInt();
    List<Integer> diagonal = new ArrayList<Integer>(n);
    for (int i = 0; i < n; i++) {
      diagonal.add(FR.nextInt());
    }

    List<List<Integer>> lowerTriangle = getLowerTriangle(diagonal);
    for (var row : lowerTriangle) {
      for (var num : row) {
        solution.append(num + " ");
      }
      solution.append("\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static List<List<Integer>> getLowerTriangle(List<Integer> diagonal) {
    List<List<Integer>> lowerTriangle = new ArrayList<List<Integer>>(diagonal.size());
    for (int r = 0; r < diagonal.size(); r++) {
      lowerTriangle.add(new ArrayList<Integer>(Collections.nCopies(r+1, -1)));
      lowerTriangle.get(r).set(r, diagonal.get(r));
    }

    for (int r = 0; r < lowerTriangle.size(); r++) {
      resolveElement(lowerTriangle, r);
    }
    return lowerTriangle;
  }

  static void resolveElement(List<List<Integer>> lowerTriangle, int r) {
    int toAdd = lowerTriangle.get(r).get(r) - 1;
    int element = lowerTriangle.get(r).get(r);
    // if (element == 3) {
      // System.out.println(r + " is " + "R");
    // }

    int presRow = r;
    int presCol = r;
    if (presCol > 0 && lowerTriangle.get(presRow).get(presCol - 1) == -1) {
      presCol -= 1;
    } else {
      presRow += 1;
    }

    while (toAdd > 0) {
      // System.out.println(presRow + "R P C" + presCol);
      lowerTriangle.get(presRow).set(presCol, element);

      if (presCol > 0 && lowerTriangle.get(presRow).get(presCol - 1) == -1) {
        presCol -= 1;
      } else {
        presRow += 1;
      }

      toAdd -= 1;
    }

    // StringBuilder representation = new StringBuilder();
    // for (var list : lowerTriangle) {
    //   for (var num : list) {
    //     representation.append(num + " ");
    //   }
    //   representation.append("\n");
    // }
    // System.out.println(representation.toString());
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