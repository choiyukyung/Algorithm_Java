import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] coin = new int[n];

        for (int i = 0; i<coin.length; i++) {
            st = new StringTokenizer(br.readLine());
            coin[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[k+1];
        dp[0] = 1;

        for(int c : coin) {
            for (int j = c; j <= k; j++) {
                dp[j] += dp[j - c];
            }
        }
        
        System.out.println(dp[k]);
    }
}
