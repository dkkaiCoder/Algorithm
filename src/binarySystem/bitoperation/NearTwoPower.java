package binarySystem.bitoperation;

/**
 * 给定一个整数n
 * 返回大于等于n的最小2的某次幂
 * 如果在int范围内不存在这样的数就返回整数最小值
 */
public class NearTwoPower {

    //整体算法思路：
    //就是把一个数最高位的1及其右边都刷为1 然后在此基础上加1
    //但是该问题是要大于等于的情况  因此如果刚好就是2的次幂需要n--来保证它不发生进位
    public static int nearTwoPower(int n){
        //如果这个整数n小于等于0 而2的最小次幂为1  直接返回1就好了
        if(n<=0){
            return 1;
        }
        n--;
        n|=n>>>1;
        n|=n>>>2;
        n|=n>>>4;
        n|=n>>>8;
        n|=n>>>16;
        return n+1;
    }

    public static void main(String[] args) {
        int a=100;
        System.out.println(nearTwoPower(a));
    }

}
