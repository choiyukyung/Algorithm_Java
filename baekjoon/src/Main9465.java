import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] sticker = new int[N][2];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                sticker[i][0] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                sticker[i][1] = Integer.parseInt(st.nextToken());
            }

            if (N == 1) {
                System.out.println(Math.max(sticker[0][0], sticker[0][1]));
                continue;
            }


            int[][] dp = new int[N][2];
            // 해당 스티커를 떼어낼 때 최대 점수를 저장

            dp[0][0] = sticker[0][0];
            dp[0][1] = sticker[0][1];

            dp[1][0] = sticker[0][1] + sticker[1][0];
            dp[1][1] = sticker[0][0] + sticker[1][1];

            for (int i = 2; i < N; i++) {
                dp[i][0] = Math.max(dp[i-1][1], dp[i-2][1]) + sticker[i][0];
                dp[i][1] = Math.max(dp[i-1][0], dp[i-2][0]) + sticker[i][1];
            }

            System.out.println(Math.max(dp[N-1][0], dp[N-1][1]));
        }
    }
}
