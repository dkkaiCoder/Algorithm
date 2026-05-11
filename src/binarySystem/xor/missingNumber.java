package binarySystem.xor;

/**
 * 找到缺失的数字
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 *  测试链接 : https://leetcode.cn/problems/missing-number/
 */
public class missingNumber {
    //这里n长度数组  n+1个数  肯定有一个不在里面
    public static int missingNumber(int[] nums){
        //eorAll是异或0~n所有数  eorHas是异或数组中的所有数  那么在该范围上没有出现的数只出现一次  最后一定剩下这个没出现的数
        int eorAll=0,eorHas=0;
        for(int i=0;i<nums.length;i++){
            eorAll^=i;
            eorHas^=nums[i];
        }
        eorAll^=nums.length;
        return eorAll^eorHas;
    }

    public static void main(String[] args) {
        //对上述方法进行一些测试
        int N=10000;
        int testTimes=2000;
        System.out.println("测试开始");
        for(int i=0;i<testTimes;i++){
            int n=(int)(Math.random()*N+1);
            int[] arr=RandomArr(n);
            int[] arr1=MissingArr(arr);
            int num=missingNumber(arr1);
            if(num!=arr[n-1]){
                System.out.println("出错了！");
                PrintArr(arr);
                PrintArr(arr1);
                System.out.println(num);
                break;
            }
        }
        System.out.println("测试结束");
    }

    //为了测试
    // 得到一个n长的数组
    public static int[] RandomArr(int n){
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=i;
        }
        int opSwap=(int)(Math.random()*1000);
        for(int i=0,index1,index2;i<opSwap;i++){
            index1=(int)(Math.random()*n);
            index2=(int)(Math.random()*n);
            swap(arr,index1,index2);
        }
        return arr;
    }

    //为了测试
    public static void  swap(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    //生成一个从缺失的数组
    public static int[] MissingArr(int[] arr){
        int n=arr.length-1;
        int[] newArr=new int[n];
        for(int i=0;i<n;i++){
            newArr[i]=arr[i];
        }
        return newArr;
    }

    //为了测试  打印数组
    public static void PrintArr(int[] arr){
        for(int i=0,n=arr.length;i<n;i++){
            System.out.printf(arr[i]+" ");
        }
        System.out.println();
    }
}
