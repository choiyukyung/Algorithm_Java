import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int w = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        ArrayList<Jewel> jewels = new ArrayList<>();
                
        for (int i = 0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(jewels);
        //Collections.reverse(jewels);

        int answer = 0;
        for (Jewel jewel : jewels) {
            if (w >= jewel.w) {
                answer += jewel.v * jewel.w;
                w -= jewel.w;
            } else {
                answer += jewel.v * w;
                break;
            }
        }

        System.out.println(answer);
    }

    static class Jewel implements Comparable<Jewel>{
        int w;
        int v;

        public Jewel(int w, int v) {
            this.w = w;
            this.v = v;
        }

        @Override
        public int compareTo(Jewel j) {
            return -Integer.compare(this.v, j.v);
        }

        
    }
}
