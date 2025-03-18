
import java.util.Scanner;

public class Main2670 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        double[] arr = new double[n];
        double[] dp = new double[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextDouble();
        }
        scan.close();

        dp[0] = arr[0];
        double answer = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(dp[i-1]*arr[i], arr[i]);
            answer = Math.max(answer, dp[i]);
        }

        System.out.printf("%.3f", answer);

    }
}
