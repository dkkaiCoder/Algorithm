package binarySystem.xor;

/**
 * 数组中有两种数出现了奇数次，其他的数都出现了偶数次  返回这两种出现了奇数次的数
 * 测试链接 : https://leetcode.cn/problems/single-number-iii/
 */
public class DoubleNumber {
    public static int[] singleNumber(int[] nums){
        int eor1=0;
        for(int num:nums){
            eor1^=num;
        }
        //eor1:a^b
        //Brian Kernighan算法
        //提取二进制最右侧的1 就是把整个状态提取出来
        //也就是接下来这两种数a b肯定不一样  那么他们的异或就一定不为0
        //提取出最右侧的1之后a b在该位上一定一个为0 一个为1
        //那么把该位上不为1的提取出来  就得到a b其中一个   然后再异或a^b
        //就得到了另一个
        int rightOne=eor1&(-eor1);
        int eor2=0;
        for(int num:nums){
            if((rightOne&num)==0){
                eor2^=num;
            }
        }
        return new int[]{eor2,eor1^eor2};
    }

}
