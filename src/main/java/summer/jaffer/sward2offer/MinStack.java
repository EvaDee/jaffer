package summer.jaffer.sward2offer;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 剑指offer
 * 30. 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 */
public class MinStack<T extends Comparable<T>> {
    Stack<T> minStack = new Stack<>();
    Stack<T> stack = new Stack<>();
    public void push(T node) {
        stack.push(node);
        if (minStack.isEmpty() || node.compareTo(minStack.peek()) <= 0)
            minStack.push(node);
    }

    public T pop() {
        T node = stack.pop();
        if (node.compareTo(minStack.peek()) == 0)
            minStack.pop();
        return node;
    }

    public T top() {
        return stack.pop();
    }

    public T min() {
        return minStack.peek();
    }
}
