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
      int boxCount = FR.nextInt();
      int specialCount = FR.nextInt();
      TreeSet<Integer> initialBoxSet = new TreeSet<Integer>();
      List<Integer> initialBoxLocations = new ArrayList<Integer>(boxCount);
      for (int b = 0; b < boxCount; b += 1) {
        int location = FR.nextInt();
        initialBoxSet.add(location);
        initialBoxLocations.add(location);
      }
      TreeSet<Integer> specialSet = new TreeSet<Integer>();
      List<Integer> specialLocations = new ArrayList<Integer>(specialCount);
      for (int s = 0; s < specialCount; s += 1) {
        int location = FR.nextInt();
        specialSet.add(location);
        specialLocations.add(location);
      }
      int specialFilled = countSpecialFilled(initialBoxSet, initialBoxLocations, specialSet, specialLocations);
      solution.append(specialFilled + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int countSpecialFilled(TreeSet<Integer> initialBoxSet, List<Integer> initialBoxLocations, TreeSet<Integer> specialSet, List<Integer> specialLocations) {
    List<Integer> settledList = new ArrayList<Integer>();
    TreeSet<Integer> settledSet = new TreeSet<Integer>();
    for (int i = 0; i < initialBoxLocations.size(); i += 1) {
      int location = initialBoxLocations.get(i);
      if (specialSet.contains(location)) {
        settledList.add(location);
        settledSet.add(location);
      }
    }
    int rightFilled = 0;
    Integer specialStart = specialSet.ceiling(0);
    if (specialStart != null) {
      Integer firstBoxLocation = initialBoxSet.ceiling(0);
      if (firstBoxLocation != null) {
        int firstBox = Search.findFirstIdx(initialBoxLocations, firstBoxLocation);
        int firstSpecial = Search.findFirstIdx(specialLocations, specialStart);
        while(firstSpecial < specialLocations.size() && specialLocations.get(firstSpecial) < initialBoxLocations.get(firstBox)) {
          firstSpecial += 1;
        }
        for (int s = firstSpecial; s < specialLocations.size(); s += 1) {
          int location = specialLocations.get(s);
          int boxCount = Search.findLastIdx(initialBoxLocations, initialBoxSet.floor(location)) - firstBox + 1;
          Integer nextSettled = settledSet.higher(location);
          int additionalSettled;
          if (nextSettled == null) {
            additionalSettled = 0;
          } else {
            additionalSettled = settledList.size() - Search.findFirstIdx(settledList, nextSettled);
          }
          int chainBeginning = location - boxCount + 1;
          int specialCovered = s - Search.findFirstIdx(specialLocations, specialSet.ceiling(chainBeginning)) + 1;
          rightFilled = Math.max(rightFilled, specialCovered + additionalSettled);
        }
      }
    }

    int leftFilled = 0;
    specialStart = specialSet.floor(0);
    if (specialStart != null) {
      Integer firstBoxLocation = initialBoxSet.floor(0);
      if (firstBoxLocation != null) {
        int firstBox = Search.findLastIdx(initialBoxLocations, firstBoxLocation);
        int firstSpecial = Search.findLastIdx(specialLocations, specialStart);
        while(firstSpecial >= 0 && specialLocations.get(firstSpecial) > initialBoxLocations.get(firstBox)) {
          firstSpecial -= 1;
        }
        for (int s = firstSpecial; s >= 0; s -= 1) {
          int location = specialLocations.get(s);
          int boxCount = firstBox - Search.findFirstIdx(initialBoxLocations, initialBoxSet.ceiling(location)) + 1;
          Integer nextSettled = settledSet.lower(location);
          int additionalSettled;
          if (nextSettled == null) {
            additionalSettled = 0;
          } else {
            additionalSettled = Search.findLastIdx(settledList, nextSettled) + 1;
          }
          int chainBeginning = location + boxCount - 1;
          int specialCovered = Search.findLastIdx(specialLocations, specialSet.floor(chainBeginning)) - s + 1;
          leftFilled = Math.max(leftFilled, specialCovered + additionalSettled);
        }
      }
    }

    return rightFilled + leftFilled;
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