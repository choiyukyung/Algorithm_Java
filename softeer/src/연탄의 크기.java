import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int answer = 0;
        int r = 2;
        while(true) {
            int count = 0;
            for(int i = 0; i<n; i++) {
                if(arr[i]%r==0) {
                    count++;
                }
            }
            answer = Math.max(answer, count);
            r++;
            if(n==answer || arr[n-answer-1]<r) {
                break;
            }
        }
        System.out.println(answer);
    }
}
