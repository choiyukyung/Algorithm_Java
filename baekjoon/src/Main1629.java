import java.util.Scanner;

public class Main1629 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        long a = scan.nextLong();
        long b = scan.nextLong();
        long c = scan.nextLong();
        scan.close();

        long answer = 1;

        a = a % c;
        while(b>0) {
            if (b % 2 == 1) {
                answer = (answer * a) % c;
            }
            a = (a * a) % c;
            b /= 2; 
        }

        System.out.println(answer);
    }
}
