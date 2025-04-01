import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main14888 {
    static int N, min, max;
    static int[] number, op;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        number = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i<N;i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        op = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i<4;i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
        
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        dfs(0, number[0]);
        
        System.out.println(max);
        System.out.println(min);
    }

    static void dfs(int depth, int cal) {
        if(depth == N-1) {
            min = Math.min(min, cal);
            max = Math.max(max, cal);
        }
        int i = 0;
        if(op[i]>0) {
            op[i]--;
            dfs(depth+1, cal+number[depth+1]);
            op[i]++;
        }
        i = 1;
        if(op[i]>0) {
            op[i]--;
            dfs(depth+1, cal-number[depth+1]);
            op[i]++;
        }
        i = 2;
        if(op[i]>0) {
            op[i]--;
            dfs(depth+1, cal*number[depth+1]);
            op[i]++;
        }
        i = 3;
        if(op[i]>0) {
            op[i]--;
            dfs(depth+1, cal/number[depth+1]);
            op[i]++;
        }

    }
}
