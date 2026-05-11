package binarySystem.xor;

/**
 *  数组中只有1种数出现次数少于m次，其他数都出现了m次 返回出现次数小于m次的那种数
 *  测试链接 : https://leetcode.cn/problems/single-number-ii/
 *  注意：测试链接只是它的一种特殊情况
 */
public class OneKindNumberLessMTimes {

    //m=3的情况
    public static int singleNumber(int[] nums){
        return find(nums,3);
    }

    //具体算法过程：关键还是在于统计
    //对于每一个出现m次数的数  统计它的每一位  最终一定能被m整除
    //但是出现次数小于m次的数  统计完它的每一位  只要是在它为1的位上除以m一定会有余数  没有余数的位上就是0
    public static int find(int[] arr,int m){
        //cnt[i]：表示第i位上有多少个1
        int[] cnt=new int[32];
        for(int num:arr){
            for(int i=0;i<32;i++){
                /*if(((num>>>i)&1)==1){
                    cnt[i]++;
                }*/
                //对上面这个逻辑优化一下
                cnt[i]+=(num>>>i)&1;
            }
        }

        int ans=0;
        for(int i=0;i<32;i++){
            if(cnt[i]%m!=0){
                ans|=1<<i;
            }
        }
        return ans;
    }

}
