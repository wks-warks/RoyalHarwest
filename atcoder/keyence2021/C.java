import java.io.*;
import java.util.*;
 
public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));
  static int mmod = 998_244_353;

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int tests = 1;
    // tests = FR.nextInt();
    for (int t = 0; t < tests; ++t) {
      int height = FR.nextInt();
      int width = FR.nextInt();
      char[][] matrix = new char[height][width];
      int fixed = FR.nextInt();
      for (int i = 0; i < fixed; i += 1) {
        int hi = FR.nextInt()-1;
        int wi = FR.nextInt()-1;
        char ci = FR.next().charAt(0);
        matrix[hi][wi] = ci;
      }
      int ways = getWays(matrix, height, width, fixed);
      solution.append(ways+"\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getWays(char[][] matrix, int height, int width, int fixed) {
    int[][] wayMatrix = new int[height][width];
    wayMatrix[0][0] = 1;
    int m3 = minv(3);
    for (int h = 0; h < height; h += 1) {
      for (int w = 0; w < width; w += 1) {
        switch(matrix[h][w]) {
          case 'R':
                if (w+1<width) {
                  wayMatrix[h][w+1] += wayMatrix[h][w];
                  wayMatrix[h][w+1] %= mmod;
                }
                break;
          case 'D':
                if (h+1<height) {
                  wayMatrix[h+1][w] += wayMatrix[h][w];
                  wayMatrix[h+1][w] %= mmod;
                }
                break;
          case 'X':
                if (w+1<width) {
                  wayMatrix[h][w+1] = (wayMatrix[h][w+1] + wayMatrix[h][w]) % mmod;
                }
                if (h+1<height) {
                  wayMatrix[h+1][w] = (wayMatrix[h+1][w] + wayMatrix[h][w]) % mmod;
                }
                break;
          default:
                if (w+1<width) {
                  int add = (int) ((long)wayMatrix[h][w] * 2 * m3 % mmod);
                  wayMatrix[h][w+1] = (wayMatrix[h][w+1] + add) % mmod;
                }
                if (h+1<height) {
                  int add = (int) ((long)wayMatrix[h][w] * 2 * m3 % mmod);
                  wayMatrix[h+1][w] = (wayMatrix[h+1][w] + add) % mmod;
                }
        }
      }
    }
    return (int) ((long)wayMatrix[height-1][width-1]*modExp(3, height*width-fixed) % mmod);
  }

  static int modExp(int base, int pow) {
    int mul = base % mmod;
    int res = 1;
    while (pow > 0) {
      if ((pow&1) > 0) {
        res = (int)((long)res * mul % mmod);
      }
      mul = (int)((long)mul * mul % mmod);
      pow >>= 1;
    }
    return res;
  }

  static int minv(int x) {
    return (exgcd(x, mmod)[0] % mmod + mmod) % mmod;
  }

  static int[] exgcd(int a, int b) {
    if (b == 0) return new int[] {1, 0};
    int[] y = exgcd(b, a % b);
    return new int[] {y[1], y[0] - y[1] * (a / b)};
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