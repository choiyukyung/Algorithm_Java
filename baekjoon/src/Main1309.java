import java.util.Scanner;

public class Main1309 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

         //하나의 가로 줄에 각각 O/X 경우
        int[][] dp = new int[n+1][2];

        dp[1][0] = 2;
        dp[1][1] = 1;
        for(int i = 2; i<=n; i++) {
            dp[i][0] = (dp[i-1][0] * 1 + dp[i-1][1] * 2)%9901;
            dp[i][1] = (dp[i-1][0] + dp[i-1][1])%9901;
        }

        System.out.println((dp[n][0] + dp[n][1])%9901);
        scan.close();
    }
}