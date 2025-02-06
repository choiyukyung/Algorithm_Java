import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[3][3];
        for(int i = 0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 3*3;
        for(int i = 0; i<3; i++) {
            int[] temp = new int[3];
            temp = Arrays.copyOf(arr[i], arr[i].length);
            Arrays.sort(temp);
            int dif = temp[2] - temp[0];
            answer = Math.min(answer, dif);
        }

        for(int i = 0; i<3; i++) {
            int[] temp = new int[3];
            for(int j = 0; j<3; j++) {
                temp[j] = arr[j][i];
            }
            Arrays.sort(temp);
            int dif = temp[2] - temp[0];
            
            answer = Math.min(answer, dif);
        }

        System.out.println(answer);
    }
}
