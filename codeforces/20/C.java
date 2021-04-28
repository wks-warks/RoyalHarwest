import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.PriorityQueue;

public class Main {
  public static void main(String[] args) throws IOException {
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int vertices = Integer.parseInt(st.nextToken());
    int edges = Integer.parseInt(st.nextToken());
    Graph graph = new Graph(vertices, edges);    

    for (int e = 0; e < edges; e++) {
      st = new StringTokenizer(br.readLine());
      int first = Integer.parseInt(st.nextToken());
      int second = Integer.parseInt(st.nextToken());
      int edgeWeight = Integer.parseInt(st.nextToken());
      graph.addEdge(first, second, edgeWeight);
    }

    graph.setMinPath();

    Vertex last =  graph.getVertex(vertices);
    if (last.getPrev() == -1) {
      pw.print(-1);
      pw.close();
      return;
    }

    List<Integer> path = new ArrayList<Integer>();
    path.add(vertices);
    while (path.get(path.size()-1) != 1) {
      path.add(graph.getVertex(path.get(path.size()-1)).getPrev());
    }
    Collections.reverse(path);

    StringBuilder solution = new StringBuilder();
    for (var next : path) {
      solution.append(next + " ");
    }

    pw.print(solution.toString());
    pw.close();
  }
}

class Graph {
  private int vertices, edges;
  private List<Vertex> vertexList;

  public Graph(int vertices, int edges) {
    vertexList = new ArrayList<Vertex>(vertices+1);
    for (int v = 0; v <= vertices; v++) {
      vertexList.add(new Vertex(v));
    }
    vertexList.get(1).updateDistance(0, 0, 0);
    this.vertices = vertices;
    this.edges = edges;
  }

  public void addEdge(int first, int second, int weight) {
    vertexList.get(first).addEdge(second, weight);
    vertexList.get(second).addEdge(first, weight);
  }

  public void setMinPath() {
    PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(new Vertex());
    pq.add(vertexList.get(1));
    Set<Integer> settled = new HashSet<Integer>();

    while (pq.size() > 0) {
      Vertex top = pq.poll();
      // System.out.println(top.getIdx() + " " + top.getDistance());
      if (settled.contains(top.getIdx())) {
        // System.out.println("SETT" + top.getIdx());
        continue;
      }
      for (var entry : top.getAdjacencyList()) {
        Vertex neighbour = vertexList.get(entry.getKey());
        if (neighbour.updateDistance(top.getDistance(), entry.getValue(), top.getIdx())) {
          pq.add(neighbour);
          // if (top.getIdx() == 1) {
          //  System.out.println("NEI" + neighbour.getIdx() + " " + neighbour.getDistance());
          // }
        }
        
      }
      settled.add(top.getIdx());
    }
  }

  public Vertex getVertex(int idx) {
    return vertexList.get(idx);
  }
}

class Vertex implements Comparator<Vertex> {
  private int idx;
  private long distance;
  private int prevInPath;
  private List<Map.Entry<Integer, Integer>> adjacencyList;

  public Vertex() {

  }
  public Vertex(int idx) {
    this.idx = idx;
    distance = Long.MAX_VALUE;
    prevInPath = -1;
    adjacencyList = new ArrayList<Map.Entry<Integer, Integer>>();
  }

  public int getIdx() {
    return idx;
  }  
  public long getDistance() {
    return distance;
  }
  public int getPrev() {
    return prevInPath;
  }
  public List<Map.Entry<Integer, Integer>> getAdjacencyList() {
    return adjacencyList;
  }

  public void addEdge(int neighbourIdx, int edgeWeight) {
    adjacencyList.add(new SimpleEntry<Integer, Integer>(neighbourIdx, edgeWeight));
  }
  public boolean updateDistance(long prevDistance, int edgeWeight, int newPrev) {
    if (prevDistance != Long.MAX_VALUE && prevDistance + edgeWeight < distance) {
      this.distance = prevDistance + edgeWeight;
      this.prevInPath = newPrev;
      return true;
    } else {
      return false;
    }
  }

  @Override
  public int compare(Vertex first, Vertex second) {
    return MathFuncs.signum(first.distance - second.distance);
  }
}

class MathFuncs {
  static int signum(long value) {
    return value > 0 ? 1 : value == 0 ? 0 : -1;
  }
}