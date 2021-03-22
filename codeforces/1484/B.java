import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(br.readLine());

    StringBuilder output = new StringBuilder();
    StringTokenizer st;
    for (int t = 0; t < tests; t += 1) {
      int listSize = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());

      List<Integer> list = new ArrayList<Integer>(listSize);
      for (int e = 0; e < listSize; e += 1) {
        list.add(Integer.parseInt(st.nextToken()));
      }

      String response = calculateResponse(list);
      output.append(response + "\n");
    }
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static String calculateResponse(List<Integer> list) {
    if (zeroCase(list)) {
      return "0";
    }

    int minModValue = calculateMinModValue(list);

    boolean incFound = false;
    boolean modFound = false;
    int incValue = Integer.MIN_VALUE;
    int modValue = 0;

    int relativeInc;
    relativeInc = Integer.MIN_VALUE;
    
    if (list.get(1) < list.get(0)) {
      relativeInc = list.get(1) - list.get(0);
    } else {
      incFound = true;
      incValue = list.get(1) - list.get(0);
    }

    for (int i = 2; i < list.size(); i += 1) {
      int prev = list.get(i-1);
      int pres = list.get(i);

      if (modFound && incFound) {
        if (pres != ((prev + incValue) % modValue)) {
          return "-1";
        } else {
          continue;
        }
      }

      int diff = pres - prev;
      if (incFound) {
        if (diff >= 0) {

          if (diff != incValue) {
            return "-1";
          } else {
            continue;
          }
        } else {
          
          modFound = true;
          modValue = prev + incValue - pres;
          if (modValue < minModValue) {
            return "-1";
          }
          continue;
        }
      }

      if (diff < 0) {
        if (relativeInc != diff) {
          return "-1";
        }
      } else {
        modFound = true;
        modValue = pres - (prev + relativeInc);
        incFound = true;
        incValue = pres - prev;
        if (modValue < minModValue) {
          return "-1";
        }
      }
    }

    return modValue + " " + incValue;
  }

  static int calculateMinModValue(List<Integer> list) {
    int minModValue = 0;

    for (var element : list) {
      minModValue = Math.max(minModValue, element + 1);
    }

    return minModValue;
  }

  static boolean zeroCase(List<Integer> list) {
    if (list.size() < 3) {
      return true;
    }

    int diff = list.get(1) - list.get(0);

    for (int i = 2; i < list.size(); i += 1) {
      if (list.get(i) != list.get(i-1) + diff) {
        return false;
      }
    }

    return true;
  }
}