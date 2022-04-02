// Author : warks
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.ArrayDeque;

public class Main implements Runnable {
    static ContestScanner in = new ContestScanner();
    static ContestPrinter out = new ContestPrinter();

    public static void main(String[] args) {
        new Thread(null, new Main(), "main", 1<<28).start();
    }

    public void run() {
        int n = in.nextInt();
        int[] a = new int[] {in.nextInt() - 1, in.nextInt() - 1};
        int[] b = new int[] {in.nextInt() - 1, in.nextInt() - 1};

        char[][] grid = in.nextCharMatrix(n, n);
        int moves = getMoves(n, a, b, grid);
        out.println(moves);        

        out.close();
    }

    private static int getMoves(int n, int[] a, int[] b, char[][] grid) {
        int[][] moves = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                moves[i][j] = Integer.MAX_VALUE;
            }
        }

        moves[a[0]][a[1]] = 0;

        List<List<Set<Integer>>> backward = new ArrayList<List<Set<Integer>>>(n);
        List<List<Set<Integer>>> forward = new ArrayList<List<Set<Integer>>>(n);
        for (int i = 0; i < n; i++) {
            backward.add(new ArrayList<Set<Integer>>(n));
            forward.add(new ArrayList<Set<Integer>>(n));
            for (int j = 0; j < n; j++) {
                backward.get(i).add(null);
                forward.get(i).add(null);
            }
        }

        for (int i = 0; i < n; i++) { // i-th backward diagonal from left
            fill(backward, i, 0, -1, 1, grid);
        }

        for (int i = 1; i < n; i++) { // i-th backward diagonal from bottom
            fill(backward, n-1, i, -1, 1, grid);
        }

        for (int i = 0; i < n; i++) { // i-th forward diagonal from top
            fill(forward, 0, i, 1, 1, grid);
        }

        for (int i = 1; i < n; i++) { // i-th forward diagonal from left
            fill(forward, i, 0, 1, 1, grid);
        }

        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j < n; j++) {
        //         Set<Integer> set = forward.get(i).get(j);
        //         int size;
        //         if (set == null) {
        //             size = 0;
        //         } else {
        //             size = set.size();
        //         }
        //         System.out.print(size);
        //     }
        //     System.out.println();
        // }

        Queue<Integer> queue = new ArrayDeque<Integer>();
        int topKey = getKey(a[0], a[1], n);
        queue.add(topKey);
        forward.get(a[0]).get(a[1]).remove(topKey);
        backward.get(a[0]).get(a[1]).remove(topKey);

        while (!queue.isEmpty()) {
            int top = queue.poll();
            int[] pos = getPos(top, n);
            int r = pos[0];
            int c = pos[1];

            List<Integer> remove = new ArrayList<Integer>();
            Set<Integer> fset = forward.get(r).get(c);
            for (var key : fset) {
                if (key != top) {
                    int[] nextPos = getPos(key, n);
                    int nextR = nextPos[0];
                    int nextC = nextPos[1];

                    moves[nextR][nextC] = moves[r][c] + 1;
                    queue.add(key);
                    remove.add(key);
                }
            }

            backward.get(r).get(c).remove(top);
            Set<Integer> bset = backward.get(r).get(c);
            for (var key : bset) {
                if (key != top) {
                    int[] nextPos = getPos(key, n);
                    int nextR = nextPos[0];
                    int nextC = nextPos[1];

                    moves[nextR][nextC] = moves[r][c] + 1;
                    queue.add(key);
                    remove.add(key);
                }
            }

            for (var key : remove) {
                int[] rpos = getPos(key, n);
                int rr = rpos[0];
                int cc = rpos[1];

                forward.get(rr).get(cc).remove(key);
                backward.get(rr).get(cc).remove(key);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (moves[i][j] == Integer.MAX_VALUE) {
                    moves[i][j] = -1;
                }
            }
        }

        return moves[b[0]][b[1]];
    }

    private static void fill(List<List<Set<Integer>>> setList, int r, int c, int rInc, int cInc, char[][] grid) {
        if (Math.min(r, c) < 0 || Math.max(r, c) == setList.size()) {
            return;
        } else if (grid[r][c] != '#') {
            int key = getKey(r, c, setList.size());

            int prevR = r - rInc;
            int prevC = c - cInc;
            if (Math.min(prevR, prevC) >= 0 && Math.max(prevR, prevC) < setList.size() && setList.get(prevR).get(prevC) != null) {
                setList.get(r).set(c, setList.get(prevR).get(prevC));
            } else {
                setList.get(r).set(c, new HashSet<Integer>());
            }
            setList.get(r).get(c).add(key);
        }

        fill(setList, r + rInc, c + cInc, rInc, cInc, grid);
    }

    private static int getKey(int r, int c, int n) {
        return r * n + c;
    }

    private static int[] getPos(int key, int n) {
        return new int[] {key / n, key % n};
    }
}

// Credits: NASU41
class ContestScanner {
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
class ContestPrinter extends java.io.PrintWriter{
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