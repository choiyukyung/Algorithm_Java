import java.util.Scanner;

public class Main11055 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] arr = new int[n];

        int[] dp = new int[n];
        int max = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();

            dp[i] = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    //case : 100 1 101 -> 201
                    dp[i] = Math.max(dp[j]+arr[i], dp[i]);
                }
            }

            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
        scan.close();
    }
}
