import java.util.Scanner;

public class Main1932 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[][] triangle = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                triangle[i][j] = scan.nextInt();
            }
        }

        //삼각형 dp 채우기 - 원래 삼각형의 자리의 값을 선택했을때 + 부모까지의 합의 최대
        int[][] dp = new int[n][n];
        dp[0][0] = triangle[0][0];
        
        for (int i = 1; i < n; i++) {
            //양끝은 부모가 유일
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
        }
        
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(dp[n - 1][i], answer);
        }
        
        System.out.println(answer);
    }
}
