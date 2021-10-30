import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

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

    static Car[] cars;
    public void run() {
        try {
            int count = fr.nextInt();
            cars = new Car[count];
            for (int i = 0; i < count; i++) {
                cars[i] = new Car(i+1);
            }

            int queries = fr.nextInt();
            while (queries-->0) {
                int type = fr.nextInt();
                switch (type) {
                    case 1:
                        int x = fr.nextInt();
                        int y = fr.nextInt();
                        connect(x, y);
                        break;
                    case 2:
                        x = fr.nextInt();
                        y = fr.nextInt();
                        disconnect(x, y);
                        break;
                    case 3:
                        x = fr.nextInt();
                        printComponent(x);
                }
            }
            
            fr.close();
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void connect(int x, int y) {
        cars[y-1].nextCar = cars[x-1];
        cars[x-1].prevCar = cars[y-1];
    }

    private static void disconnect(int x, int y) {
        cars[y-1].nextCar = null;
        cars[x-1].prevCar = null;
    }

    private static void printComponent(int x) {
        Car curr = cars[x-1];
        while (curr.nextCar != null) {
            curr = curr.nextCar;
        }

        List<Integer> indices = new ArrayList<Integer>();
        while (curr != null) {
            indices.add(curr.index);
            curr = curr.prevCar;
        }

        pw.print(indices.size());
        for (var idx : indices) {
            pw.print(" " + idx);
        }
        pw.println();
    }
}

class Car {
    int index;
    Car prevCar, nextCar;
    Car(int index) {
        this.index = index;
        prevCar = nextCar = null;
    }
}