import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main11509 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[] balloon = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            balloon[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> arrowH = new ArrayList<>();

        for(int i = 0; i<N; i++) {
            if(arrowH.contains(balloon[i])) {
                arrowH.remove(new Integer(balloon[i]));
            }
            arrowH.add(balloon[i]-1);
        }

        System.out.println(arrowH.size());


    }
    
}
