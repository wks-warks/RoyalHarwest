import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int wires = Integer.parseInt(br.readLine());
		int[] birdCount = new int[wires];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int w = 0; w < wires; w++) {
			birdCount[w] = Integer.parseInt(st.nextToken());
		}

		int shots = Integer.parseInt(br.readLine());
		int[][] shotData = new int[shots][2];
		for (int s = 0; s < shots; s++) {
			st = new StringTokenizer(br.readLine());
			shotData[s][0] = Integer.parseInt(st.nextToken());
			shotData[s][1] = Integer.parseInt(st.nextToken());
		}

		updateCount(birdCount, shotData);

		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		for (var count : birdCount) {
			pw.println(count);
		}
		pw.close();
	}

	static void updateCount(int[] birdCount, int[][] shotData) {
		for (var shot : shotData) {
			int wire = shot[0] - 1;
			int pos = shot[1] - 1;
			int up = pos;
			int down = birdCount[wire] - pos - 1;
			if (wire != 0) {
				birdCount[wire-1] += up;
			}
			if (wire != birdCount.length-1) {
				birdCount[wire+1] += down;
			}
			birdCount[wire] = 0;
		}
	}
}



