//Codeforces 1370B 
import java.util.Scanner;
import java.util.ArrayList;

public class CF1370B {
    static final Scanner SC = new Scanner(System.in);
    public static void main(String[] args) {
        StringBuilder solution = new StringBuilder();
        int tests = SC.nextInt();
        for (int t = 0; t < tests; ++t) {
            int n = SC.nextInt();
            int[] arr = new int[n*2];
            for (int i = 0; i < arr.length; ++i)
                arr[i] = SC.nextInt();
            Operations op = computeOperations(arr);
            solution.append(op.toString());
        }
        System.out.print(solution.toString());
    }

    // Computes and returns Operations performed
    static Operations computeOperations(int[] arr) {
        int oddCount = 0;
        for (int num : arr)
            if ((num & 1)== 1)
                oddCount += 1;
        
        ArrayList<Integer> oddIdx = new ArrayList<>();
        ArrayList<Integer> evenIdx = new ArrayList<>();

        if ((oddCount & 1) == 1) {
            // Ignore one odd and even
            boolean oddIgnored = false;
            boolean evenIgnored = false;
            for (int i = 0; i < arr.length; ++i) {
                var num = arr[i];
                if (oddIgnored && (num & 1) == 1) {
                    oddIdx.add(i+1); // 1-Indexing
                }
                else if (evenIgnored && (num & 1) == 0) {
                    evenIdx.add(i+1);
                }
                else {
                    if ((num & 1) == 0)
                        evenIgnored = true;
                    else
                        oddIgnored = true;
                }
            }
        }
        else if (oddCount > 2) {
            // Ignore two odd
            int ignored = 0;
            for (int i = 0; i < arr.length; ++i) {
                var num = arr[i];
                if ((num & 1) == 0)
                    evenIdx.add(i+1);
                else if (ignored == 2)
                    oddIdx.add(i+1);
                else
                    ignored += 1;
            }
        }
        else {
            // Ignore two even
            int ignored = 0;
            for (int i = 0; i < arr.length; ++i) {
                var num = arr[i];
                if ((num & 1) == 1)
                    oddIdx.add(i+1);
                else if (ignored == 2)
                    evenIdx.add(i+1);
                else
                    ignored += 1;
            }
        }

        Operations operations = new Operations();
        for (int i = 0; i < oddIdx.size(); i += 2) {
            operations.addOperation(oddIdx.get(i), oddIdx.get(i+1));
        }
        for (int i = 0; i < evenIdx.size(); i += 2) {
            operations.addOperation(evenIdx.get(i), evenIdx.get(i+1));
        }

        return operations;
    }
}

class Operations {
    private ArrayList<Pair> operations;
    public Operations(){
        operations = new ArrayList<>();
    }

    public void addOperation(int num1, int num2) {
        operations.add(new Pair(num1, num2));
    }

    @Override
    public String toString() {
        StringBuilder opString = new StringBuilder();
        for (Pair p : operations)
            opString.append(p.toString());
        return opString.toString();
    }
}

class Pair {
    private int num1, num2;
    public Pair(int num1, int num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    @Override
    public String toString() {
        return String.format("%d %d\n", num1, num2);
    }
}