import java.util.Scanner;

public class Main2156 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }

        scan.close();

        if(n==1) {
            System.out.println(arr[0]);
            return;
        } else if(n==2) {
            System.out.println(arr[0] + arr[1]);
            return;
        }
        int[] dp = new int[n];

        dp[0] = arr[0];
        dp[1] = dp[0] + arr[1];
        dp[2] = Math.max(arr[0] + arr[1], Math.max(arr[1] + arr[2], arr[0] + arr[2]));

        for (int i = 3; i <n; i++) {
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-2] + arr[i], dp[i-3] + arr[i-1] + arr[i]));
        }

        System.out.println(dp[n-1]);


    }
}
