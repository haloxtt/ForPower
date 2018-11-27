import java.util.Stack;

/**
 * @description:150.逆波兰表达式求值
 * @author: xietaotao
 * @create: 2018-11-16 11:14
 * ["2", "1", "+", "3", "*"]
输出: 9
解释: ((2 + 1) * 3) = 9
 **/
public class EvalRPN {

    public int evalRPN(String[] tokens) {
        if (tokens.length < 2)
            return Integer.valueOf(tokens[0]);
        Stack<String> stack = new Stack<>();
        int i = 2;
        stack.push(tokens[0]);
        stack.push(tokens[1]);
        while (!stack.empty() && i < tokens.length) {
            if ("+".equals(tokens[i])) {
                int temp = Integer.valueOf(stack.pop()) + Integer.valueOf(stack.pop());
                stack.push(String.valueOf(temp));
            } else if ("-".equals(tokens[i])) {
                int num1 = Integer.valueOf(stack.pop());
                int num2 = Integer.valueOf(stack.pop());
                int temp =  num2 - num1;
                stack.push(String.valueOf(temp));
            } else if ("*".equals(tokens[i])) {
                int temp = Integer.valueOf(stack.pop()) * Integer.valueOf(stack.pop());
                stack.push(String.valueOf(temp));
            } else if ("/".equals(tokens[i])) {
                int num1 = Integer.valueOf(stack.pop());
                int num2 = Integer.valueOf(stack.pop());
                int temp =  num2 / num1;
                stack.push(String.valueOf(temp));
            } else {
                stack.push(tokens[i]);
            }
            i++;
        }
        return Integer.valueOf(stack.peek());
    }

    public static void main(String[] args) {
        String[] str = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        EvalRPN evalRPN = new EvalRPN();
        System.out.println(evalRPN.evalRPN(str));
    }
}
