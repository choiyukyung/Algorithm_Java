import java.util.Scanner;

public class Main9095 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int tc = scan.nextInt();
        for (int t = 0; t < tc; t++) {
            int n = scan.nextInt();
            int[] dp = new int[n + 1];

            if (n == 1) {
                System.out.println(1);
            } else if (n == 2) {
                System.out.println(2);
            } else {
                dp[1] = 1;
                dp[2] = 2;
                dp[3] = 4;
                for (int i = 4; i <= n; i++) {
                    dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
                }
                System.out.println(dp[n]);
            }
        }

        scan.close();
    }
}
