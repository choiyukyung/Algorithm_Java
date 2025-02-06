import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        int[] rangeSum = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i<=n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            rangeSum[i] = rangeSum[i-1] + arr[i];
        }

        for (int i = 1; i<=k; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            double avg = (double)(rangeSum[end] - rangeSum[start-1]);
            avg = avg / (end - start + 1);
            System.out.printf("%.2f\n", avg);
        }
        
    }
}
