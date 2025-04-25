import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2470 {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        if(arr[0]>0) {
            System.out.println(arr[0] + " " + arr[1]);
            return;
        } else if(arr[N-1]<0) {
            System.out.println(arr[N-2] + " " + arr[N-1]);
            return;
        }

        int min = Integer.MAX_VALUE;

        int end = N-1;
        int sum = 0;
        int indexS = 0;
        int indexE = N-1;
        for (int start = 0; start < end; ) {
            sum = arr[start] + arr[end];
            if(Math.abs(sum)<Math.abs(min)) {
                min = sum;
                indexS = start;
                indexE = end;
            }
            if(sum>0) {
                end--;
            } else {
                start++;
            }
        }

        System.out.println(arr[indexS] + " " + arr[indexE]);
    }
}
