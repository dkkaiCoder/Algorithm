package binarySystem.bitwiseOperators;

/**
 * 不用任何算术运算，只用位运算实现加减乘除  代码实现中你找不到任何一个算术运算符
 * 测试链接 : https://leetcode.cn/problems/divide-two-integers/
 */
public class BitOperationAddMinusMultiplyDivide {
    public static int add(int a,int b){
        int ans=a;
        while(b!=0){
            //ans:a和b 无进位加法
            ans=a^b;
            //b：a和b相加时的进位信息
            b=(a&b)<<1;
            a=ans;
        }
        return ans;
    }

    public static int neg(int n){
        return add(~n,1);
    }

    public static int minus(int a,int b){
        return add(a,neg(b));
    }

    public static int multiply(int a,int b){
        int ans=0;
        while(b!=0){
            if((b&1)!=0){
                //考察b当前最右的状态
                ans=add(ans,a);
            }
            a<<=1;
            b>>>=1;
        }
        return ans;
    }

    //必须保证a和b都不是整数最小值  因为整数最小值是没办法转过去的
    //整个过程相当于把数a分解为a=b*2^k+b*2^l+b*2^m+b*2^n+b*2^p
    //结果就是k l m n p这些组成的数
    public static int  div(int a,int b){
        int x=a<0?neg(a):a;
        int y=b<0?neg(b):b;
        int ans=0;
        for(int i=30;i>=0;i=minus(i,1)){
            if((x>>i)>=y){
                ans|=1<<i;
                x=minus(x,y<<i);
            }
        }
        return a<0^b<0?neg(ans):ans;
    }

    public static int MIN=Integer.MIN_VALUE;
    public static int divide(int a,int b){
        if(a==MIN&&b==MIN){
            // a和b都是整数最小
            return 1;
        }
        if(a!=MIN&&b!=MIN){
            // a和b都不是整数最小，那么正常去除
            return div(a,b);
        }
        if(b==MIN){
            //b是整数最小值 整数运算的除法是整除 整数最小值的数值是最大的  一定会得到0
            return 0;
        }
        //来到这里说明a是整数最小值
        if(b==neg(1)){
            return Integer.MAX_VALUE;
        }
        //b既不是-1 也不是整数最小值
        //a是整数最小值  是不是可以考虑让它变大一些
        //所以基于对b正负的讨论  如果b>0  div(a+b,b)-1 否则 div(a-b,b)+1
        a=add(a,b>0?b:neg(b));
        int ans=div(a,b);
        int offset=b>0?neg(1):1;
        return add(ans,offset);
    }
}
