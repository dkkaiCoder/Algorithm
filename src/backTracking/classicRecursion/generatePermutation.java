package backTracking.classicRecursion;
/**
 * 测试链接：https://www.nowcoder.com/practice/92e6247998294f2c933906fdedbc6e6a
 * 给定一个字符串s，长度为n，求s的所有子序列
 * 1.子序列: 指一个字符串删掉部分字符（也可以不删）形成的字符串，可以是不连续的，比如"abcde"的子序列可以有"ace","ad"等等
 * 2.将所有的子序列的结果返回为一个字符串数组
 * 3.字符串里面可能有重复字符，但是返回的子序列不能有重复的子序列，比如"aab"的子序列只有"","a","aa","aab","ab","b"，不能存在2个相同的"ab"
 * 4.返回字符串数组里面的顺序可以不唯一
 * 子序列本身是可以有重复的，只是这个题目要求去重
 */
import java.util.HashSet;

public class generatePermutation {
    public static String[] generatePermutation(String str){
        //把String转为字符串让每种语音都有通用性
        char[] s=str.toCharArray();
        //字符长度
        int n=s.length;
        //去重子序列使用
        HashSet<String> set=new HashSet<>();
        f1(s,0,new StringBuilder(),set);
        //set中记录了多少结果
        int m=set.size();
        //存储最终可能的结果
        String[] ans=new String[m];
        int i=0;
        for(String cur:set){
            ans[i++]=cur;
        }
        return ans;
    }

    //s：给定的原字符串
    //i:尝试的位置
    //path:对尝试过的路径进行记录
    //set:可能出现的结果
    public static void f1(char[] s,int i,StringBuilder path,HashSet<String> set){
        if(i==s.length){
            //当前已经尝试完所有字符 来到越界位置 前面有一个确定的结果了 把它加入
            set.add(path.toString());
            return;
        }
        //后面还字符可以尝试
        //先把当前来到的字符添加到路径中(选中当前字符)
        path.append(s[i]);
        //再接着对后面的字符进行尝试
        f1(s,i+1,path,set);
        //把上面选中的字符删去再接着往下进行别的尝试
        path.deleteCharAt(path.length()-1);
        f1(s,i+1,path,set);
    }

    public static String[] generatePermutation2(String str){
        //把String转为字符串让每种语音都有通用性
        char[] s=str.toCharArray();
        //字符长度
        int n=s.length;
        //去重子序列使用
        HashSet<String> set=new HashSet<>();
        f2(s,0,new char[n],0,set);
        //set中记录了多少结果
        int m=set.size();
        //存储最终可能的结果
        String[] ans=new String[m];
        int i=0;
        for(String cur:set){
            ans[i++]=cur;
        }
        return ans;
    }

    //s：给定的原字符串
    //i:尝试的位置
    //path:对尝试过的路径进行记录
    //size:标记path中有效的字符 回溯回去路径通过软删除恢复现场
    //set:可能出现的结果
    public static void f2(char[] s,int i,char[] path,int size,HashSet<String> set){
        if(i==s.length){
            //当前已经尝试完所有字符 来到越界位置 前面有一个确定的结果了 把它加入
            set.add(String.valueOf(path,0,size));
            return;
        }
        //后面还字符可以尝试
        //先把当前来到的字符添加到路径中(选中当前字符)
        //size除了标记有多少个字符  还有记录下次尝试的字符该放在哪个下标
        path[size]=s[i];
        //选择当前字符
        f2(s,i+1,path,size+1,set);
        //舍弃当前字符
        f2(s,i+1,path,size,set);
    }


    //测试
    public static void main(String[] args) {
        String[] s=generatePermutation2("aab");
        for(String res:s){
            System.out.println(res);
        }
    }
}
