import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main implements Runnable {
    static class FastReader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public FastReader(String fileName) throws IOException {
            din = new DataInputStream(new FileInputStream(fileName));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64];
            int cnt = 0, ch;

            while ((ch = read()) != -1) {
                if (ch == '\n') {
                    if (cnt != 0) {
                        break;
                    } else {
                        continue;
                    }
                }

                buf[cnt++] = (byte)ch;
            }

            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int val = 0;
            byte ch = read();

            while (ch <= ' ') {
                ch = read();
            }
            
            boolean negative = (ch == '-');
            if (negative) {
                ch = read();
            }

            do {
                val = val * 10 + ch - '0';
            } while ((ch = read()) >= '0' && ch <= '9');

            if (negative) {
                return -val;
            } else {
                return val;
            }
        }

        public long nextLong() throws IOException {
            long val = 0L;
            byte ch = read();

            while (ch <= ' ') {
                ch = read();
            }

            boolean negative = (ch == '-');
            if (negative) {
                ch = read();
            }

            do {
                val = val * 10 + ch - '0';
            } while ((ch = read()) >= '0' && ch <= '9');

            if (negative) {
                return -val;
            } else {
                return val;
            }
        }

        public double nextDouble() throws IOException {
            double val = 0.0, div = 1.0;
            byte ch = read();

            while (ch <= ' ') {
                ch = read();
            }

            boolean negative = (ch == '-');
            if (negative) {
                ch = read();
            }

            do {
                val = val * 10.0 + ch - '0';
            } while ((ch = read()) >= '0' && ch <= '9');

            if (ch == '.') {
                while ((ch = read()) >= '0' && ch <= '9') {
                    val += (ch - '0') / (div *= 10.0);
                }
            }

            if (negative) {
                return -val;
            } else {
                return val;
            }
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) {
                return;
            }
            din.close();
        }
    }

    static FastReader fr = new FastReader();
    static PrintWriter pw = new PrintWriter(System.out);
    public static void main(String[] args) {
        new Thread(null, new Main(), "warks", 1<<28).start();
    }

    public void run() {
        try {
            int n = fr.nextInt();
            Integer[][] gifts = new Integer[n][2];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n; j++) {
                gifts[j][i] = fr.nextInt();
                }
            }

            long ways = countWays(n, gifts);
            pw.println(ways);

            fr.close();
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

  private static long countWays(int n, Integer[][] gifts) {
    Arrays.sort(gifts, (a, b) -> (a[0] - b[0]));
    SegmentTree st = new SegmentTree(gifts, 1);

    long ways = 0;
    int prev = -1;
    List<Integer> prevList = new ArrayList<Integer>();
    for (var gift : gifts) {
      if (gift[0] != prev) {
        for (var element : prevList) {
          st.update(element);
        }
        for (var element : prevList) {
          ways += st.query(element);
        }

        prev = gift[0];
        prevList = new ArrayList<Integer>();
      }
      prevList.add(gift[1]);
    }

    for (var element : prevList) {
      st.update(element);
    }
    for (var element : prevList) {
      ways += st.query(element);
    }

    return ways;
  }


}
class SegmentTree {
  TreeMap<Integer, Integer> compression;
  int[] st;

  SegmentTree (Integer[][] arr, int secondIdx) {
    compression = new TreeMap<Integer, Integer>();
    for (var pair : arr) {
      compression.put(pair[secondIdx], null);
    }

    int newIdx = 0;
    for (var entry : compression.entrySet()) {
      entry.setValue(newIdx++);
    }

    st = new int[compression.size()<<1];
  }

  int query(int value) {
    int idx = compression.get(value);
    return query(idx, compression.size());
  }

  int query(int left, int right) {
    int res = 0;
    for (left += compression.size(), right += compression.size(); left < right; left >>= 1, right >>= 1) {
      if ((left & 1) > 0) {
        res += st[left++];
      }
      if ((right & 1) > 0) {
        res += st[--right];
      }
    }
    return res;
  }

  void update(int value) {
    int idx = compression.get(value);
    modify(idx);
  }

  void modify(int idx) {
    for (st[idx += compression.size()] += 1; idx > 1; idx >>= 1) {
      st[idx>>1] = st[idx] + st[idx^1];
    }
  }
}