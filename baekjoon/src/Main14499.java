import java.io.*;
import java.util.*;

public class Main14499 {
    static int n, m, x, y, k;
    static int[] dice;
    static int[][] map;

    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] command = new int[k];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            command[i] = Integer.parseInt(st.nextToken());
        }

        dice = new int[7];

        // 처음 인덱스가 123456이라고 할때 굴린 인덱스를 배열에 넣어놓고 map
        // [1]은 위 방향, [6]은 아래 방향(문제에서 그려진 그림 숫자대로 위치별 숫자 붙임)
        int[] roll_east = { 0, 4, 2, 1, 6, 5, 3 };
        int[] roll_west = { 0, 3, 2, 6, 1, 5, 4 };
        int[] roll_south = { 0, 2, 6, 3, 4, 1, 5 };
        int[] roll_north = { 0, 5, 1, 3, 4, 6, 2 };

        for (int i = 0; i < k; i++) {
            if (moveMap(command[i])) {
                switch (command[i]) {
                    case 1:
                        roll_dice(roll_east);
                        break;
                    case 2:
                        roll_dice(roll_west);
                        break;
                    case 3:
                        roll_dice(roll_north);
                        break;
                    case 4:
                        roll_dice(roll_south);
                        break;
                }
                copyMapOrDice();
                System.out.println(dice[1]);
            }
        }

    }

    static void roll_dice(int[] roll_map) {
        int[] swapDice = new int[7];

        for (int i = 1; i < 7; i++) {
            swapDice[i] = dice[roll_map[i]];
        }

        dice = swapDice.clone(); // 값만복사
    }

    // 지도에서 옮기기
    static boolean moveMap(int cmd) {
        int nx = x + dx[cmd - 1];
        int ny = y + dy[cmd - 1];

        if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
            return false;
        }

        x = nx;
        y = ny;

        return true;
    }

    // 지도와 주사위 값을 적절히 복사하기
    static void copyMapOrDice() {
        if (map[x][y] == 0) {
            map[x][y] = dice[6];
        } else {
            dice[6] = map[x][y];
            map[x][y] = 0;
        }
    }

}
