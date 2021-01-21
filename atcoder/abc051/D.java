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
      int vertexCount = FR.nextInt();
      List<List<Pair>> adjacencyLists= new ArrayList<List<Pair>>(vertexCount);
      for (int i = 0; i < vertexCount; i += 1) {
        adjacencyLists.add(new ArrayList<Pair>());
      }
      int edgeCount = FR.nextInt();
      Edge[] edges = new Edge[edgeCount];
      for (int i = 0; i < edgeCount; i += 1) {
        int firstCity = FR.nextInt()-1;
        int secondCity = FR.nextInt()-1;
        int length = FR.nextInt();
        edges[i] = new Edge(firstCity, secondCity, length);
        adjacencyLists.get(firstCity).add(new Pair(secondCity, length));
        adjacencyLists.get(secondCity).add(new Pair(firstCity, length));
      }
      int unnecessaryEdges = 0;
      for (var edge : edges) {
        int firstCity = edge.firstCity;
        int secondCity = edge.secondCity;
        int length = edge.length;
        int shortestPath = getShortestPath(firstCity, secondCity, edges, adjacencyLists);
        if (shortestPath < length) unnecessaryEdges += 1;
      }
      solution.append(unnecessaryEdges + "\n");
    }
		PW.print(solution.toString());
    PW.close();
  }

  static int getShortestPath(int firstCity, int secondCity, Edge[] edges, List<List<Pair>> adjacencyLists) {
    int vertexCount = adjacencyLists.size();
    PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>(vertexCount, new Node());
    List<Integer> minDistances = new ArrayList<Integer>();
    for (int i = 0; i < vertexCount; i += 1) {
      minDistances.add(Integer.MAX_VALUE);
    }
    minDistances.set(firstCity, 0);

    for (var element : adjacencyLists.get(firstCity)) {
      priorityQueue.add(new Node(element.first, element.second));
      minDistances.set(element.first, element.second);
    }
    while (priorityQueue.size() > 0) {
      Node headNode = priorityQueue.poll();
      int city = headNode.city;
      int distance = headNode.distance;
      for (var element : adjacencyLists.get(city)) {
        int nextCity = element.first;
        int nextDistance = distance + element.second;
        if (nextDistance < minDistances.get(nextCity)) {
          minDistances.set(nextCity, nextDistance);
          priorityQueue.add(new Node(nextCity, nextDistance));
        }
      }
    }
    return minDistances.get(secondCity);
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

class Edge {
  int firstCity;
  int secondCity;
  int length;
  public Edge() {

  }
  public Edge(int firstCity, int secondCity, int length) {
    this.firstCity = firstCity;
    this.secondCity = secondCity;
    this.length = length;
  }
}

class Node implements Comparator<Node> {
  public int city;
  public int distance;
  public Node() {

  }
  public Node(int city, int distance) {
    this.city = city;
    this.distance = distance;
  }
  @Override
  public int compare(Node first, Node second) {
    return first.distance < second.distance ? -1 : first.distance == second.distance ? 0 : 1;
  }
}

class Pair {
  int first;
  int second;
  public Pair() {

  }
  public Pair(int first, int second) {
    this.first = first;
    this.second = second;
  }
}