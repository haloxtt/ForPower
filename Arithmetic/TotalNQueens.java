import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 52 N皇后问题
 */
public class TotalNQueens {
    static int LENGTH;
    static int[] cols;//定义皇后的位置的数组
    int count = 0;

    public int totalNQueens(int n) {
        LENGTH = n;
        cols = new int[n];
        getIndex(0);
        return count;
    }
    private void getIndex(int n) {
        boolean[] isfix = new boolean[LENGTH];//定义哪些位置不能放皇后
        for (int i = 0; i < n; i++) {//哪些位置不能放
            int d =  n - i;
            //同一行不能放
            isfix[cols[i]] = true;//同一行的不能放
            if (cols[i] - d >= 0) {//正斜
                isfix[cols[i] - d] = true;
            }
            if (cols[i] + d < LENGTH) {//反斜
                isfix[cols[i] + d] = true;
            }
        }
        for (int i = 0; i < LENGTH; i++) {//定义能放的位置
            if (!isfix[i]) {
                cols[n] = i;
                if (n < LENGTH - 1) {
                    getIndex(n+ 1);
                } else {
                    count++;
                }
            }
        }
    }

    public static void main(String[] args) {
        TotalNQueens totalNQueens = new TotalNQueens();
        System.out.println(totalNQueens.totalNQueens(1));
    }
}
