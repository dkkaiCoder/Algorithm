package binarySystem.xor;

/**
 * 通过异或运算实现两个数的交换
 * 不难发现只要两个数相等的时候还使用异或运算的交换最终结果就是两个结果都为0
 */
public class SwapExclusiveOr {
    public static void main(String[] args) {
        int a=10;
        int b=-3203;
        System.out.println("a="+a+" b="+b);
        a=a^b;
        b=a^b;
        a=a^b;
        System.out.println("a="+a+" b="+b);

        //孤例难证
        //下面使用对数器来进行验证
        //数组长度范围的最大值
        int N=2000;
        //数组中数据的范围[1,V]
        int V=Integer.MAX_VALUE;
        //测试次数
        int testTimes=10000;
        System.out.println("测试开始");
        for(int i=0;i<testTimes;i++){
            int n=(int)(Math.random()*N+1);//为了得到[1,N]大小的数组
            int[] arr=RandomArray(n,V);
            int[] arr1=copyArray(arr);
            int[] arr2=copyArray(arr);
            //交换的下标
            int len=arr.length;
            int j=(int)(Math.random()*len);
            int k=(int)(Math.random()*len);
            //只有这种情况是不正确的
            if(j==k){
                continue;
            }
            swap1(arr1,j,k);
            swap2(arr2,j,k);
            if(!sameArr(arr1,arr2)){
                System.out.println("出错了！");
                System.out.printf("arr[%d]=%d arr[%d]=%d\n",j,arr[j],k,arr[k]);
                System.out.printf("arr1[%d]=%d arr1[%d]=%d\n",j,arr1[j],k,arr1[k]);
                System.out.printf("arr2[%d]=%d arr2[%d]=%d\n",j,arr2[j],k,arr2[k]);
                break;
            }
        }
        System.out.println("测试结束");
    }

    //使用异或运算实现交换需要注意必须是两个不同的数
    public static void swap1(int[] arr,int i,int j){
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }


    //为了验证
    //这是一个绝对没问题的方法
    public static void swap2(int[] arr,int i,int j){
        int tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    //为了验证
    //生成一个长度为n数据范围在[1,v]的数组
    public static int[] RandomArray(int n,int v){
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=(int)(Math.random()*v+1);
        }
        return arr;
    }

    //为了验证
    //copy一份原数组  不在原数组的基础上进行操作方便后续操作
    public static int[] copyArray(int[] arr){
        int n=arr.length;
        int[] newArr=new int[n];
        for(int i=0;i<n;i++){
            newArr[i]=arr[i];
        }
        return newArr;
    }

    //为了验证
    //对比两种交换写法交换后的结果是否一致
    public static boolean sameArr(int[] arr1,int[] arr2){
        int n=arr1.length;
        for(int i=0;i<n;i++){
            if(arr1[i]!=arr2[i]){
                return false;
            }
        }
        return true;
    }
}
