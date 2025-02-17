import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1229 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        
        int[] six = new int[1001];

        six[0] = 0;
        six[1] = 1;

        for (int i = 2; i<six.length;i++) {
            six[i] = 6*(i-1) + six[i-1] - 2*(i-1) + 1;
        }
        //six[1000] = 1999000
        //System.out.println(six[1000]);

        int[] dp = new int[n+1];
        for(int i = 1; i<=n; i++) {
            dp[i] = i;
        }

        for(int i = 1; six[i]<=n; i++) {
            for (int j = six[i]; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - six[i]] + 1);
            }
        }
        System.out.println(dp[n]);
    }
}
