import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main14500 {
    static int n,m, answer;
    static int[][] board;
    static ArrayList<int[][]> list;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // input
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //블럭 만들기
        list = new ArrayList<>();
        int[][] arr = new int[4][2];
        block1to4(1, arr);

        // ㅜ 모양
        int[][] arr1 = {{0, 0}, {0, 1}, {0, 2}, {1, 1}};
        int[][] arr2 = {{0, 1}, {1, 0}, {1, 1}, {2, 1}};
        int[][] arr3 = {{1, 0}, {1, 1}, {1, 2}, {0, 1}};
        int[][] arr4 = {{0, 0}, {1, 0}, {2, 0}, {1, 1}};

        list.add(arr1);
        list.add(arr2);
        list.add(arr3);
        list.add(arr4);
        

        //각 블럭 계산(40개 1*4*4*3 + 4)
        //System.out.println(list.size());

        int answer = 0;
        for(int[][] l : list) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                   int sum = sum(l, i, j);
                   answer = Math.max(answer, sum);
                }
            }
        }
        System.out.println(answer);


    }

    static int sum(int[][] arr, int x, int y) {
        int sum = 0;
        for(int i = 0; i<arr.length; i++) {
            if((arr[i][0] + x)<0 || (arr[i][1] + y)<0 || (arr[i][0] + x)>=n || (arr[i][1] + y)>=m) {
                return 0;
            }
            sum += board[arr[i][0] + x][arr[i][1] + y];
        }
        return sum;

    }

    static void block1to4(int depth, int[][] arr) {

        if(depth == 4) {
            //deep copy로!
            int[][] copy = new int[4][2];
            for (int i = 0; i < 4; i++) {
                copy[i][0] = arr[i][0];
                copy[i][1] = arr[i][1];
            }
            list.add(copy);
            return;
        }
        int x = arr[depth-1][0];
        int y = arr[depth-1][1];

        for(int i = 0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(depth>1 && nx == arr[depth-2][0] && ny == arr[depth-2][1]) continue;

            arr[depth][0] = nx;
            arr[depth][1] = ny;
            block1to4(depth+1, arr);
            
        }
    }
}
