package binarySystem.bitSet;

/**
 * 测试链接 : https://leetcode-cn.com/problems/design-bitset/
 * 位集 Bitset 是一种能以紧凑形式存储位的数据结构。
 * 实现 Bitset 类
 * Bitset(int size) 用 size 个位初始化 Bitset ，所有位都是 0
 * void fix(int idx) 将下标为 idx 的位上的值更新为 1 。如果值已经是 1 ，则不会发生任何改变
 * void unfix(int idx) 将下标为 idx 的位上的值更新为 0 。如果值已经是 0 ，则不会发生任何改变
 * void flip() 翻转 Bitset 中每一位上的值。换句话说，所有值为 0 的位将会变成 1 ，反之亦然
 * boolean all() 检查 Bitset 中 每一位 的值是否都是 1 。如果满足此条件，返回 true ；否则，返回 false
 * boolean one() 检查 Bitset 中 是否 至少一位 的值是 1 。如果满足此条件，返回 true ；否则，返回 false
 * int count() 返回 Bitset 中值为 1 的位的 总数
 * String toString() 返回 Bitset 的当前组成情况。注意，在结果字符串中，第 i 个下标处的字符应该与 Bitset 中的第 i 位一致
 */
public class DesignBitSet {

    public class Bitset{
        private int[] set;
        private final int size;
        private int zeros;
        private int ones;
        private boolean reverse;

        public Bitset(int size) {
            set=new int[size];
            this.size=size;
            zeros=size;
            ones=0;
            reverse=false;
        }

        public void fix(int idx) {
            int index=idx/32;
            int bit=idx%32;
            if(!reverse){
                //没有翻转就是0标记不存在  1标记存在
                if((set[index]>>>bit&1)==0){
                    zeros--;
                    ones++;
                    set[index]|=1<<bit;
                }
            }else{
                //翻转了就是0标记存在  1标记不存在
                if((set[index]>>>bit&1)!=0){
                    //这里需要注意翻转后set里面存的0 1个数进行了交换(zeros ones)  但是set里面的数据是没有改变的
                    zeros--;
                    ones++;
                    set[index]&=~(1<<bit);//这里也可以写作set[index]^=(1<<bit);
                }
            }
        }

        public void unfix(int idx) {
            int index=idx/32;
            int bit=idx%32;
            if(!reverse){
                if((set[index]>>>bit&1)!=0){
                    ones--;
                    zeros++;
                    set[index]^=1<<bit;
                }
            }else{
                if((set[index]>>bit&1)==0){
                    ones--;
                    zeros++;
                    set[index]|=1<<bit;
                }
            }
        }

        public void flip() {
            reverse=!reverse;
            int tmp=ones;
            ones=zeros;
            zeros=tmp;
        }

        public boolean all() {
            return ones==size;
        }

        public boolean one() {
            return ones>0;
        }

        public int count() {
            return ones;
        }

        public String toString() {
            StringBuilder builder=new StringBuilder();
            for(int i=0,k=0,status,number;i<size;k++){
                number=set[k];
                for(int j=0;j<32&&i<size;i++,j++){
                    status=(number>>j)&1;
                    status^=reverse?1:0;
                    builder.append(status);
                }
            }
            return builder.toString();
        }
    }
}
