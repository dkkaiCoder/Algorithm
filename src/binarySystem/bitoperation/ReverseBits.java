package binarySystem.bitoperation;

/**
 * 逆序二进制状态
 * 测试链接 : https://leetcode.cn/problems/reverse-bits/
 */

public class ReverseBits {
    public static int reverseBits(int n){
        //整个过程当然可以把每一位遍历一遍
        //但是太慢了
        //整体思路呢：就是先把1位作为整体交换 接着2位作为一个整体交换 然后再四位作为一个整体交换   这样一直到16位之后就完成了逆序
        //以8位举个例子 比如说：a b c d e f g h
        //1位为整体交换：b a d c f e h g
        //2位为整体交换：d c b a h g f e
        //4位为整体交换：h g f e d c b a   到这里已经完成了逆序  接下来扩充到32位就能完成逆序了
        n=((n&0x55555555)<<1)|((n&0xaaaaaaaa)>>>1);
        n=((n&0x33333333)<<2)|((n&0xcccccccc)>>>2);
        n=((n&0x0f0f0f0f)<<4)|((n&0xf0f0f0f0)>>>4);
        n=((n&0x00ff00ff)<<8)|((n&0xff00ff00)>>>8);
        n=(n<<16)|(n>>>16);
        return n;
    }
}
