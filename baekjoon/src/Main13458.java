import java.io.*;
import java.util.*;

public class Main13458 {
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        long answer = 0;
        for (int i = 0; i < n; i++) {
            int stu = arr[i];
            // 총감독관
            stu -= b;
            answer++;
            // 부감독관
            if (stu > 0) {
                answer += stu / c;
                stu -= (stu / c) * c;
                answer += stu > 0 ? 1 : 0;
            }
        }

        System.out.println(answer);

    }

}
