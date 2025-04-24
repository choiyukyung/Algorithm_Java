import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main1107 {
    static int N, M;
    static ArrayList<Integer> broken;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
    
        broken = new ArrayList<>();
        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                broken.add(Integer.parseInt(st.nextToken()));
            }
        }
    
        int answer = Math.abs(100 - N);
    
        for (int i = 0; i <= 999999; i++) {
            String str = Integer.toString(i);
            boolean possible = true;
            for (int j = 0; j < str.length(); j++) {
                if (broken.contains(str.charAt(j) - '0')) {
                    possible = false;
                    break;
                }
            }
    
            if (possible) {
                int pressCount = str.length() + Math.abs(N - i);  // 숫자 누른 수 + 이동
                answer = Math.min(answer, pressCount);
            }
        }
    
        System.out.println(answer);
    }
}
