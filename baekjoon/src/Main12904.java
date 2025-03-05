import java.util.Scanner;

public class Main12904 {
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        String S = scan.next();
        String T = scan.next();

        StringBuilder t = new StringBuilder(T);

        while(S.length()<t.length()) {
            if(t.charAt(t.length()-1) == 'B'){
                t.delete(t.length()-1, t.length());
                t.reverse();
            } else {
                t.delete(t.length()-1, t.length());
            }
        }
        T = t.toString();        

        System.out.println((S.equals(T))?1:0);

        scan.close();

    }
}