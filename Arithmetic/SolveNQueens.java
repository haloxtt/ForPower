import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 51   N皇后算法
 */
public class SolveNQueens {
    static List<List<String>> resullist;
    static int LENGTH;
    static int[] cols;//定义皇后的位置的数组

    public List<List<String>> solveNQueens(int n) {
        resullist = new ArrayList<>();
        LENGTH = n;
        cols = new int[n];
        getIndex(0);
        return resullist;
    }
    private   void getIndex(int n) {
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
                    List<String> list = new ArrayList<>();//一个结果
                    for (int j = 0; j < LENGTH; j++) {
                        StringBuffer sbf= new StringBuffer();
                        for (int k = 0; k < LENGTH; k++) {
                            if (j == cols[k]) {
                                sbf.append("Q");
                            } else {
                                sbf.append(".");
                            }
                        }
                        list.add(sbf.toString());
                    }
                    resullist.add(list);
                }
            }
        }
    }
    public static void main(String[] args) {
        SolveNQueens solveNQueens = new SolveNQueens();
        solveNQueens.solveNQueens(1);
        List<List<String>> list = resullist;
    }
}
