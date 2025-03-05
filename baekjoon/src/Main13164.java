import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main13164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        int[] kid = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i<N;i++) {
            kid[i] = Integer.parseInt(st.nextToken());
        }
        //Arrays.sort(kid);

        //idea: 조(a b | c d e)를 끊을 때, 앞뒤 차이가 큰 원생들을 기준으로 끊는다

        int[] dif = new int[N-1];
        for (int i = 0; i<N-1;i++) {
            dif[i] = kid[i+1] - kid[i];
        }

        Arrays.sort(dif);
        
        long answer = 0;
        for (int i = 0; i<dif.length-(K-1);i++) {
            answer += dif[i];
        }
        
        System.out.println(answer);
    }
}
