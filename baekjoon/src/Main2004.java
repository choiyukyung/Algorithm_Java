import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        m = Math.max(m,n-m);

        if(n==m) {
            System.out.println(0);
            return;
        }

        int two = 0;
        int five = 0;

        long start = m+1;
        if((m+1)%2 != 0){
            start = m+2;
        }
        
        for(long i = start; i<=n; i+=2) {
            long now = i;
            while(now%2==0) {
                now /= 2;
                two++;
            }
        }
        for(long i = 2; i<=n-m; i+=2) {
            long now = i;
            while(now%2==0) {
                now /= 2;
                two--;
            }
        }

        start = m+1;
        while(start%5 != 0){
            start++;
        }

        for(long i = start; i<=n; i+=5) {
            long now = i;
            while(now%5==0) {
                now /= 5;
                five++;
            }
        }
        for(long i = 5; i<=n-m; i+=5) {
            long now = i;
            while(now%5==0) {
                now /= 5;
                five--;
            }
        }

        System.out.println((two<five)?two:five);

    }
}
