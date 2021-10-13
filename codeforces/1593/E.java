import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
// import java.util.LinkedList;
import java.util.ArrayDeque;

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
            int k = fr.nextInt();

            Map<Integer, Set<Integer>> connections = new HashMap<Integer, Set<Integer>>();
            for (int i = 1; i <= n; i++) {
                connections.put(i, new HashSet<Integer>());
            }

            for (int i = 1; i < n; i++) {
                int first = fr.nextInt();
                int second = fr.nextInt();

                connections.get(first).add(second);
                connections.get(second).add(first);
            }

            int remaining = countRemaining(n, connections, k);
            pw.println(remaining);
            }

            
            fr.close();
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static int countRemaining(int n, Map<Integer, Set<Integer>> connections, int k) {
        Map<Integer, Integer> neighbours = new HashMap<Integer, Integer>();
        Queue<Integer> leaves = new ArrayDeque<Integer>();
        for (int i = 1; i <= n; i++) {
        neighbours.put(i, connections.get(i).size());
        if (neighbours.get(i) <= 1) {
            leaves.add(i);
        }
        }

        Set<Integer> removed = new HashSet<Integer>();
        int remaining = n;
        while (remaining > 0 && k > 0) {
        k--;
        Queue<Integer> next = new ArrayDeque<Integer>();

        while (leaves.size() > 0) {
            int leaf = leaves.poll();
            removed.add(leaf);
            remaining--;

            for (var nbr : connections.get(leaf)) {
            if (removed.contains(nbr)) {
                continue;
            }
            
            neighbours.put(nbr, neighbours.get(nbr)-1);
            if (neighbours.get(nbr) == 1) {
                next.add(nbr);
            }
            }
        }

        leaves = next;
        }

        return remaining;
    }
}