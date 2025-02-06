import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        char[] arr = st.nextToken().toCharArray();
        boolean[] check = new boolean[n];

        int answer = 0;
        for(int i = 0; i<n; i++) {
            if(arr[i] == 'P') {
                int checkIdx = -1;
                for(int j = i-1; (j>=i-k && j>=0); j--) {
                    if(arr[j] == 'H' && check[j] == false) {
                        checkIdx = j;
                    }
                }
                if(checkIdx == -1) {
                    for(int j = i+1; (j<=i+k && j<n); j++) {
                        if(arr[j] == 'H' && check[j] == false) {
                            checkIdx = j;
                            break;
                        }
                    }
                }
                if(checkIdx != -1) {
                    answer++;
                    check[checkIdx] = true;
                }
            }
        }

        System.out.println(answer);
    }
}
