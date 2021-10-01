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
            int stopCount = reader.nextInt();
            int busSpeed = reader.nextInt();
            int studentSpeed = reader.nextInt();
            
            int[] stops = new int[stopCount];
            for (int i = 0; i < stopCount; i++) {
                stops[i] = reader.nextInt();
            }

            int xu = reader.nextInt(); // x-coordinate of university
            int yu = reader.nextInt(); // y-coordinate of university

            int optimumStop = getOptimumStop(busSpeed, studentSpeed, stops, xu, yu);
            pw.println(optimumStop);
            
            pw.close();
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static int getOptimumStop(int busSpeed, int studentSpeed, int[] stops, int xu, int yu) {
        double optimumTime = Double.POSITIVE_INFINITY;
        double optimumStopDistance = Double.POSITIVE_INFINITY;
        int optimumStop = -1;

        for (int i = 1; i < stops.length; i++) {
            double busTime = getTime(stops[i], busSpeed);
            double stopDistance = getDistance(stops[i], 0, xu, yu);
            double walkTime = getTime(stopDistance, studentSpeed);
            double totalTime = busTime + walkTime;

            if (totalTime < optimumTime) {
                optimumTime = totalTime;
                optimumStopDistance = stopDistance;
                optimumStop = i+1;
            } else if (totalTime == optimumTime) {
                if (optimumStopDistance > stopDistance) {
                    optimumStopDistance = stopDistance;
                    optimumStop = i+1;
                }
            }
        }

        return optimumStop;
    }

    static double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
    }

    static double getTime(double distance, int speed) {
        return distance / speed;
    }
}