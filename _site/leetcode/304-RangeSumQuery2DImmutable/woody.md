>答案示例，本人自行编写后参考LeetCode官方题库。

**304. 二维区域和检索-矩阵不可变**  
---
[https://leetcode-cn.com/problems/range-sum-query-2d-immutable/](https://leetcode-cn.com/problems/range-sum-query-2d-immutable/) 

摘要  

本文适用于初学者，潜入深出。  


```java  

package algorithm_leetcode;

//看到这道题目时，给我第一印象似乎只有一种方法来解决
class  NumMatrix2{
	//定义成员变量
	private int[][] data;
	public NumMatrix2(int[][] matrix){
		data=matrix;
	}
	//普通的方法用来计算完对应范围的数据的和
	public int sumRegion(int row1, int col1, int row2, int col2){
		int k=0;
		//走完所有的ROWS
		for(int i=row1;i<=row2;i++){
			//走完所有的COLUMS
    		for(int j=col1;j<=col2;j++){
				//进行累加
    		   k+=data[i][j];
    		}   		
    	}
		//返回给调用者
    	return k;
	}
}
//认为leetCode中省略的代码
public class T304RangeSumQuery2DImmutable2 {
	public static void main(String[] args) {
		int[][] matrix={ {3, 0, 1, 4, 2},
			   			 {5, 6, 3, 2, 1},
			   			 {1, 2, 0, 1, 5},
			   			 {4, 1, 0, 1, 7},
			   			 {1, 0, 4, 0, 5}};
		int row1=2;
    	int col1=1;
    	int row2=4;
    	int col2=3;
		NumMatrix2 obj = new NumMatrix2(matrix);
		int param_1 = obj.sumRegion(row1,col1,row2,col2);
		System.err.println(param_1);
    }      
}
//如果你在自己的工具上写完后，乍一看好像没有别的好的解决方法了吧
//但是仔细思考下会发现leetCode中用了一个有参构造方法，类是对象的模板，对象是类的实例，
//分配空间-->递归创建父类对象-->初始化本类属性-->调用本类构造方法
//这些都是在缓存中完成的，所以如果可以借助这个构造方法来做一些计算数组的事情会提高效率
class NumMatrix {
    //成员变量
	private int[][] dp;
	//有参构造方法
	public NumMatrix(int[][] matrix) {
		//排除数组长度为0的情况
	    if (matrix.length == 0 || matrix[0].length == 0) return;
		//建一个新的数组
	    dp = new int[matrix.length + 1][matrix[0].length + 1];
		//走完matrix的所有ROWS
	    for (int r = 0; r < matrix.length; r++) {
			//走完matrix的所有COLUMS
	        for (int c = 0; c < matrix[0].length; c++) {
	        	//System.out.print("dp:"+dp[r + 1][c + 1]+"=" + dp[r + 1][c] +"+"+ dp[r][c + 1]+"+" + matrix[r][c] +"-"+ dp[r][c]);
	        	//System.out.println();
				//这样是为了实现某个数据上如 dp[1][2] 你取到的这个值就是从dp[0][0]到dp[1][2]的所有值的和
	            dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
	        }
	    }
	}	
	public int sumRegion(int row1, int col1, int row2, int col2) {
		//System.out.println("dp:"+dp[row2 + 1][col2 + 1]+"-" + dp[row1][col2 + 1] +"-"+ dp[row2 + 1][col1]+"+" + dp[row1][col1]);
	    //想求得某个区域的和，要减去两个部分区域但是这两部分区域有交集，即会多减掉一个交集。
		return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
	}
```  

---


**参考资料**  

* 本题leetCode英文官方题解：  
[https://leetcode.com/articles/range-sum-query-2d-immutable/](https://leetcode.com/articles/range-sum-query-2d-immutable/)  
