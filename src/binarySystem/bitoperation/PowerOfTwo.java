package binarySystem.bitoperation;

/**
 * 判断一个整数是不是2的幂
 * 测试链接 : https://leetcode.cn/problems/power-of-two/
 */
public class PowerOfTwo {
    //Brian Kernighan算法 提取出最右侧的1看它等不等于n  如果是2的次幂它们就相等
    //需要注意2的次幂最小都是1 所以非正数一定不是2的次幂
    public static boolean isPowerOfTwo(int n){
        return n>0&&(n&(-n))==n;
    }

    //另外一种写法
    //(n&(n-1))实现的就是消除最低位的1
    //对于一个2的次幂一定只有一个位上是1 消除后一定是0
    public static boolean isPowerOfTwo2(int n){
        return n>0&&(n&(n-1))==0;
    }
}
