package class18_InterviewCodings.InterviewCoding13;

import java.util.Stack;

public class Code03_ExpressionCompute {
    public static int getValue(String str) {
        return value(str.toCharArray(),0)[0];
    }


    /**
     * 这个递归不用写明显的base case，因为在传入的时候会保证表达式是合法的，因为递归在while里面，是在从左往右
     * 遍历的过程中进入递归的，总有进入最后一次递归的时候，进入递归是为了消去所有的左括号，所以不可能你进入到递归
     * 以后立马面对右括号这种极端情况。因为这种递归不是那种无脑枚举的递归，能进入到递归的条件本身就是苛刻的，所以
     * 不用担心边界条件
     *
     * @param str 表达式
     * @param i   从i往后算到一个合适的位置
     * @return 返回算到了哪一个位置停了，返回算到那个位置答案是多少[ans,index]
     */
    public static int[] value(char[] str, int i) {
        int cur = 0;//利用cur=10*cur+(str[i]-'1')，从高位到低位累加出数字
        int N = str.length;
        Stack<Integer> stack = new Stack<>();
        while (i < N && str[i] != ')') {
            if ('0' <= str[i] && str[i] <= '9') {
                cur = 10 * cur + str[i++] - '0';
            } else if (str[i] == '/' || str[i] == '*' || str[i] == '+' || str[i] == '-') {
                if (!stack.isEmpty()&&stack.peek() == '/') {
                    stack.pop();//除号先弹出来
                    stack.push(stack.pop() / cur);
                } else if (!stack.isEmpty()&&stack.peek() == '*') {
                    stack.pop();//乘号先弹出来
                    stack.push(stack.pop() * cur);
                } else {
                    stack.push(cur);
                }
                cur=0;
                stack.push((int) str[i++]);
            } else {
                int[] value = value(str, i + 1);
                cur = value[0];
                i=value[1]+1;
            }
        }
        stack.push(cur);
        while(stack.size()>1){//a op b
            int b=stack.pop();
            int op=stack.pop();
            int a=stack.pop();
            if (op=='+'){
                stack.push(a+b);
            }else {//'-'
                stack.push(a-b);
            }
        }
        return new int[]{stack.pop(),i};
    }

    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp));

        exp = "10-5*3";
        System.out.println(getValue(exp));

        exp = "-3*4";
        System.out.println(getValue(exp));

        exp = "3+1*4";
        System.out.println(getValue(exp));

    }

}
