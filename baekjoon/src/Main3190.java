import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main3190 {
    static int n, k, l, answer;
    static int[][] board, changeD;
    static ArrayList<int[][]> list;
    static int[] rx = {0,1,0,-1};// 오른쪽 회전
    static int[] ry = {1,0,-1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // input
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        k = Integer.parseInt(st.nextToken());

        board = new int[n+1][n+1];
        
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x][y] = -1;
        }

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());

        changeD = new int[l][2];

        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            changeD[i][0] = Integer.parseInt(st.nextToken());
            if(st.nextToken().equals("D")){
                //오른쪽
                changeD[i][1] = 2;
            } else {
                //왼쪽
                changeD[i][1] = 1;
            }
        }
        
        //움직이기
        int idx = 1;
        int idy = 1;
        int s = 0;
        int dx = 0;
        int dy = 1;
        int changeIdx = 0;
        int apple = 0;
        while(true) {
            s++;

            if(changeIdx<changeD.length && s-1==changeD[changeIdx][0]) {
                if(changeD[changeIdx][1] == 1) { //좌측회전
                    for(int i = 0; i<4;i++) {
                        if(rx[i]==dx && ry[i]==dy) {
                            dx = rx[(i+3)%4];
                            dy = ry[(i+3)%4];
                            break;
                        }
                    }
                } else {
                    for(int i = 0; i<4;i++) {
                        if(rx[i]==dx && ry[i]==dy) { //우측회전
                            dx = rx[(i+1)%4];
                            dy = ry[(i+1)%4];
                            break;
                        }
                    }
                }
                changeIdx++;
            }
            idx = idx + dx;
            idy = idy + dy;

            if(idx<=0 || idy<= 0 || idx>n || idy>n) { // 벽
                System.out.println(s);
                return;
            }

            if(board[idx][idy]!=0 && s<=board[idx][idy]+apple+1) {// 자기 자신의 몸
                System.out.println(s);
                return;
            }

            if(board[idx][idy] == -1) {
                apple++;
            }
            board[idx][idy] = s;

        }
        
    }
}
