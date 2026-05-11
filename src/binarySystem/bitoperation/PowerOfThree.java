package binarySystem.bitoperation;

/**
 * 判断一个数是否为3的次幂
 * 测试链接 : https://leetcode.cn/problems/power-of-three/
 */
public class PowerOfThree {
    //如果一个数是3的次幂 那么这个数一定只含有3这个质数因子
    //在整数范围内最大的3次幂为3的19次幂 1162261467
    //1162261467只含有3这个质数因子  如果n是3的次幂  也就是n也只含有3这个质数因子
    //那么就会有1162261467%n==0  反之如果1162261467%n!=0就说明n这个数还含有其他质数因子
    //同样需要注意一个数的某次幂一定是正数
    public static boolean isPowerOfThree(int n){
        return n>0&&1162261467%n==0;
    }

}
