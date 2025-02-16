import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1106 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] cost = new int[n];
        int[] customer = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            customer[i] = Integer.parseInt(st.nextToken());
        }

        // dp[사람수] = 최소비용
        // 적어도 c명 유치 -> c 이상일때 오히려 비용이 적게 듦
        int[] dp = new int[c + 1 + 100];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = customer[i]; j <= c + 100; j++) {
                //overflow 주의
                if(dp[j - customer[i]] + cost[i] > 0) {
                    dp[j] = Math.min(dp[j], dp[j - customer[i]] + cost[i]);
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = c; i <= c + 100; i++) {
            answer = Math.min(answer, dp[i]);
        }

        System.out.println(answer);
        // System.out.println(Arrays.toString(dp));

    }
}
