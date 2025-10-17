import java.io.*;
import java.util.*;

public class Main20055 {
    static int n, k;
    static int[][] arr;
    static int upIdx, downIdx;
    static int arr0cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[2 * n + 1][2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < 2 * n + 1; i++) {
            arr[i][0] = Integer.parseInt(st.nextToken());
        }

        arr0cnt = 0;
        upIdx = 1;
        downIdx = n;

        int answer = 0;

        while (arr0cnt < k) {
            answer++;

            // 1. belt 회전
            rotateBelt();

            // 2. 로봇 순서대로 이동
            moveRobot();

            // 3. 첫칸에 로봇 올리기
            putRobot();
        }

        System.out.println(answer);

    }

    private static void putRobot() {
        if (arr[upIdx][0] > 0) {
            arr[upIdx][1] = 1;
            arr[upIdx][0]--;

            if (arr[upIdx][0] == 0)
                arr0cnt++;
        }
    }

    private static void moveRobot() {


        for(int i = n-1; i >= 1; i--) {
            if(arr[i][1]!=0 && arr[i+1][1]==0 && arr[i+1][0] > 0) {
                arr[i][1]=0;
                arr[i+1][1]=1;
                arr[i+1][0]--;

                if(arr[i+1][0] == 0)
                    arr0cnt++;
            }
        }

        arr[n][1] = 0;


    }

    private static void rotateBelt() {
        int befB = arr[2*n][0];
        int befR = arr[2*n][1];
        for(int i = 2*n; i>1; i--) {
            arr[i][0] = arr[i-1][0];
            arr[i][1] = arr[i-1][1];
        }
        arr[1][0] = befB;
        arr[1][1] = befR;

        arr[n][1] = 0;
    }

}
