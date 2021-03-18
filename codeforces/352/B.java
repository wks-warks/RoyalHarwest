import java.io.*;
import java.util.*;
public class Test {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		Scanner sc = new Scanner(System.in);
// 		int n = sc.nextInt();
        int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
// 			arr[i] = sc.nextInt();
            arr[i] = Integer.parseInt(st.nextToken());
		}
			
// Map with each no as Key and list of its positions as Value			
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
		for (int i=0; i<n; i++) {
			int no = arr[i];
			if (map.containsKey(no)) {
				ArrayList<Integer> list = map.get(no);
				list.add(i);
			} else {
				ArrayList<Integer> list = new ArrayList<Integer>();
				list.add(i);
				map.put(no, list);
			}
		}
		
// Checks if list is AP then stores d as single element of list otherwise deletes Key		
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
			ArrayList<Integer> list = entry.getValue();
			int size = list.size();
			int d = 0;
			for (int i=1; i<size; i++) {
				if (i == 1) {
					d = list.get(1) - list.get(0); 
				} else if (list.get(i) - list.get(i-1) != d){
					toRemove.add(entry.getKey());
					break;
				}
			}
			ArrayList<Integer> dlist = new ArrayList<Integer>();
			dlist.add(d);
			map.replace(entry.getKey(), dlist);
		}
		
// Removing Map Keys which are not AP (+ Avoiding ConcurrentModificationException)
		for (Integer rem : toRemove) {
			map.remove(rem);
		}
		
		StringBuilder str = new StringBuilder();
		for (Map.Entry<Integer, ArrayList<Integer>>  entry : map.entrySet()) {
			str.append(entry.getKey() + " " + entry.getValue().get(0) + "\n");
		}
		
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
		pw.println(map.size());
		pw.println(str);
		pw.close();
// 		System.out.println(map.size());
// 		System.out.println(str);
	}
}