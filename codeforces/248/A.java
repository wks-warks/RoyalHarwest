import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

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

    static PrintWriter pw = new PrintWriter(System.out);
    static FastReader reader = new FastReader();
    public static void main(String[] args) {
        new Thread(null, new Main(), "warks", 1<<28).start();
    }

    public void run() {
        try {
            int cupboards = reader.nextInt();
            int[][] states = new int[cupboards][2];
            for (int i = 0; i < cupboards; i++) {
                states[i][0] = reader.nextInt();
                states[i][1] = reader.nextInt();
            }

            int timeNeeded = computeTimeNeeded(states);
            pw.println(timeNeeded);

            pw.close();
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static int computeTimeNeeded(int[][] states) {
        int leftOpen = 0;
        int rightOpen = 0;

        for (int i = 0; i < states.length; i++) {
            if (states[i][0] == 1) {
                leftOpen++;
            }
            if (states[i][1] == 1) {
                rightOpen++;
            }
        }

        int leftTime = Math.min(leftOpen, states.length-leftOpen);
        int rightTime = Math.min(rightOpen, states.length-rightOpen);
        return leftTime + rightTime;
    }
}