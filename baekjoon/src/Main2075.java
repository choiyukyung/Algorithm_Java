
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main2075 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        // 단순 정렬X -> n이 아닌 n^2을 정렬하기 때문에 O(nlogn)이 아닌 O(n^2logn^2)
        // 값을 저장하면서 바로 정렬
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n * n; i++) {
            queue.add(scan.nextInt());
        }

        for (int i = 0; i < n - 1; i++) {
            queue.poll();
        }

        System.out.println(queue.poll());
        scan.close();
    }
}
