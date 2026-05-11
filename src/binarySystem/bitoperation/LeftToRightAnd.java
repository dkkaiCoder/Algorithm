package binarySystem.bitoperation;

/**
 * 给定两个整数left,right 表示区间[left,,right]  返回此区间内所有&的结果
 * 测试链接 : https://leetcode.cn/problems/bitwise-and-of-numbers-range/
 */
public class LeftToRightAnd {
    //整体算法思想：当left==right  也就是只有一个数的时候 就返回这个数就好了
    //但是left<right的时候  那么left和right的某连续高位是相同的  区别在于right的低位上还有多于left有的其他1
    //此时最右侧的1一定留不下  因为right-1的状态一定在区间里面  那么最低位的1一定留不下
    //0101 0100 1101如果不等于left 就一定小于left  那么0101 0100 1100一定在里面  与之后的结果0101 0100 1100
    //继续此时left还小于上面这个数  说明0101 0100 1011在里面  那么最右侧的1一定留不下 0101 0100 1000
    //当这个数等于left了 就直接返回
    //整个过程就相当于消除了最右侧的1 根据Brain Kernighan算法提取出最右侧的1  比如上面第二次0000 0000 0100
    //取反再和right进行位与就完成了消除操作
    public static int rangeBitwiseAnd(int left,int right){
        while(left<right){
            right&=~(right&(-right));//当然这里直接减去也是可以的right-=(right&(-right));
        }
        return right;
    }

}
