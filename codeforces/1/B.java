// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      String coordinate = FR.next();
      String alternative = getAlternative(coordinate);
      solution.append(alternative + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static String getAlternative(String coordinate) {
    boolean typeRXCY = false;
    int i;
    for (i = coordinate.length()-1; i >= 0; i -= 1) {
      if (Character.isAlphabetic(coordinate.charAt(i))) {
        if (coordinate.charAt(i) == 'C' && i > 0) {
          typeRXCY = !Character.isAlphabetic(coordinate.charAt(i-1));
        }
        break;
      }
    }

    if (typeRXCY) {
      return getCHash(coordinate.substring(i+1)) + coordinate.substring(1, i);
    } else {
      return "R" + coordinate.substring(i+1) + getCY(coordinate.substring(0, i+1));
    }
  }

  static String getCHash(String CY) {
    int col = Integer.parseInt(CY);
    StringBuilder CHash = new StringBuilder();
    while (col > 0) {
      char alphabet = (char) ((col+25) % 26 + 'A');
      if (alphabet == 'Z') {
        col -= 26;
      }
      CHash.append(alphabet);
      col /= 26;
    }
    return CHash.reverse().toString();
  }

  static String getCY(String CHash) {
    int multiplier = 1;
    int col = 0;
    for (int i = CHash.length()-1; i >= 0; i -= 1) {
      int value = CHash.charAt(i) - 'A' + 1;
      col += value * multiplier;
      multiplier *= 26;
    }
    return "C" + col;
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