import java.io.*;
import java.util.*;

public class Main1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Queue<int[]> list = new LinkedList<>();
            int[] priorityCount = new int[10];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int p = Integer.parseInt(st.nextToken());
                list.add(new int[] { i, p });
                priorityCount[p]++;
            }

            int index = 0;

            while (!list.isEmpty()) {
                int[] now = list.poll();

                boolean print = false;
                for (int i = now[1] + 1; i <= 9; i++) {
                    if (priorityCount[i] > 0) {
                        print = true;
                        break;
                    }
                }

                if (print) {
                    list.add(now);
                } else {
                    index++;
                    priorityCount[now[1]]--;
                    if (now[0] == M) {
                        System.out.println(index);
                        break;
                    }
                }
            }

        }
    }
}
