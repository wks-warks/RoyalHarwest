import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.Point;
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

    public void run() {
        try {
            String s = fr.readLine();
            String t = fr.readLine();

            printMoves(s, t);

            fr.close();
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static void printMoves(String s, String t) {
        Point start = getPoint(s);
        Point end = getPoint(t);

        int xDir = end.x > start.x ? 1 : end.x == start.x ? 0 : -1;
        int yDir = end.y > start.y ? 1 : end.y == start.y ? 0 : -1;

        List<String> moves = new ArrayList<String>();
        while (start.x != end.x || start.y != end.y) {
            String move = "";
            if (start.x != end.x) {
                if (xDir == -1) {
                    move += "L";
                } else {
                    move += "R";
                }
                start.x += xDir;
            }
            if (start.y != end.y) {
                if (yDir == -1) {
                    move += "D";
                } else {
                    move += "U";
                }
                start.y += yDir;
            }
            moves.add(move);
        }

        pw.println(moves.size());
        for (var move : moves) {
            pw.println(move);
        }
    }

    static Point getPoint(String s) {
        return new Point(s.charAt(0) - 'a', s.charAt(1) - '1');
    }
}