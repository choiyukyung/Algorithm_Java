import java.io.*;
import java.util.*;

public class Main11501 {
    static int T, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            int[] arr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());

            }

            int[] dp = new int[N];
            int max = arr[N-1];
            long answer = 0;
            for(int i = N - 2; i>=0; i--) {
                if(arr[i]<max) {
                    dp[i] = max - arr[i];
                    answer += dp[i];
                } else {
                    max = arr[i];
                }
            }

            System.out.println(answer);
        }

    }

}
