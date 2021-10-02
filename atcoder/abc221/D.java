import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;

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
            long[][] logs = new long[n][2];
            for (int i = 0; i < n; i++) {
                logs[i][0] = fr.nextLong();
                logs[i][1] = fr.nextLong();
            }

            long[] kPlayerDays = getKPlayerDays(logs);
            for (var count : kPlayerDays) {
                pw.print(count + " ");
            }

            fr.close();
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static long[] getKPlayerDays(long[][] logs) {
        TreeMap<Long, Long> loggedPlayers = new TreeMap<Long, Long>();
        for (var log : logs) {
            if (loggedPlayers.containsKey(log[0])) {
                loggedPlayers.put(log[0], loggedPlayers.get(log[0]) + 1);
            } else {
                loggedPlayers.put(log[0], 1L);
            }

            if (loggedPlayers.containsKey(log[0]+log[1])) {
                loggedPlayers.put(log[0]+log[1], loggedPlayers.get(log[0]+log[1])-1);
            } else {
                loggedPlayers.put(log[0]+log[1], -1L);
            }
        }

        long[] kPlayerDays = new long[logs.length];
        long start = loggedPlayers.firstKey();
        long players = loggedPlayers.get(start);
        loggedPlayers.remove(start);

        long prev = start;
        for (var entry : loggedPlayers.entrySet()) {
            long change = entry.getValue();
            long day = entry.getKey();
            long duration = day - prev;
            
            if (players > 0) {
                kPlayerDays[(int)(players-1)] += duration;
            }
            players += change;
            prev = day;
        }

        return kPlayerDays;
    }
}