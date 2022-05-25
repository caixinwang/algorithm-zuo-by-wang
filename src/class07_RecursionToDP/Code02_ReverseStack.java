package class07_RecursionToDP;

import java.util.Stack;

public class Code02_ReverseStack {

    /**
     * 抓住栈底的元素，其它的元素往下沉。利用递归栈帮我们存信息。reverse函数控制不会传空栈
     *
     * @param stack
     * @return
     */
    private static int f(Stack<Integer> stack) {
        int res = stack.pop();

        if (stack.isEmpty()) {
            return res;//栈的最后一个元素返回
        } else {
            int last = f(stack);//最终会last会抓住最后一个元素
            stack.push(res);
            return last;//把最后一层递归抓到的栈底元素一路往上扔到最开始的递归
        }
    }

    /**
     * 从宏观的来看，空栈不用翻。先从栈底抽一个，然后把剩下的翻转，然后再放回去。
     * @param stack
     */
    private static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int p=f(stack);
        reverse(stack);
        stack.push(p);
    }

}
