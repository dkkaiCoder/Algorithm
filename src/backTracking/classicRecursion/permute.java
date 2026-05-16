package backTracking.classicRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 没有重复项数字的全排列
 * 测试链接 : https://leetcode.cn/problems/permutations/
 */

public class permute {

    public static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> ans=new ArrayList<>();
        f(nums,0,ans);
        return ans;
    }

    public static void f(int[] nums,int i,List<List<Integer>> ans){
        if(i==nums.length){
            List<Integer> cur=new ArrayList<>();
            for(int num:nums){
                cur.add(num);
            }
            ans.add(cur);
            return;
        }
        for(int j=i;j<nums.length;j++){
            swap(nums,i,j);
            f(nums,i+1,ans);
            //恢复现场
            swap(nums,i,j);
        }
    }

    public static void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }

    public static void main(String[] args){
        int[] nums={1,2,3};
        for(List<Integer> list:permute(nums)){
            System.out.println(list);
        }
    }
}
