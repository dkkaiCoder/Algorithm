package binarySystem.bitoperation;

/**
 * 返回n的二进制中有几个1
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离
 * 测试链接 : https://leetcode.cn/problems/hamming-distance/
 */
public class CountOneBinarySystem {

    public int hammingDistance(int x, int y) {
        return cntOnes(x^y);
    }

    //返回二进制中有几个1
    //整体算法思想：就是把整个二进制位看作很多块
    //首先分为长度为1的每一块  每一个长度为1的块它二进制对应的值就表示该块中1的个数
    //接着扩充到长度为2的每一块  每一个长度为2的块它的二进制对应的值就表示该块中1的个数
    //然后充到长度为4的每一块  每一个长度为4的块它的二进制对应的值就表示该块中1的个数
    //依次按照这个流程走下去  到每一个长度为32的块它的二进制对应的值就表示该块中1的个数
    //有了这个想法之后就该考虑怎么实现从1-2-4-...-32呢
    //把长度k(1,2,4,...)的块的每一位分别提取出来相加
    public static int cntOnes(int n){
        n=(n&0x55555555)+((n>>>1)&0x55555555);
        n=(n&0x33333333)+((n>>>2)&0x33333333);
        n=(n&0x0f0f0f0f)+((n>>>4)&0x0f0f0f0f);
        n=(n&0x00ff00ff)+((n>>>8)&0x00ff00ff);
        n=(n&0x0000ffff)+((n>>>16)&0x0000ffff);
        return n;
    }
}
