import java.io.*;

public class Main9461 {
    static int T, N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(br.readLine());

        long[] dp = new long[100];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = dp[2] + dp[0];
        dp[4] = dp[3];
        dp[5] = dp[4] + dp[0];
        dp[6] = dp[5] + dp[1];
        dp[7] = dp[6] + dp[2];
        for (int i = 5; i < 100; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }

        // for (int t = 0; t < 20; t++) {
        //     System.out.println(dp[t]);
        // }

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            System.out.println(dp[N - 1]);
        }

    }

}
