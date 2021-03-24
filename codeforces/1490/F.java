import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Collections;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int tests = Integer.parseInt(br.readLine());

    StringTokenizer st;
    StringBuilder output = new StringBuilder();
    for (int t = 0; t < tests; t += 1) {
      int listSize = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());

      List<Integer> list = new ArrayList<Integer>(listSize);
      for (int e = 0; e < listSize; e += 1) {
        list.add(Integer.parseInt(st.nextToken()));
      }

      int elementsToRemove = countElementsToRemove(list);

      output.append(elementsToRemove + "\n");
    }

    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    pw.print(output.toString());
    pw.close();
  }

  static int countElementsToRemove(List<Integer> list) {
    Map<Integer, Integer> freqMap = getFreqMap(list);

    Map<Integer, Integer> fofMap = new TreeMap<Integer, Integer>();
    for (var entry : freqMap.entrySet()) {
      int freq = entry.getValue();

      if (!fofMap.containsKey(freq)) {
        fofMap.put(freq, 1);
      } else {
        fofMap.put(freq, fofMap.get(freq) + 1);
      }
    }

    int elementsToRemove = list.size();

    List<Integer> removalsForLowerBound = getRemovalsForLowerBound(fofMap);
    List<Integer> removalsForUpperBound = getRemovalsForUpperBound(fofMap);

    for (int i = 0; i < removalsForLowerBound.size(); i += 1) {
      int candidate = removalsForLowerBound.get(i) + removalsForUpperBound.get(i);
      elementsToRemove = Math.min(elementsToRemove, candidate);      
    }

    return elementsToRemove;
  }

  static List<Integer> getRemovalsForLowerBound(Map<Integer, Integer> fofMap) {
    List<Integer> removalsForLowerBound = new ArrayList<Integer>(fofMap.size());

    int total = 0;
    for (var entry : fofMap.entrySet()) {
      removalsForLowerBound.add(total);

      int freq = entry.getKey();
      int fof = entry.getValue();
      total += freq * fof;
    }

    return removalsForLowerBound;
  }

  static List<Integer> getRemovalsForUpperBound(Map<Integer, Integer> fofMap) {
    List<Integer> removalsForUpperBound = new ArrayList<Integer>(Collections.nCopies(fofMap.size(), -1));
   
    Map<Integer, Integer> decreasingFoFMap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
    for (var entry : fofMap.entrySet()) {
      decreasingFoFMap.put(entry.getKey(), entry.getValue());
    }

    int idx = removalsForUpperBound.size() - 1;
    int prevGroups = 0;
    int prevFreq = 0;
    int removals = 0;
    for (var entry : decreasingFoFMap.entrySet()) {
      int presFreq = entry.getKey();
      int presGroups = entry.getValue();
      removals += prevGroups * (prevFreq-presFreq);

      removalsForUpperBound.set(idx--, removals);
      prevGroups += presGroups;
      prevFreq = presFreq;
    }

    return removalsForUpperBound;
  }

  static Map<Integer, Integer> getFreqMap(List<Integer> list) {
    Map<Integer, Integer> freqMap = new HashMap<Integer, Integer>();

    for (var element : list) {
      if (!freqMap.containsKey(element)) {
        freqMap.put(element, 1);
      } else {
        freqMap.put(element, freqMap.get(element) + 1);
      }
    }

    return freqMap;
  }
}