import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main5464 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        int[] bill = new int[n+1];
        int[] car = new int[m+1];

        for (int i = 1; i <= n; i++) {
            bill[i] = scan.nextInt();
        }

        for (int i = 1; i <= m; i++) {
            car[i] = scan.nextInt();
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] park = new int[m+1]; //차가 어떤 위치에 주차되어있는지 저장
        for (int i = 1; i <= n; i++) {
            pq.add(i);
        }

        Queue<Integer> wait = new LinkedList<>();
        int answer = 0;
        for (int i = 0; i < 2*m; i++) {
            int cmd = scan.nextInt();
            if(cmd>0) {
                if(pq.isEmpty()) {
                    wait.add(cmd);
                } else {
                    park[cmd] = pq.poll();
                    answer += car[cmd] * bill[park[cmd]];
                }
            } else {
                pq.add(park[cmd*(-1)]);
                if(!wait.isEmpty()){
                    cmd = wait.poll();
                    park[cmd] = pq.poll();
                    answer += car[cmd] * bill[park[cmd]];
                }
            }
        }
        scan.close();
        System.out.println(answer);
    }
}
