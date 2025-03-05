import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main2212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        
        int[] sen = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i<N;i++) {
            sen[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sen);

        int[] dif = new int[N-1];
        for (int i = 0; i<N-1;i++) {
            dif[i] = sen[i+1] - sen[i];
        }

        Arrays.sort(dif);
        
        long answer = 0;
        for (int i = 0; i<dif.length-(K-1);i++) {
            answer += dif[i];
        }
        
        System.out.println(answer);
    }
}
