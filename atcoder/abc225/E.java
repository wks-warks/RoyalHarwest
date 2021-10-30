import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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
            List<Pair<Fraction, Fraction>> windows = new ArrayList<Pair<Fraction, Fraction>>();
            int sevens = fr.nextInt();
            while (sevens-->0) {
                int x = fr.nextInt();
                int y = fr.nextInt();
                Fraction smaller = new Fraction(y-1, x);
                Fraction larger = new Fraction(y, x-1);
                windows.add(new Pair<Fraction, Fraction>(smaller, larger));
            }

            Collections.sort(windows, (a, b) -> a.getSecond().compareTo(b.getSecond()));
            Fraction prev = new Fraction(0, 1);
            int count = 0;
            for (var pair : windows) {
                if (pair.getFirst().compareTo(prev) < 0) {
                    continue;
                } else {
                    prev = pair.getSecond();
                    count++;
                }
                // pw.println(pair.getFirst().num + " " + pair.getFirst().den);
                // pw.println(pair.getSecond().num + " " + pair.getSecond().den);
                // pw.println();
            }
            
            pw.println(count);
            fr.close();
            pw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class Fraction implements Comparable<Fraction> {
    int num, den;
    Fraction (int num, int den) {
        if (den == 0) {
            this.num = 2_000_000_000;
            this.den = 1;
        } else if (num == 0) {
            this.num = 0;
            this.den = 1;
        } else {
            this.num = num;
            this.den = den;
            int g = gcd(Math.abs(num), Math.abs(den));
            this.num /= g;
            this.den /= g;
        }
    }

    public int compareTo(Fraction f) {
        long first = (long) num * f.den;
        long second = (long) f.num * den;
        return first > second ? 1 : first == second ? 0 : -1;
    }

    private static int gcd(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        return gcd(num2, num1 % num2);
    }
}

class Pair<T1, T2> {
  private T1 first;
  private T2 second;
  public Pair(T1 first, T2 second) {
    setFirst(first);
    setSecond(second);
  }

  public T1 getFirst() {
    return first;
  }
  public T2 getSecond() {
    return second;
  }

  public void setFirst(T1 first) {
    this.first = first;
  }
  public void setSecond(T2 second) {
    this.second = second;
  }

  @Override
  public int hashCode() {
    final int prime = 7_243;
    int hash = first.hashCode() % prime;
    hash = (int) ((long) hash * second.hashCode() % prime);
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Pair) {
      Pair<?, ?> other = (Pair<?, ?>) obj;
      return first.equals(other.getFirst()) && second.equals(other.getSecond());
    }
    return false;
  }
}