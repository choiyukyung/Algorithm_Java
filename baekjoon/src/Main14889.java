import java.io.*;
import java.util.*;

public class Main14889 {
    static int N, answer;
    static int[] list;
    static int[][] S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        S = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        list = new int[N / 2];
        combination(0, 0);
        System.out.println(answer);

    }

    private static void combination(int depth, int start) {
        if (depth == N / 2) {
            int score = combination2(depth, start, list, 0);
            int s = 0;
            int ns = 0;
            int[] notlist = new int[N / 2];
            for (int i = 0; i < N; i++) {
                if (s == N / 2) {
                    notlist[ns] = i;
                    ns++;
                } else if (list[s] != i) {
                    notlist[ns] = i;
                    ns++;
                } else {
                    s++;
                }
            }
            int notscore = combination2(depth, start, notlist, 0);
            answer = Math.min((int) Math.abs(score - notscore), answer);
            return;
        }
        for (int i = start; i < N; i++) {
            list[depth] = i;
            combination(depth + 1, i + 1);
        }
    }

    private static int combination2(int depth, int start, int[] list, int sum) {
        for (int i = 0; i < list.length; i++) {
            for (int j = i + 1; j < list.length; j++) {
                sum += (S[list[i]][list[j]] + S[list[j]][list[i]]);
            }
        }
        return sum;
    }
}
