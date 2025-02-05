import java.util.Scanner;

public class Main1535 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int[] L = new int[n];
        int[] J = new int[n];

        for(int i = 0; i < n; i++) {
            L[i] = scan.nextInt();
        }

        for(int i = 0; i < n; i++) {
            J[i] = scan.nextInt();
        }

        int[] dp = new int[101];

        for(int i = 0; i < n; i++) {
            for(int j = 100; j >= L[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-L[i]] + J[i]);
            }
        }

        int max = 0;
        for (int j = 1; j < 100; j++) {
            max = Math.max(max, dp[j]);
        }

        System.out.println(max);
    }
}
