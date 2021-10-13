import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

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
            int tests = fr.nextInt();
            for (int t = 0; t < tests; t++) {
              int n = fr.nextInt();

              int[] a = new int[n];
              for (int i = 0; i < n; i++) {
                a[i] = fr.nextInt();
              }

              int k = findK(a);
              pw.println(k);
            }
            
            fr.close();
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static int findK(int[] a) {
        int half = a.length >> 1;
        Set<Integer> uniq = new HashSet<Integer>();
        Map<Integer, Integer> frequency = new HashMap<Integer, Integer>();
        for (var num : a) {
            uniq.add(num);

            if (frequency.containsKey(num)) {
                frequency.put(num, frequency.get(num)+1);
            } else {
                frequency.put(num, 1);
            }
            if (frequency.get(num) >= half) {
                return -1;
            }
        }

        int ans = 1;
        for (var num : uniq) {
            Set<Integer> differences = new HashSet<Integer>();

            for (var other : uniq) {
                if (num >= other) {
                    continue;
                }
                differences.add(other-num);
            }

            for (var diff : differences) {
                int sqrt = 1 + (int) Math.sqrt(diff);
                for (int factor = 1; factor <= sqrt; factor++) {
                    if (diff % factor > 0) {
                        continue;
                    }
                    
                    int count = getCount(num, frequency, factor);
                    if (count >= half) {
                        ans = Math.max(ans, factor);
                    }
                    count = getCount(num, frequency, diff/factor);
                    if (count >= half) {
                        ans = Math.max(ans, diff/ factor);
                    }
                }
            }
        }
        return ans;
    }

    static int getCount(int num, Map<Integer, Integer> frequency, int factor) {
        int count = 0;
        for (var entry : frequency.entrySet()) {
            int curr = entry.getKey();
            int freq = entry.getValue();

            if (curr < num) {
                continue;
            }
            if ((curr - num) % factor == 0) {
                count += freq;
            }
        }
        return count;
    }
}