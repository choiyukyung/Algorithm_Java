import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2630 {
    static int[][] paper;
    static int countW = 0, countB = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        paper = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recursive(N, 0, 0);
        System.out.println(countW);
        System.out.println(countB);
    }

    static void recursive(int size, int r, int c) {
        if (check(size, r, c)) {
            if (paper[r][c] == 0) {
                countW++;
            } else {
                countB++;
            }
            return;
        }

        recursive(size / 2, r, c);
        recursive(size / 2, r + size / 2, c);
        recursive(size / 2, r, c + size / 2);
        recursive(size / 2, r + size / 2, c + size / 2);

    }

    static boolean check(int size, int r, int c) {
        int color = paper[r][c];
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (color != paper[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
