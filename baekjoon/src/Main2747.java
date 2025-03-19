import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2747 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        
        int[] fibo = new int[N+1];
        fibo[0] = 0;
        fibo[1] = 1;
        for (int i = 2; i<=N;i++) {
            fibo[i] = fibo[i-2]+fibo[i-1];
        }
        
        System.out.println(fibo[N]);
    }
}
