import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main1092 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        
        int[] car = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i<N;i++) {
            car[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        LinkedList<Integer> list = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i<M;i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(car);
        Collections.sort(list);

        if(car[N-1]<list.getLast()) {
            System.out.println(-1);
            return;
        }

        int time = 0;
        while(!list.isEmpty()) {
            time++;
            int carIdx = N-1;
            for (int i = list.size()-1; i>=0;i--) {
                if(carIdx<0) break;
                if(car[carIdx]<list.get(0)) break;
                if(car[carIdx]>=list.get(i)) {
                    list.remove(i);
                    carIdx--;
                }
            }
        }

        
        System.out.println(time);
    }
}
