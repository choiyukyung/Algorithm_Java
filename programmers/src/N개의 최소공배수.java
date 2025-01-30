class Solution {
    public int solution(int[] arr) {
        int a = arr[0];
        
        for(int i = 1; i<arr.length; i++) {
            int b = arr[i];
            int gcd = 1;
            
            if(a>b) gcd = euclidean(a, b);
            else gcd = euclidean(b, a);
            
            a = a*b / gcd;
        }
        
        return a;
    }
    
    static int euclidean(int a, int b) {
        while(b>0) {
            int r = a%b;
            a = b;
            b = r;
        }
        return a;
    }
}
