import java.io.*;
import java.util.*;

public class Main15683 {
    static int n, m, answer;
    static int[][] arr;
    static ArrayList<int[]> cctvs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        ArrayList<int[]> cctv5 = new ArrayList<>();
        cctvs = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                arr[j][i] = Integer.parseInt(st.nextToken());
                if (arr[j][i] == 5) {
                    cctv5.add(new int[] { j, i });
                } else if (arr[j][i] > 0 && arr[j][i] < 6) {
                    cctvs.add(new int[] { arr[j][i], j, i });
                }
            }
        }

        for (int[] loc : cctv5) {
            // 미리 색칠
            colorUp(loc[0], loc[1]);
            colorDown(loc[0], loc[1]);
            colorLeft(loc[0], loc[1]);
            colorRight(loc[0], loc[1]);
        }

        answer = n * m;
        dfs(0);

        System.out.println(answer);

    }

    static void dfs(int cctvIdx) {
        if (cctvIdx == cctvs.size()) {
            answer = Math.min(answer, checkBlind());
            return;
        }

        int[] cctvNow = cctvs.get(cctvIdx);

        int[][] copy = new int[n][m];
        copy = copyArr(arr);

        switch (cctvNow[0]) {
            case 1:
                colorUp(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);

                colorDown(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);

                colorLeft(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);

                colorRight(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);
                break;

            case 2:
                colorUp(cctvNow[1], cctvNow[2]);
                colorDown(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);

                colorLeft(cctvNow[1], cctvNow[2]);
                colorRight(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);
                break;

            case 3:
                colorUp(cctvNow[1], cctvNow[2]);
                colorRight(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);

                colorRight(cctvNow[1], cctvNow[2]);
                colorDown(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);

                colorDown(cctvNow[1], cctvNow[2]);
                colorLeft(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);

                colorLeft(cctvNow[1], cctvNow[2]);
                colorUp(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);
                break;

            case 4:
                colorUp(cctvNow[1], cctvNow[2]);
                colorRight(cctvNow[1], cctvNow[2]);
                colorDown(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);

                colorRight(cctvNow[1], cctvNow[2]);
                colorDown(cctvNow[1], cctvNow[2]);
                colorLeft(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);

                colorDown(cctvNow[1], cctvNow[2]);
                colorLeft(cctvNow[1], cctvNow[2]);
                colorUp(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);

                colorLeft(cctvNow[1], cctvNow[2]);
                colorUp(cctvNow[1], cctvNow[2]);
                colorRight(cctvNow[1], cctvNow[2]);
                dfs(cctvIdx + 1);
                arr = copyArr(copy);
                break;

        }

    }

    static int[][] copyArr(int[][] original) {
        int[][] copy = new int[n][m];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                copy[j][i] = original[j][i];
            }
        }
        return copy;
    }

    static int checkBlind() {
        int count = 0;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (arr[j][i] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    static void colorUp(int x, int y) {
        for (int i = x; i >= 0; i--) {
            if (arr[i][y] != 0) {
                if (arr[i][y] == 6) {
                    return;
                }
            }
            arr[i][y] = 7; // 색칠
        }
    }

    static void colorDown(int x, int y) {
        for (int i = x; i < n; i++) {
            if (arr[i][y] != 0) {
                if (arr[i][y] == 6) {
                    return;
                }
            }
            arr[i][y] = 7; // 색칠
        }
    }

    static void colorLeft(int x, int y) {
        for (int i = y; i >= 0; i--) {
            if (arr[x][i] != 0) {
                if (arr[x][i] == 6) {
                    return;
                }
            }
            arr[x][i] = 7; // 색칠
        }
    }

    static void colorRight(int x, int y) {
        for (int i = y; i < m; i++) {
            if (arr[x][i] != 0) {
                if (arr[x][i] == 6) {
                    return;
                }
            }
            arr[x][i] = 7; // 색칠
        }
    }

}
