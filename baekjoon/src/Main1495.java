import java.util.Scanner;

public class Main1495 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int s = scan.nextInt();
        int m = scan.nextInt();

        int[] v = new int[n+1];

        for(int i = 1; i <= n; i++) {
            v[i] = scan.nextInt();
        }

        scan.close();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[s][0] = true;

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j <= m; j++) {
                if(dp[j][i-1]) {
                    if(j+v[i]<=m) dp[j+v[i]][i] = true;
                    if(j-v[i]>=0) dp[j-v[i]][i] = true;
                }
            }
        }

        for(int j = m; j >= 0; j--) {
            if(dp[j][n]) {
                System.out.println(j);
                return;
            }
        }
        System.out.println("-1");
    }
}
