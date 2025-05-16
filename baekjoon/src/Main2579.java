import java.io.*;

public class Main2579 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            System.out.println(arr[0]);
            return;
        } else if (N == 2) {
            System.out.println(arr[0] + arr[1]);
            return;
        }

        int[] dp = new int[N];
        dp[0] = arr[0];
        dp[1] = dp[0] + arr[1];
        dp[2] = Math.max(arr[2] + dp[0], arr[2] + arr[1]);
        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(arr[i] + dp[i - 2], arr[i] + arr[i - 1] + dp[i - 3]); // 해당 계단을 밟으면서 최대
        }

        System.out.println(dp[N - 1]);

    }

}
