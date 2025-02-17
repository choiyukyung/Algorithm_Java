import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2294 {
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
        Arrays.fill(dp, Integer.MAX_VALUE-1);
        //Integer.MAX_VALUE-1 이렇게 초기화하면 오버플로우 피할 수 있다.

        dp[0] = 0;
        for(int c : coin) {
            for (int j = c; j <= k; j++) {
                dp[j] = Math.min(dp[j], dp[j - c] + 1);   
            }
        }
        
        if(dp[k]==Integer.MAX_VALUE-1)
            System.out.println(-1);
        else
            System.out.println(dp[k]);
    }
}
