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
      int appCount = FR.nextInt();
      int memReqd = FR.nextInt();
      int[] memUsed = new int[appCount];
      for (int i = 0; i < appCount; i += 1) {
        memUsed[i] = FR.nextInt();
      }
      int[] convPts = new int[appCount];
      for (int i = 0; i < appCount; i += 1) {
        convPts[i] = FR.nextInt();
      }
      int ConvPtsLost = computeConvPtsLoss(memReqd, memUsed, convPts);
      solution.append(ConvPtsLost + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int computeConvPtsLoss(int memReqd, int[] memUsed, int[] convPts) {
    List<Integer> memSingleCP = new ArrayList<Integer>(memUsed.length>>1);
    List<Integer> memDoubleCP = new ArrayList<Integer>(memUsed.length>>1);
    for (int i = 0; i < memUsed.length; i += 1) {
      if (convPts[i] == 1) {
        memSingleCP.add(memUsed[i]);
      } else {
        memDoubleCP.add(memUsed[i]);
      }
    }
    Collections.sort(memSingleCP, Collections.reverseOrder());
    Collections.sort(memDoubleCP, Collections.reverseOrder());
    int singleSize = memSingleCP.size();
    int doubleSize = memDoubleCP.size();

    int singlePtr, doublePtr;
    singlePtr = doublePtr = 0;
    int memFreed, evenConvPtsLost;
    memFreed = evenConvPtsLost = 0;
    
    // Assuming even number of singleCP elements consumed
    while (memFreed < memReqd) {
      evenConvPtsLost += 2;
      int singlesLeft = singleSize - singlePtr;
      int doublesLeft = doubleSize - doublePtr;
      if (singlesLeft > 1) {
        int singleConsider = memSingleCP.get(singlePtr) + memSingleCP.get(singlePtr+1);
        int doubleConsider = doublesLeft > 0 ? memDoubleCP.get(doublePtr) : -1;
        if (singleConsider > doubleConsider) {
          memFreed += singleConsider;
          singlePtr += 2;
        } else {
          memFreed += doubleConsider;
          doublePtr += 1;
        }
      } else if (doublesLeft > 0) {
        memFreed += memDoubleCP.get(doublePtr++);
      } else {
        evenConvPtsLost = -1;
        break;
      }
    }
    if (memFreed < memReqd) evenConvPtsLost = -1;
    if (memSingleCP.size() == 0) return evenConvPtsLost;

    // Assuming odd number of singleCP elements consumed
    singlePtr = doublePtr = 0;
    memFreed = memSingleCP.get(singlePtr++);
    int oddConvPtsLost = 1;
    while (memFreed < memReqd) {
      oddConvPtsLost += 2;
      int singlesLeft = singleSize - singlePtr;
      int doublesLeft = doubleSize - doublePtr;
      if (singlesLeft > 1) {
        int singleConsider = memSingleCP.get(singlePtr) + memSingleCP.get(singlePtr+1);
        int doubleConsider = doublesLeft > 0 ? memDoubleCP.get(doublePtr) : -1;
        if (singleConsider > doubleConsider) {
          memFreed += singleConsider;
          singlePtr += 2;
        } else {
          memFreed += doubleConsider;
          doublePtr += 1;
        }
      } else if (doublesLeft > 0) {
        memFreed += memDoubleCP.get(doublePtr++);
      } else {
        oddConvPtsLost = -1;
        break;
      }
    }
    if (memFreed < memReqd) oddConvPtsLost = -1;
    
    if (oddConvPtsLost == -1 && evenConvPtsLost == -1) return -1;
    if (oddConvPtsLost == -1) return evenConvPtsLost;
    if (evenConvPtsLost == -1) return oddConvPtsLost;
    return Math.min(evenConvPtsLost, oddConvPtsLost);
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