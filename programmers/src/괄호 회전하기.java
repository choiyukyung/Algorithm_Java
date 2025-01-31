import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        //회전
        for(int i = 0; i<s.length(); i++) {
            String change = s.substring(i, s.length()) + s.substring(0, i);
            
            if(check(change.toCharArray())) {
                answer++;
            }
        }
        
        return answer;
    }
    
    static boolean check(char[] arr) {
        
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0;i<arr.length;i++) {
            char c = arr[i];
            if (c == '(' || c == '{' || c == '[') {
                stack.add(c);
            } else {
                if(stack.isEmpty()) return false;
                char o = stack.pop();
                if(!((o == '(' && c == ')') || 
                    (o == '{' && c == '}') || 
                    (o == '[' && c == ']'))) return false;
            }
        }
        
        if(stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
