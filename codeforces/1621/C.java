// Author : warks
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class C implements Runnable {
    // Credits: NASU41
    static class ContestScanner {
        private final java.io.InputStream in;
        private final byte[] buffer = new byte[1024];
        private int ptr = 0;
        private int buflen = 0;

        private static final long LONG_MAX_TENTHS = 922337203685477580L;
        private static final int LONG_MAX_LAST_DIGIT = 7;
        private static final int LONG_MIN_LAST_DIGIT = 8;

        public ContestScanner(java.io.InputStream in){
            this.in = in;
        }
        public ContestScanner(java.io.File file) throws java.io.FileNotFoundException {
            this(new java.io.BufferedInputStream(new java.io.FileInputStream(file)));
        }
        public ContestScanner(){
            this(System.in);
        }
    
        private boolean hasNextByte() {
            if (ptr < buflen) {
                return true;
            }else{
                ptr = 0;
                try {
                    buflen = in.read(buffer);
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }
                if (buflen <= 0) {
                    return false;
                }
            }
            return true;
        }
        private int readByte() { 
            if (hasNextByte()) return buffer[ptr++]; else return -1;
        }
        private static boolean isPrintableChar(int c) {
            return 33 <= c && c <= 126;
        }
        public boolean hasNext() {
            while(hasNextByte() && !isPrintableChar(buffer[ptr])) ptr++;
            return hasNextByte();
        }
        public String next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            StringBuilder sb = new StringBuilder();
            int b = readByte();
            while(isPrintableChar(b)) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }
    
        public long nextLong() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            long n = 0;
            boolean minus = false;
            int b = readByte();
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            if (b < '0' || '9' < b) {
                throw new NumberFormatException();
            }
            while (true) {
                if ('0' <= b && b <= '9') {
                    int digit = b - '0';
                    if (n >= LONG_MAX_TENTHS) {
                        if (n == LONG_MAX_TENTHS) {
                            if (minus) {
                                if (digit <= LONG_MIN_LAST_DIGIT) {
                                    n = -n * 10 - digit;
                                    b = readByte();
                                    if (!isPrintableChar(b)) {
                                        return n;
                                    } else if (b < '0' || '9' < b) {
                                        throw new NumberFormatException(
                                            String.format("%d%s... is not number", n, Character.toString((char) b))
                                        );
                                    }
                                }
                            } else {
                                if (digit <= LONG_MAX_LAST_DIGIT) {
                                    n = n * 10 + digit;
                                    b = readByte();
                                    if (!isPrintableChar(b)) {
                                        return n;
                                    } else if (b < '0' || '9' < b) {
                                        throw new NumberFormatException(
                                            String.format("%d%s... is not number", n, Character.toString((char) b))
                                        );
                                    }
                                }
                            }
                        }
                        throw new ArithmeticException(
                            String.format("%s%d%d... overflows long.", minus ? "-" : "", n, digit)
                        );
                    }
                    n = n * 10 + digit;
                }else if(b == -1 || !isPrintableChar(b)){
                    return minus ? -n : n;
                }else{
                    throw new NumberFormatException();
                }
                b = readByte();
            }
        }
        public int nextInt() {
            long nl = nextLong();
            if (nl < Integer.MIN_VALUE || nl > Integer.MAX_VALUE) throw new NumberFormatException();
            return (int) nl;
        }
        public double nextDouble() {
            return Double.parseDouble(next());
        }
    
        public long[] nextLongArray(int length){
            long[] array = new long[length];
            for(int i=0; i<length; i++) array[i] = this.nextLong();
            return array;
        }
        public long[] nextLongArray(int length, java.util.function.LongUnaryOperator map){
            long[] array = new long[length];
            for(int i=0; i<length; i++) array[i] = map.applyAsLong(this.nextLong());
            return array;
        }
        public int[] nextIntArray(int length){
            int[] array = new int[length];
            for(int i=0; i<length; i++) array[i] = this.nextInt();
            return array;
        }
        public int[] nextIntArray(int length, java.util.function.IntUnaryOperator map){
            int[] array = new int[length];
            for(int i=0; i<length; i++) array[i] = map.applyAsInt(this.nextInt());
            return array;
        }
        public double[] nextDoubleArray(int length){
            double[] array = new double[length];
            for(int i=0; i<length; i++) array[i] = this.nextDouble();
            return array;
        }
        public double[] nextDoubleArray(int length, java.util.function.DoubleUnaryOperator map){
            double[] array = new double[length];
            for(int i=0; i<length; i++) array[i] = map.applyAsDouble(this.nextDouble());
            return array;
        }
    
        public long[][] nextLongMatrix(int height, int width){
            long[][] mat = new long[height][width];
            for(int h=0; h<height; h++) for(int w=0; w<width; w++){
                mat[h][w] = this.nextLong();
            }
            return mat;
        }
        public int[][] nextIntMatrix(int height, int width){
            int[][] mat = new int[height][width];
            for(int h=0; h<height; h++) for(int w=0; w<width; w++){
                mat[h][w] = this.nextInt();
            }
            return mat;
        }
        public double[][] nextDoubleMatrix(int height, int width){
            double[][] mat = new double[height][width];
            for(int h=0; h<height; h++) for(int w=0; w<width; w++){
                mat[h][w] = this.nextDouble();
            }
            return mat;
        }
    
        public char[][] nextCharMatrix(int height, int width){
            char[][] mat = new char[height][width];
            for(int h=0; h<height; h++){
                String s = this.next();
                for(int w=0; w<width; w++){
                    mat[h][w] = s.charAt(w);
                }
            }
            return mat;
        }
    }

    // Credits: NASU41
    static class ContestPrinter extends java.io.PrintWriter{
        public ContestPrinter(java.io.PrintStream stream){
            super(stream);
        }
        public ContestPrinter(java.io.File file) throws java.io.FileNotFoundException{
            super(new java.io.PrintStream(file));
        }
        public ContestPrinter(){
            super(System.out);
        }
        
        private static String dtos(double x, int n) {
            StringBuilder sb = new StringBuilder();
            if(x < 0){
                sb.append('-');
                x = -x;
            }
            x += Math.pow(10, -n)/2;
            sb.append((long)x);
            sb.append(".");
            x -= (long)x;
            for(int i = 0;i < n;i++){
                x *= 10;
                sb.append((int)x);
                x -= (int)x;
            }
            return sb.toString();
        }

        @Override
        public void print(float f){
            super.print(dtos(f, 20));
        }
        @Override
        public void println(float f){
            super.println(dtos(f, 20));
        }
        @Override
        public void print(double d){
            super.print(dtos(d, 20));
        }
        @Override
        public void println(double d){
            super.println(dtos(d, 20));
        }
        
        

        public void printArray(int[] array, String separator){
            int n = array.length;
            if(n==0){
                super.println();
                return;
            }
            for(int i=0; i<n-1; i++){
                super.print(array[i]);
                super.print(separator);
            }
            super.println(array[n-1]);
        }
        public void printArray(int[] array){
            this.printArray(array, " ");
        }
        public void printArray(int[] array, String separator, java.util.function.IntUnaryOperator map){
            int n = array.length;
            if(n==0){
                super.println();
                return;
            }
            for(int i=0; i<n-1; i++){
                super.print(map.applyAsInt(array[i]));
                super.print(separator);
            }
            super.println(map.applyAsInt(array[n-1]));
        }
        public void printArray(int[] array, java.util.function.IntUnaryOperator map){
            this.printArray(array, " ", map);
        }

        public void printArray(long[] array, String separator){
            int n = array.length;
            if(n==0){
                super.println();
                return;
            }
            for(int i=0; i<n-1; i++){
                super.print(array[i]);
                super.print(separator);
            }
            super.println(array[n-1]);
        }
        public void printArray(long[] array){
            this.printArray(array, " ");
        }
        public void printArray(long[] array, String separator, java.util.function.LongUnaryOperator map){
            int n = array.length;
            if(n==0){
                super.println();
                return;
            }
            for(int i=0; i<n-1; i++){
                super.print(map.applyAsLong(array[i]));
                super.print(separator);
            }
            super.println(map.applyAsLong(array[n-1]));
        }
        public void printArray(long[] array, java.util.function.LongUnaryOperator map){
            this.printArray(array, " ", map);
        }
        public <T> void printArray(T[] array, String separator){
            int n = array.length;
            if(n==0){
                super.println();
                return;
            }
            for(int i=0; i<n-1; i++){
                super.print(array[i]);
                super.print(separator);
            }
            super.println(array[n-1]);
        }
        public <T> void printArray(T[] array){
            this.printArray(array, " ");
        }
        public <T> void printArray(T[] array, String separator, java.util.function.UnaryOperator<T> map){
            int n = array.length;
            if(n==0){
                super.println();
                return;
            }
            for(int i=0; i<n-1; i++){
                super.print(map.apply(array[i]));
                super.print(separator);
            }
            super.println(map.apply(array[n-1]));
        }
        public <T> void printArray(T[] array, java.util.function.UnaryOperator<T> map){
            this.printArray(array, " ", map);
        }
    }

    static ContestScanner in = new ContestScanner();
    static ContestPrinter out = new ContestPrinter();

    public static void main(String[] args) {
        new Thread(null, new C(), String.join(
            "All that is gold does not glitter,",
            "Not all those who wander are lost;",
            "The old that is strong does not wither,",
            "Deep roots are not reached by the frost.",

            "From the ashes a fire shall be woken,",
            "A light from the shadows shall spring;",
            "Renewed shall be blade that was broken,",
            "The crownless again shall be king."
        ), 1<<28).start();
    }

    public void run() {
        int tests = in.nextInt();
        for (int t = 0; t < tests; t++) {
            int len = in.nextInt();
            int[] perm = getPermutation(len);
            out.print("! ");
            out.printArray(perm);
            out.flush();
        }
        
        out.close();
    }

    static int queries;
    private static int[] getPermutation(int len) {
        int[] perm = new int[len];
        int limit = len - 1;
        Set<Integer> settledIdx = new HashSet<Integer>();

        query(1);
        queries = 0;
        for (int i = 0; i < len; i++) {
            if (settledIdx.size() == limit) {
                settleMissing(perm);
                break;
            }
            if (settledIdx.contains(i)) {
                continue;
            }

            List<Integer> chain = new ArrayList<Integer>();
            Set<Integer> chainSet = new HashSet<Integer>();
            int qPrior = queries;
            fillChain(i + 1, chain, chainSet);
            settleChain(qPrior, i, chain, perm, settledIdx);
        }

        return perm;
    }

    private static void settleChain(int qPrior, int i, List<Integer> chain, int[] perm, Set<Integer> settledIdx) {
        int start = (chain.size() - qPrior) % chain.size();
        if (start < 0) {
            start += chain.size();
        }
        int pos = i;

        for (int j = 0; j < chain.size(); j++) {
            int idx = (start + j) % chain.size();
            int num = chain.get(idx);
            perm[pos] = num;
            settledIdx.add(pos);
            pos = num - 1;
        }
    }

    private static void fillChain(int num, List<Integer> chain, Set<Integer> chainSet) {
        int response = query(num);
        while (chainSet.add(response)) {
            chain.add(response);
            chainSet.add(response);
            response = query(num);
        }
    }

    private static void settleMissing(int[] perm) {
        for (int i = 0; i < perm.length; i++) {
            if (perm[i] == 0) {
                perm[i] = i + 1;
                return;
            }
        }
    }

    private static int query(int num) {
        queries++;
        out.println("? " + num);
        out.flush();
        return in.nextInt();
    }
}