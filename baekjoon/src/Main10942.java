import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main10942 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i<arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            for (int j = i+1; j < n; j++) {
                dp[i][j] = palindrome(i,j);
            }
        }

        st = new StringTokenizer(br.readLine());
        int q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            sb.append(dp[s][e]?1:0).append("\n");
        }
        System.out.println(sb);
    }

                
    private static boolean palindrome(int s, int e) {
        int mid = (s+e)/2;
        for (int i = s; i <= mid; i++) {
            if (arr[i] != arr[e - (i - s)]) {
                return false;
            }
        }
        return true;

    }
}
