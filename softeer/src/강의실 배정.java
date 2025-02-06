import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        
        int[][] arr = new int[n][2];
        for(int i = 0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> o1[1] - o2[1]);

        int end = 0, answer = 0;
        for(int[] c : arr) {
            if(end<=c[0]) {
                end = c[1];
                answer++;
            }
        }
        System.out.println(answer);

    }
}
