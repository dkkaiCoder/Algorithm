package backTracking.NQueens;

/**
 * N皇后问题：如何将 n 个皇后放置在 n × n 的棋盘上，并且使皇后彼此之间不能相互攻击
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量
 * 也就是在一个棋盘上  某一位置放上了皇后 那么关于这个的同行 同列 左上至右下 右上至左下的位置不能再放置皇后
 * 时间复杂度 O(n!)
 * 测试链接：https://leetcode.cn/problems/n-queens-ii/
 */
public class NQueens {
    //思路一：使用数组来记录N皇后的路径
    //初始化一个path数组  path[i]--->在第i行第path[i]列放置了皇后
    public static int totalNQueens(int n){
        if(n<1){
            return 0;
        }
        return f1(0,new int[n],n);
    }

    //row表示当前尝试到第几行了  也是说从0到row-1已经选定了
    //path记录从0到row-1行的皇后都放在了哪些位置
    //n表示这是几皇后问题
    //返回从row到n-1一共多少种方案数
    public static int f1(int row,int[] path,int n){
        if(row==n){
            return 1;
        }
        int ans=0;
        for(int j=0;j<n;j++){
            if(check(row,j,path)){
                path[row]=j;
                ans+=f1(row+1,path,n);
            }
        }
        return ans;
    }

    //检测当前位置是否合法  合法返回true  否则false
    //之前某列已经放了皇后的在path数组中
    //两个对角线都是绝对值为1的两条直线  假设当前位置为第i行第j列  在[0,row-1]上若p行k列放置了皇后
    //当前位置如果在(p,k)的对角线上  就会有|p-i|=|k-j|
    //返回会不会冲突
    //row column表示当前放的位置
    public  static boolean check(int row,int column,int[] path){
        for(int k=0;k<row;k++){
            if(path[k]==column||Math.abs(row-k)==Math.abs(column-path[k])){
                return false;
            }
        }
        return true;
    }

    //思路二：使用位信息来表示路径实现N皇后
    public static int totalNQueens2(int n){
        if(n<1){
            return 0;
        }
        //5皇后  n=5
        //1<<n:0010 0000
        //(1<<n)-1:0001 1111
        //标1的位置表示当前能放皇后
        int limit=(1<<n)-1;
        return f2(limit,0,0,0);
    }

    //limit:当前是几皇后问题
    //col:之后皇后列的影响
    //left:之后皇后从右上到左下的影响
    //right:之后皇后从左上到右下的影响
    //具体算法实现：四皇后为例
    //col：在某一列放皇后 就把当前列对应的位设置为1
    //0  1000
    //1  1001
    //2  1101
    //3  1111
    //left:在某一列放皇后 就把当前列对应的位设置为1 然后来到下一列左移1位
    //0  0100
    //1  1000 --1001
    //2  0010 --0110
    //3  1100
    //right:在某一列放皇后 就把当前列对应的位设置为1  然后来到下一列右移1位(类比left)
    public static int f2(int limit,int col,int left,int right){
        if(col==limit){
            //所有皇后放完了
            return 1;
        }
        //总限制:ban为0可放  1不可放
        int ban=col|left|right;
        //~ban:1可放皇后  0不可放
        int candidate=limit&(~ban);
        //放置皇后的尝试
        int place=0;
        //一共有多少种有效的问题
        int ans=0;
        while(candidate!=0){
            //依次提取最右侧的1
            place=candidate&(-candidate);
            candidate^=place;
            ans+=f2(limit,col|place,(left|place)>>1,(right|place)<<1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int n=14;
        long start,end;
        System.out.println("测试开始");
        System.out.println("解决"+n+"皇后问题");
        start=System.currentTimeMillis();
        totalNQueens(n);
        end=System.currentTimeMillis();
        System.out.println("方法一答案："+totalNQueens(n));
        System.out.println("方法一运行时间:"+(end-start)+"毫秒");


        start=System.currentTimeMillis();
        totalNQueens2(n);
        end=System.currentTimeMillis();
        System.out.println("方法二答案："+totalNQueens2(n));
        System.out.println("方法二运行时间:"+(end-start)+"毫秒");
        System.out.println("测试结束");


        System.out.println("=======");
        System.out.println("只有位运算的版本，才能10秒内跑完16皇后问题的求解过程");
        start = System.currentTimeMillis();
        int ans = totalNQueens2(16);
        end = System.currentTimeMillis();
        System.out.println("16皇后问题的答案 : " + ans);
        System.out.println("运行时间 : " + (end - start) + " 毫秒");

    }
}
