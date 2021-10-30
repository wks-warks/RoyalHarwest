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

    static FastReader fr = new FastReader();
    static PrintWriter pw = new PrintWriter(System.out);
    public static void main(String[] args) {
        new Thread(null, new Main(), "warks", 1<<28).start();
    }

    public void run() {
        try {
            int n = fr.nextInt();
            int m = fr.nextInt();
            int[][] matrix = new int[n][m];
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    matrix[r][c] = fr.nextInt();
                }
            }

            boolean submatrix = isSubmatrix(matrix);
            pw.println(submatrix ? "Yes" : "No");

            fr.close();
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean isSubmatrix(int[][] matrix) {
        boolean satisfiesRowDiff = checkRowDiff(matrix);
        boolean satisfiesColDiff = checkColDiff(matrix);
        boolean satisfiesModDiff = checkModDiff(matrix);
        return satisfiesRowDiff && satisfiesColDiff && satisfiesModDiff;
    }

    private static boolean checkRowDiff(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] - matrix[i-1][0] != 7) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkColDiff(int[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 1; c < matrix[r].length; c++) {
                if (matrix[r][c] - matrix[r][c-1] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkModDiff(int[][] matrix) {
        int cols = matrix[0].length;
        int firstMod = matrix[0][0] % 7;
        if (firstMod == 0) {
            firstMod += 7;
        }
        return firstMod + cols - 1 <= 7;
    }
}