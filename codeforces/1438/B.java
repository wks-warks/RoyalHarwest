public class Main {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private java.io.DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public Reader()
        {
            din = new java.io.DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public String readLine() throws java.io.IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    if (cnt != 0) {
                        break;
                    }
                    else {
                        continue;
                    }
                }
                buf[cnt++] = (byte)c;
            }
            return new String(buf, 0, cnt);
        }
 
        public int nextInt() throws java.io.IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
 
            if (neg)
                return -ret;
            return ret;
        }
 
        public long nextLong() throws java.io.IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
 
        public double nextDouble() throws java.io.IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
 
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
 
            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }
 
            if (neg)
                return -ret;
            return ret;
        }
 
        private void fillBuffer() throws java.io.IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0,
                                 BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws java.io.IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws java.io.IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
 
    public static void main(String[] args)
        throws java.io.IOException
    {
        Reader s = new Reader();
        java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.BufferedWriter(new java.io.OutputStreamWriter(System.out)));
        int tests = s.nextInt();
        for (int t = 0; t < tests; t++)
        {
          int n = s.nextInt();
          int[] b = new int[n];
          for (int i = 0; i < n; i++)
          {
            b[i] = s.nextInt();
          }
          boolean possible = checkPossible(b);
          pw.println(possible ? "yeS" : "nO");
        }
        pw.close();
    }

    static boolean checkPossible(int[] b)
    {
      java.util.Set<Integer> seen = new java.util.HashSet<Integer>();
      for (var value : b)
      {
        if (seen.contains(value))
        {
          return true;
        }
        seen.add(value);
      }
      return false;
    }
}