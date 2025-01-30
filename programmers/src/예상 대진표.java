class Solution
{
    public int solution(int n, int a, int b)
    {
        while(true) {
            
            int aexp = range(a);
            int bexp = range(b);

            if(aexp != bexp) {
                return Math.max(aexp, bexp);
            }
            
            a -= Math.pow(2, aexp-1);
            b -= Math.pow(2, bexp-1);
        }

    }
    
    //2^6<n<=2^7 ==> 7
    static int range(int n) {
        
        int exp = 0;
        int num = 1;
        
        while(n>num) {
            num *= 2;
            exp++;
        }
        
        return exp;
    }
}