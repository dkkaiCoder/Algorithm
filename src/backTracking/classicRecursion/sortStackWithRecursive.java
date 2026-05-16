package backTracking.classicRecursion;
/**
 * 用递归函数排序栈
 * 栈只提供push、pop、isEmpty三个方法
 * 请完成无序栈的排序，要求排完序之后，从栈顶到栈底从小到大
 * 只能使用栈提供的push、pop、isEmpty三个方法、以及递归函数
 * 除此之外不能使用任何的容器，数组也不行
 */

import java.util.Stack;

public class sortStackWithRecursive {

    public static void sort(Stack<Integer> stack){
        int deep=deep(stack);
        while(deep>0){
            int max=max(stack,deep);
            int times=times(stack,deep,max);
            down(stack,deep,max,times);
            deep-=times;
        }
    }


    //返回栈的深度
    //不改变栈的数据状况
    public static int deep(Stack<Integer> stack){
        if(stack.isEmpty()){
            return 0;
        }
        int num=stack.pop();
        int deep=1+deep(stack);
        stack.push(num);
        return deep;
    }

    //从栈当前顶部开始 往下数deep层
    //返回这deep层的最大值
    public static int max(Stack<Integer> stack,int deep){
        if(deep==0){
            return Integer.MIN_VALUE;
        }
        int num=stack.pop();
        int restMax=max(stack,deep-1);
        int max=Math.max(num,restMax);
        stack.push(num);
        return max;
    }

    //从栈当前的顶部开始，往下数deep层，已知最大值是max了
    //返回，max出现了几次，不改变栈的数据状况
    public static int times(Stack<Integer> stack,int deep,int max){
        if(deep==0){
            return 0;
        }
        int num=stack.pop();
        int restTimes=times(stack,deep-1,max);
        int times=(num==max?1:0)+restTimes;
        stack.push(num);
        return times;
    }

    // 从栈当前的顶部开始，往下数deep层，已知最大值是max，出现了k次
    // 请把这k个最大值沉底，剩下的数据状况不变
    public static void down(Stack<Integer> stack,int deep,int max,int times){
        if(deep==0){
            for(int i=0;i<times;i++){
                stack.push(max);
            }
            return;
        }
        int num=stack.pop();
        down(stack,deep-1,max,times);
        if(num!=max){
            stack.push(num);
        }
    }

    public static void main(String[] args){
        Stack<Integer> stack=new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(5);
        stack.push(4);
        stack.push(6);
        stack.push(2);
        sort(stack);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }

        //下面使用对数器进行验证
        int N=20;
        int V=10000;
        int testTimes=10000;
        System.out.println("测试开始");
        for(int i=0;i<testTimes;i++){
            int n=(int)(Math.random()*N);
            Stack<Integer> s=randomStack(n,V);
            sort(s);
            if(!isSorted(s)){
                System.out.println("出错了");
            }
        }
        System.out.println("测试结束");

    }


    //为了测试
    //随机生成栈
    public static Stack<Integer> randomStack(int N,int V){
        Stack<Integer> stack=new Stack<>();
        for(int i=0;i<N;i++){
            stack.push((int)(Math.random()*V));
        }
        return stack;
    }

    //为了测试
    //检测栈是不是从顶至底依次有序
    public static boolean isSorted(Stack<Integer> stack){
        int step=Integer.MIN_VALUE;
        while(!stack.isEmpty()){
            if(step>stack.peek()){
                return false;
            }
            step=stack.pop();
        }
        return true;
    }

}
