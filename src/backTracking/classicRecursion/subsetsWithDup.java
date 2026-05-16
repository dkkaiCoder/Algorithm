package backTracking.classicRecursion;

/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的组合
 * 答案 不能 包含重复的组合。返回的答案中，组合可以按 任意顺序 排列
 * 注意其实要求返回的不是子集，因为子集一定是不包含相同元素的，要返回的其实是不重复的组合
 *  测试链接：https://leetcode.cn/problems/subsets-ii/
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class subsetsWithDup {

    public static List<List<Integer>> subsetsWithDup(int[] nums){
        List<List<Integer>> ans=new ArrayList<>();
        //先对nums数组排序  方便后续的剪枝处理  整个过程就是按每一块取0到k个进行选择
        Arrays.sort(nums);
        f(nums,0,new int[nums.length],0,ans);
        return ans;
    }


    public static void f(int[] nums,int i,int[] path,int size,List<List<Integer>> ans){
        if(i==nums.length){
            List<Integer> list=new ArrayList<>();
            for(int j=0;j<size;j++){
                list.add(path[j]);
            }
            ans.add(list);
            return;
        }
        //寻找下一块数据的初始位置
        int j=i+1;
        while(j<nums.length&&nums[j]==nums[i]){
            j++;
        }
        //当前数要0个
        f(nums,j,path,size,ans);
        for(;i<j;i++){
            //当前数要1 2 3...个
            path[size++]=nums[i];
            f(nums,j,path,size,ans);
        }
    }

    //测试
    public static void main(String[] args) {
        int[] nums={1,2,2};
        for(List<Integer> list:subsetsWithDup(nums)){
            System.out.println(list);
        }
    }

}
