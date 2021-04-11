import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int f1 = Integer.parseInt(st.nextToken());
    int f2 = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(br.readLine());

    int fn = computeFn(f1, f2, n);
    System.out.println(fn);
  }

  static int computeFn(int f1, int f2, int n) {
    int f3 = f2 - f1;
    
    int fn = -1;

    switch(n % 3) {
      case 0:
        int diff = n - 3;
        fn = ((diff / 3) & 1) > 0 ? -f3 : f3;
        break;

      case 1:
        diff = n - 1;
        fn = ((diff / 3) & 1) > 0 ? -f1 : f1;
        break;

      default:
        diff = n - 2;
        fn = ((diff / 3) & 1) > 0 ? -f2 : f2;
    }

    fn %= 1_000_000_007;
    return fn < 0 ? fn + 1_000_000_007 : fn;
  }
}

/*

  fi = fi-1 + fi+1
  fi+1 = fi - fi-1
  fi+1 = fi + fi+2
  fi+2 = -fi-1
  fi+3 = -fi

  Example
  2 3 1 -2 -3 -1 2...
*/