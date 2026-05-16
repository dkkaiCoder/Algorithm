package backTracking.classicRecursion;

/**
 * 用递归函数逆序栈
 */

import java.util.Stack;

public class reverseStackWithRecursive {
    //栈底元素移除掉，上面的元素盖下来
    //返回移除掉的栈底元素
    public static int bottomOut(Stack<Integer> stack){
        int num=stack.pop();
        if(stack.isEmpty()){
            return num;
        }else{
            int last=bottomOut(stack);
            stack.push(num);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int num=bottomOut(stack);
        reverse(stack);
        stack.push(num);
    }

    public static void main(String[] args){
        Stack<Integer> stack=new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        //reverse(stack);
        for(int i=0,size=stack.size();i<size;i++){
            System.out.println(stack.pop());
        }
    }
}
