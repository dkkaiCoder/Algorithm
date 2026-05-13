package binarySystem.bitSet;

import java.util.HashSet;

/**
 * 位图：用一个二进制位标记数字是否存在  限制：必须连续且范围不能过大
 * Bitset(int n):初始化位图  支持CRUD(增删改查)
 * void add(int num)
 * void remove(int num)
 * void flip(int num)
 * boolean contains(int num)
 */

public class BitSet {

    public static class Bitset{

        public int[] set;

        public Bitset(int n){
            //要得到一个数a除以b的向上取整可以通过(a+b-1)/b
            //前提：a b都得非负
            set=new int[(n+32-1)/32];
        }

        //下面的操作需要知道如何找到一个数的位置
        //第几个数num/32 第几位num%32

        public void add(int num){
            set[num/32]|=1<<(num%32);
        }

        public void remove(int num){
            set[num/32]&=~(1<<(num%32));
        }

        public void flip(int num){
            set[num/32]^=1<<(num%32);
        }

        public boolean contains(int num){
            return ((set[num/32]&(1<<(num%32))))!=0;
        }


        //Bitset能实现和Hashset一样的功能  但是更省空间 且需要连续
        //因此对数器使用Hashset来验证
        public static void main(String[] args) {
            int N=2000;
            int testTimes=10000;
            HashSet<Integer> set=new HashSet<>();
            Bitset bitset=new Bitset(N);
            System.out.println("测试开始");
            System.out.println("调用阶段开始");
            for(int i=0;i<testTimes;i++){
                double decision=Math.random();
                int number=(int)(Math.random()*N);
                if(decision<0.333){
                    set.add(number);
                    bitset.add(number);
                }else if(decision<0.666){
                    set.remove(number);
                    bitset.remove(number);
                }else{
                    if(set.contains(number)){
                        set.remove(number);
                    }else{
                        set.add(number);
                    }
                    bitset.flip(number);
                }
            }
            System.out.println("调用阶段结束");
            System.out.println("测试阶段开始");
            for(int i=0;i<N;i++){
                if(set.contains(i)!=bitset.contains(i)){
                    System.out.println("出错了");
                }
            }
            System.out.println("测试阶段结束");
            System.out.println("测试结束");
        }
    }
}
