// Author : RegalBeast

import java.io.*;
import java.util.*;

public class Main {
  static final FastReader FR = new FastReader();
  static final PrintWriter PW = new PrintWriter(new OutputStreamWriter(System.out));

  public static void main(String[] args) {
    StringBuilder solution = new StringBuilder();
    int baseEmployees = FR.nextInt();
    // List<Integer> subordinates = new ArrayList<Integer>(baseEmployees);
    Set<Integer> subordinates = new HashSet<Integer>();
    List<List<Integer>> sharedSupervisorSalary = new ArrayList<List<Integer>>(baseEmployees);
    Map<Integer, Map<Integer, Set<Integer>>> sharedPairs = new HashMap<Integer, Map<Integer, Set<Integer>>>();

    for (int e1 = 0; e1 < baseEmployees; e1 += 1) {
      subordinates.add(e1+1);
      sharedSupervisorSalary.add(new ArrayList<Integer>(baseEmployees));
      for (int e2 = 0; e2 < baseEmployees; e2 += 1) {
        int sharedSalary = FR.nextInt();
        sharedSupervisorSalary.get(e1).add(sharedSalary);
        if (!sharedPairs.containsKey(sharedSalary)) {
          sharedPairs.put(sharedSalary, new HashMap<Integer, Set<Integer>>());
        }
        if (!sharedPairs.get(sharedSalary).containsKey(e1+1)) {
          sharedPairs.get(sharedSalary).put(e1+1, new HashSet<Integer>());
        }
        sharedPairs.get(sharedSalary).get(e1+1).add(e2+1);
      }
    }
    NextFree.setValue(baseEmployees+1);

    Map<Integer, Integer> salaries = new HashMap<Integer, Integer>();
    Map<Integer, Integer> supervisors = new HashMap<Integer, Integer>();
    
    Node boss = new Node(subordinates, sharedSupervisorSalary, sharedPairs, -1, salaries, supervisors);
    boss.buildStructure();

    solution.append(salaries.size() + "\n");
    for (int e = 0; e < salaries.size(); e += 1) {
      solution.append(salaries.get(e+1) + " ");
    }
    solution.append("\n");

    solution.append((baseEmployees+1) + "\n");
    for (var entry : supervisors.entrySet()) {
      int employeeIdx = entry.getKey();
      int supervisorIdx = entry.getValue();
      if (supervisorIdx == -1) {
        continue;
      }
      solution.append(employeeIdx + " " + supervisorIdx + "\n");
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

class Node {
  private int supervisorIdx;
  private int index;
  private Set<Integer> subordinates;
  private List<List<Integer>> sharedSupervisorSalary;
  private Map<Integer, Map<Integer, Set<Integer>>> sharedPairs;
  private Map<Integer, Integer> salaries;
  private Map<Integer, Integer> supervisors;
  private Node subordinate1, subordinate2;

  public Node(Set<Integer> subordinates, List<List<Integer>> sharedSupervisorSalary, Map<Integer, Map<Integer, Set<Integer>>> sharedPairs, int supervisorIdx, Map<Integer, Integer> salaries, Map<Integer, Integer> supervisors) {
    this.supervisorIdx = supervisorIdx;
    this.index = subordinates.size() == 1 ? subordinates.iterator().next() : NextFree.getIdx();
    this.subordinates = subordinates;
    this.sharedSupervisorSalary = sharedSupervisorSalary;
    this.sharedPairs = sharedPairs;
    this.salaries = salaries;
    this.supervisors = supervisors;
    subordinate1 = null;
    subordinate2 = null;
  }

  public void buildStructure() {
    if (subordinates.size() == 1) {
      supervisors.put(index, supervisorIdx);
      salaries.put(index, sharedSupervisorSalary.get(index-1).get(index-1));
      return;
    }

    int salary = findSalary();
    int passIdx = index;
    if (supervisorIdx > 0 && salary == salaries.get(supervisorIdx)) {
      passIdx = supervisorIdx;
      NextFree.decrement();
    }
    if (passIdx == index) {
      supervisors.put(index, supervisorIdx);
      salaries.put(index, salary);
    }


    Set<Integer> leftSubordinates = new HashSet<Integer>();
    Set<Integer> rightSubordinates = new HashSet<Integer>();
    Set<Integer> settled = new HashSet<Integer>();
    
    int firstSub = -1;
    for (var sub : sharedPairs.get(salary).keySet()) {
      if (subordinates.contains(sub)) {
        firstSub = sub;
        break;
      }
    }
    // System.out.println("Index: " + index + " Salary: " + salary);
    // System.out.println("Set: " + subordinates + "\n");

    // int firstSub = sharedPairs.get(salary).keySet().iterator().next();
    leftSubordinates.add(firstSub);
    settled.add(firstSub);
    for (var other : sharedPairs.get(salary).get(firstSub)) {
      if (!subordinates.contains(other)) {
        continue;
      }
      rightSubordinates.add(other);
      settled.add(other);
    }
    for (var sub : subordinates) {
      if (!settled.contains(sub)) {
        leftSubordinates.add(sub);
        settled.add(sub);
      }
    }
    // for (var sub : sharedPairs.get(salary).keySet()) {
      // if (!subordinates.contains(sub)) {
        // continue;
      // }
      // if (settled.contains(sub)) {
        // continue;
      // }
      // leftSubordinates.add(sub);
      // settled.add(sub);
    // }

    subordinate1 = new Node(leftSubordinates, sharedSupervisorSalary, sharedPairs, passIdx, salaries, supervisors);
    subordinate1.buildStructure();
    subordinate2 = new Node(rightSubordinates, sharedSupervisorSalary, sharedPairs, passIdx, salaries, supervisors);
    subordinate2.buildStructure();
  }

  private int findSalary() {
    int salary = 0;
    int commonSub = subordinates.iterator().next();
    for (var other : subordinates) {
      salary = Math.max(salary, sharedSupervisorSalary.get(commonSub-1).get(other-1));
    }
    return salary;
  }
}

class NextFree {
  private static int useThis = 0;
  public static void setValue(int value) {
    useThis = value;
  }
  public static int getIdx() {
    return useThis++;
  }
  public static void decrement() {
    useThis -= 1;
  }
}