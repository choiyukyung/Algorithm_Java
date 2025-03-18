import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main32712{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        
        //포인트: 계속 특정 값 하나를 끝날 때까지 반복할 수 있다.

        long sum = 0;
        long answer = 0;
        for (int i = 0; i < Math.min(n, k); i++) {
            answer = Math.max(answer, sum + (k - i) * arr[i]);
            sum += arr[i];
        }

        long[] rArr = new long[n];
        for (int i = 0; i < n; i++) {
            rArr[i] = arr[n - 1 - i];
        }

        sum = 0;
        for (int i = 0; i < Math.min(n, k); i++) {
            answer = Math.max(answer, sum + (k - i) * rArr[i]);
            sum += rArr[i];
        }
        

        System.out.println(answer);
    }
}
