import java.io.*;
import java.util.*;

public class Main14890 {
    static int n, l;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean[] row = new boolean[n];
        boolean[] col = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            boolean[] slope = new boolean[n];
            int j = 1;
            for (j = 1; j < n; j++) {
                int dif = Math.abs(arr[i][j] - arr[i][j - 1]);
                if (dif == 1) { // 계단 있음

                    if(arr[i][j] < arr[i][j - 1]) { // 내려가는 경우
                        // L만큼 동일 칸 유지
                        if(j+l-1>n-1) break; // 경사로 놓을 자리 없음

                        slope[j] = true;
                        int k = j+1;
                        for (k = j+1; k <= j+l-1; k++) {
                            if(arr[i][k]!=arr[i][k-1]) break;
                            slope[k] = true;
                        }
                        if(k==j+l) { // 경사로 잘 놓음
                            j = k-1;
                        } else { // 단차로 경사로 못 놓음
                            break; 
                        }
                    } else if(arr[i][j] > arr[i][j - 1]) { // 올라가는 경우
                        // L만큼 동일 칸 유지
                        if(j-l<0) break; // 경사로 놓을 자리 없음

                        if(slope[j-1]) break;
                        slope[j-1] = true;
                        int k = j-2;
                        for (k = j-2; k >= j-l; k--) {
                            if(slope[k] || arr[i][k]!=arr[i][k+1]) break;
                            slope[k] = true;
                        }
                        if(k!=j-l-1) { // 단차 또는 경사로 겹침으로 경사로 못 놓음
                            break; 
                        } // 경사로 잘 놓음
                    }

                } else if (dif > 1) { // 계단 너무 큼
                    break; // backtracking
                }
            }
            if (j == n) { // 단차가 없거나 경사로 잘 놓을 수 있는 케이스
                row[i] = true;
                answer++;
            }
        }

        for (int i = 0; i < n; i++) {
            boolean[] slope = new boolean[n];
            int j = 1;
            for (j = 1; j < n; j++) {
                int dif = Math.abs(arr[j][i] - arr[j - 1][i]);
                if (dif == 1) { // 계단 있음

                    if(arr[j][i] < arr[j - 1][i]) { // 내려가는 경우
                        // L만큼 동일 칸 유지
                        if(j+l-1>n-1) break; // 경사로 놓을 자리 없음

                        slope[j] = true;
                        int k = j+1;
                        for (k = j+1; k <= j+l-1; k++) {
                            if(arr[k][i]!=arr[k-1][i]) break;
                            slope[k] = true;
                        }
                        if(k==j+l) { // 경사로 잘 놓음
                            j = k-1;
                        } else { // 단차로 경사로 못 놓음
                            break; 
                        }
                    } else if(arr[j][i] > arr[j - 1][i]) { // 올라가는 경우
                        // L만큼 동일 칸 유지
                        if(j-l<0) break; // 경사로 놓을 자리 없음

                        if(slope[j-1]) break;
                        slope[j-1] = true;
                        int k = j-2;
                        for (k = j-2; k >= j-l; k--) {
                            if(slope[k] || arr[k][i]!=arr[k+1][i]) break;
                            slope[k] = true;
                        }
                        if(k!=j-l-1) { // 단차 또는 경사로 겹침으로 경사로 못 놓음
                            break; 
                        } // 경사로 잘 놓음
                    }

                } else if (dif > 1) { // 계단 너무 큼
                    break; // backtracking
                }
            }
            if (j == n) { // 단차가 없거나 경사로 잘 놓을 수 있는 케이스
                col[i] = true;
                answer++;
            }
        }

        // 확인용코드
        // for (int i = 0; i < n; i++) {
        //     System.out.print((row[i]?1:0) + " ");
        // }
        // System.out.println();
        // for (int i = 0; i < n; i++) {
        //     System.out.print((col[i]?1:0) + " ");
        // }
        // System.out.println();

        System.out.println(answer);

    }

}
