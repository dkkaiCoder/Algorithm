package binarySystem.xor;

/**
 * 不用判断和比较返回两个数的最大值
 * flip函数借助异或的性质 按无进位加法去实现
 * 测试链接 : https://www.nowcoder.com/practice/d2707eaf98124f1e8f1d9c18ad487f76
 */
public class GetMaxWithoutJudge {

    //在保证输入为0/1的情况下
    //in:0 out:1  in:1 out:0
    public static int flip(int n){
        return n^1;
    }

    //对于一个数如果是负数返回0 正数返回1
    //也就是最高位1 return 0   最高位0  return 1
    public static int sign(int num){
        return flip(num>>>31);//如果没有无符号右移的可以写为(num>>31)&1 如果像python会处理溢出的写为((num>>31)&1)&0xFFFF
    }

    //如果发生了溢出该方法就会失效
    public static int getMax1(int a,int b){
        int c=a-b;
        //也就是说c为a与b的差 这个差如果是负数  那么通过sign就能得到0 说明b更大  非负数就得到1  a更大
        int returnA=sign(a);
        int returnB=flip(a);
        //这种定制之和returnA和returnB必然存在一个0 一个1  能得到想要的结果
        return returnA*a+returnB*b;
    }

    //那么就寻找有没有什么办法能对溢出的情况进行处理呢
    //很容易考虑到 两个数相减如果发生溢出  只可能是两个数异号
    //也就是 一个正数-一个负数  or  一个负数-一个正数
    //发现这种情况下两个数的大小是很容易比较的
    //非负数一定是大于负数的  根据符号就能判断
    //那么把a,b符号得到就能够去判断了
    public static int getMax2(int a,int b){
        //c是可能溢出的
        int c=a-b;
        //获取a,b的符号
        int sa=sign(a);
        int sb=sign(b);
        //c的符号
        int sc=sign(c);
        //那么什么情况下返回a  也就是a更大呢   这里b也同理
        //一定是在不溢出的情况下也就是c为非负数 sc==1 同时考虑溢出的情况下sa=1
        //如果ab同号不会溢出  ab异号会溢出
        int diffAB=sa^sb;
        int sameAB=flip(diffAB);
        int returnA=diffAB*sa+sameAB*sc;
        //返回b的情况是a的对立
        int returnB=flip(returnA);
        return a*returnA+b*returnB;
    }

    public static void main(String[] args){
        int a1=Integer.MIN_VALUE;
        int b1=Integer.MAX_VALUE;
        System.out.println(getMax1(a1,b1));
        System.out.println(getMax2(a1,b1));
    }
}
