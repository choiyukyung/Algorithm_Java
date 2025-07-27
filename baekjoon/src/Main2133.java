import java.io.*;
import java.util.*;

public class Main2133 {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        scan.close();
        
        int[] dp = new int[N+1];

        if(N==1) {
            System.out.println(0);
            return;
        } else if(N%2==1) {
            System.out.println(0);
            return;
        }

        dp[2] = 3;
        
        dp[0] = 1;
        //dp[6] = dp[4]*3 + dp[2]*2 + dp[0]*2;
        //기존구조*2개 추가 + 기존과 결합한 새로운 구조 ... + 완전히 새로운 구조
        for (int i = 4; i <= N; i += 2) {
            dp[i] = dp[i - 2] * 3;
            for (int j = 4; j <= i; j += 2) {
                dp[i] += dp[i - j] * 2;
            }
        }
        
        System.out.println(dp[N]);
    }
}
