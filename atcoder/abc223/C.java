// Author : RegalBeast

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main implements Runnable {
  static Input in = new Input();
  static PrintWriter out = Output();
  
  public static void main(String[] args) {
    new Thread(null, new Main(), String.join(
                                  "All that is gold does not glitter,",
                                  "Not all those who wander are lost;",
                                  "The old that is strong does not wither,",
                                  "Deep roots are not reached by the frost.",
                                  
                                  "From the ashes a fire shall be woken,",
                                  "A light from the shadows shall spring;",
                                  "Renewed shall be blade that was broken,",
                                  "The crownless again shall be king."
                                ), 1<<25).start();
  }

  public void run() {
    int fuses = in.nextInt();
    int[][] fuseData = new int[fuses][2];

    for (int f = 0; f < fuses; f++) {
      fuseData[f][0] = in.nextInt(); // length
      fuseData[f][1] = in.nextInt(); // burn-rate
    }

    double meetingPoint = findMeetingPoint(fuseData);
    out.println(meetingPoint);

    in.close();
    out.close();
  }

  static double findMeetingPoint(int[][] fuseData) {
    double left = 0.0;
    double right = getRight(fuseData);

    double meetingPoint = findMeetingPoint(fuseData, 0.0, 0, 0.0, fuseData.length-1);
    return meetingPoint;
  }

  static double epsilon = 1E-6;
  // static int calls = 0;
  static double findMeetingPoint(int[][] fuseData, double left, int leftIdx, double right, int rightIdx) {
    // calls++;
    // out.println(String.format("call: %d\nleft: %f :: idx: %d\nright: %f :: idx: %d\n", calls, left, leftIdx, right, rightIdx));
    if (leftIdx == rightIdx) {
      // out.println(left + " " + leftIdx + " O");
      return (fuseData[leftIdx][0] - (left+right)) / 2.0;
    }


    if (fuseData[leftIdx][0] - left < epsilon) {
      return findMeetingPoint(fuseData, 0.0, leftIdx+1, right, rightIdx);
    }
    
    if (fuseData[rightIdx][0] - right < epsilon) {
      return findMeetingPoint(fuseData, left, leftIdx, 0.0, rightIdx-1);
    }

    double leftTime = (fuseData[leftIdx][0] - left) / fuseData[leftIdx][1];
    double rightTime = (fuseData[rightIdx][0] - right) / fuseData[rightIdx][1];

    double time = Math.min(leftTime, rightTime);
    double addition = fuseData[leftIdx][1] * time;
    left += addition;

    right += fuseData[rightIdx][1] * time;
    // out.println(addition);
    // out.println(left + " " + leftIdx + " L");
    // out.println(right + " " + rightIdx + " R");
    return addition + findMeetingPoint(fuseData, left, leftIdx, right, rightIdx);
  }

  static double getRight(int[][] fuseData) {
    double right = 0.0;
    for (var data : fuseData) {
      right += data[0];
    }
    return right;
  }
  
  static PrintWriter Output() {
    return new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  }
  
  static PrintWriter Output(String fileName) {
    PrintWriter pw = null;
    try {
      pw = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return pw;
  }
}

class Input {
  BufferedReader br;
  StringTokenizer st;
  public Input() {
    br = new BufferedReader(new InputStreamReader(System.in));
  }

  public Input(String fileName) {
    try {
      br = new BufferedReader(new FileReader(fileName));
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public String next() {
    while (st == null || !st.hasMoreElements()) {
      try {
        st = new StringTokenizer(br.readLine());
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return st.nextToken();
  }

  public int nextInt() {
    return Integer.parseInt(next());
  }

  public long nextLong() {
    return Long.parseLong(next());
  }

  public Float nextFloat() {
    return Float.parseFloat(next());
  }

  public Double nextDouble() {
    return Double.parseDouble(next());
  }

  public String nextLine() {
    if (st != null && st.hasMoreElements()) {
      StringBuilder sb = new StringBuilder();
      while (st.hasMoreElements()) {
        sb.append(next());
      }
      return sb.toString();
    }

    String str = null;
    try {
      str = br.readLine();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return str;
  }

  public void close() {
    try {
      br.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
