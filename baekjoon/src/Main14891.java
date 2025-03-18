import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main14891 {
    static ArrayList<LinkedList<Integer>> wheelList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        wheelList = new ArrayList<>();
        
        for(int i = 0; i<4;i++) {
            wheelList.add(new LinkedList<>());
            String[] str = br.readLine().split("");
            for (String s : str) {
                wheelList.get(i).add(Integer.parseInt(s));
            }
        }

        int k = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int i = 0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken());

            //연쇄 회전
            int[] r = new int[4];
            r[w] = d;

            // 오른쪽
            for (int j = w; j < 3; j++) {
                if (wheelList.get(j).get(2) != wheelList.get(j + 1).get(6)) {
                    r[j + 1] = r[j] * -1;
                } else {
                    break;
                }
            }
            
            // 왼쪽
            for (int j = w; j > 0; j--) {
                if (wheelList.get(j).get(6) != wheelList.get(j - 1).get(2)) {
                    r[j - 1] = r[j] * -1;
                } else {
                    break;
                }
            }

            //회전
            rotate(r);

        }

        int answer = 0;
        answer += wheelList.get(0).get(0)*1;
        answer += wheelList.get(1).get(0)*2;
        answer += wheelList.get(2).get(0)*4;
        answer += wheelList.get(3).get(0)*8;
        
        System.out.println(answer);

    }

    static void rotate(int[] r) {
        for(int i = 0; i<r.length;i++) {
            if(r[i]==1) { // 시계
                int e = wheelList.get(i).removeLast();
                wheelList.get(i).addFirst(e);
            } else if(r[i]==-1) { // 반시계
                int e = wheelList.get(i).removeFirst();
                wheelList.get(i).addLast(e);
            }
        }
    }

}
