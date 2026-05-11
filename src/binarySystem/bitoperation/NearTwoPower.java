package binarySystem.bitoperation;

/**
 * 给定一个整数n
 * 返回大于等于n的最小2的某次幂
 * 如果在int范围内不存在这样的数就返回整数最小值
 */
public class NearTwoPower {

    //整体算法思路：
    //
    public static int nearTwoPower(int n){
        //如果这个整数n小于等于0 而2的最小次幂为1  直接返回1就好了
        if(n<=0){
            return 1;
        }
        n--;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n + 1;
    }

}
