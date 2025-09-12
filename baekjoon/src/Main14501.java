import java.io.*;
import java.util.*;

public class Main14501 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        int[] t = new int[n + 1];
        int[] p = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            for (int j = 1; j <= i; j++) {
                if (t[j] + j - 1 == i) { // i인 오늘 상담이 끝나는 j 시작 상담
                    dp[i] = Math.max(dp[i], p[j] + dp[i - t[j]]);
                }
            }
        }

        System.out.println(dp[n]);

    }

}
