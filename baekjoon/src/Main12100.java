import java.io.*;
import java.util.*;

public class Main12100 {
    static int n, answer;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        dfs(0, board);

        System.out.println(answer);

    }

    static void dfs(int depth, int[][] arrNow) {
        if (depth == 5) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    answer = Math.max(answer, arrNow[i][j]);
                }
            }
            return;
        }

        int[][] arr2down = copy(arrNow);
        moveDown(arr2down);
        dfs(depth + 1, arr2down);

        int[][] arr2up = copy(arrNow);
        moveUp(arr2up);
        dfs(depth + 1, arr2up);

        int[][] arr2left = copy(arrNow);
        moveLeft(arr2left);
        dfs(depth + 1, arr2left);

        int[][] arr2right = copy(arrNow);
        moveRight(arr2right);
        dfs(depth + 1, arr2right);

    }

    private static void moveLeft(int[][] arr) {

        for (int i = 0; i < n; i++) {
            // 가로 한 줄씩 왼쪽부터 보면서 하나씩 왼쪽으로 당기기
            boolean merge = false;
            int index = -1;
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 0) {
                    continue;
                } else {
                    int temp = arr[i][j];
                    arr[i][j] = 0;
                    if (index > -1) {
                        if (!merge && arr[i][index] == temp) {
                            // 병합
                            arr[i][index] *= 2;
                            merge = true;
                        } else {
                            // 병합X
                            index++;
                            arr[i][index] = temp;
                            merge = false;
                        }
                    } else {
                        index++;
                        arr[i][index] = temp;
                        merge = false;
                    }
                }
            }

        }
    }

    private static void moveRight(int[][] arr) {

        for (int i = 0; i < n; i++) {
            boolean merge = false;
            int index = n;
            for (int j = n - 1; j >= 0; j--) {
                if (arr[i][j] == 0) {
                    continue;
                } else {
                    int temp = arr[i][j];
                    arr[i][j] = 0;
                    if (index < n) {
                        if (!merge && arr[i][index] == temp) {
                            // 병합
                            arr[i][index] *= 2;
                            merge = true;
                        } else {
                            // 병합X
                            index--;
                            arr[i][index] = temp;
                            merge = false;
                        }
                    } else {
                        index--;
                        arr[i][index] = temp;
                        merge = false;
                    }
                }
            }

        }
    }

    private static void moveUp(int[][] arr) {

        for (int j = 0; j < n; j++) {
            // 세로 한 줄씩 위쪽부터 보면서 위쪽으로 당기기
            boolean merge = false;
            int index = -1;
            for (int i = 0; i < n; i++) {
                if (arr[i][j] == 0) {
                    continue;
                } else {
                    int temp = arr[i][j];
                    arr[i][j] = 0;
                    if (index > -1) {
                        if (!merge && arr[index][j] == temp) {
                            // 병합
                            arr[index][j] *= 2;
                            merge = true;
                        } else {
                            // 병합X
                            index++;
                            arr[index][j] = temp;
                            merge = false;
                        }
                    } else {
                        index++;
                        arr[index][j] = temp;
                        merge = false;
                    }
                }
            }

        }
    }

    private static void moveDown(int[][] arr) {

        for (int j = 0; j < n; j++) {
            // 세로 한 줄씩 위쪽부터 보면서 위쪽으로 당기기
            boolean merge = false;
            int index = n;
            for (int i = n - 1; i >= 0; i--) {
                if (arr[i][j] == 0) {
                    continue;
                } else {
                    int temp = arr[i][j];
                    arr[i][j] = 0;
                    if (index < n) {
                        if (!merge && arr[index][j] == temp) {
                            // 병합
                            arr[index][j] *= 2;
                            merge = true;
                        } else {
                            // 병합X
                            index--;
                            arr[index][j] = temp;
                            merge = false;
                        }
                    } else {
                        index--;
                        arr[index][j] = temp;
                        merge = false;
                    }
                }
            }

        }
    }

    static int[][] copy(int[][] arr) {
        int[][] copy = new int[arr.length][arr[0].length];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                copy[i][j] = arr[i][j];
            }
        }

        return copy;

    }

}
