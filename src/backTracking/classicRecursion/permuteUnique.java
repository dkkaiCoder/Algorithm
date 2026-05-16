package backTracking.classicRecursion;
/**
 * 有重复项数组的去重全排列
 * 在没有重复项的全排列基础上进行筛选  如果两个项有重复那么交换位置没有区别 就只保留一个
 * 测试链接：https://leetcode.cn/problems/permutations-ii/
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class permuteUnique {
    public static List<List<Integer>> permuteUnique(int[] nums){
        List<List<Integer>> ans=new ArrayList<>();
        f(nums,0,ans);
        return ans;
    }

    public static void f(int[] nums,int i,List<List<Integer>> ans){
        if(i==nums.length){
            List<Integer>  cur=new ArrayList<>();
            for(int num:nums){
                cur.add(num);
            }
            ans.add(cur);
            return;
        }
        HashSet<Integer> set=new HashSet<>();
        for(int j=i;j<nums.length;j++){
            if(!set.contains(nums[j])){
                set.add(nums[j]);
                swap(nums,i,j);
                f(nums,i+1,ans);
                swap(nums,i,j);
            }
        }
    }

    public static void swap(int [] nums,int i,int j){
        int t=nums[i];
        nums[i]=nums[j];
        nums[j]=t;
    }

    public static void main(String[] args){
        int[] nums=new int[]{1,1,2};
        for(List<Integer> list:permuteUnique(nums)){
            System.out.println(list);
        }
    }
}
