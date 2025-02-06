import java.io.*;
import java.util.*;
//A+B
public class AplusB {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int T = scan.nextInt();

        for(int tc = 1; tc<=T; tc++) {
            int A = scan.nextInt();
            int B = scan.nextInt();

            System.out.println("Case #" + tc + ": " + (A+B));
        }
    }
}
