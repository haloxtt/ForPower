/**
 * 八皇后问题（回溯法）
 */
public class Empress {
    public static int num = 0;//累计方案
    public static final int MAXQUEEN = 8;
    public static int[] cols = new int[MAXQUEEN];//定义八皇后的位置

    public static void getCount(int n) {
        boolean[] rows = new boolean[MAXQUEEN];//记录每列每个位置是否可以放皇后
        for (int i = 0; i < n; i++) {
            rows[cols[i]] = true;//同一行不能放
            int d = n - i;//斜对角的差值
            //正斜
            if (cols[i] - d >= 0) {
                rows[cols[i] - d] = true;//本列的正斜位置设置为true
            }
            //反斜
            if (cols[i] + d < MAXQUEEN) {
                rows[cols[i] + d] = true;
            }
        }
        for (int i = 0; i < MAXQUEEN; i++) {
            if (!rows[i]) {//能放
                cols[n] = i;
                if (n < MAXQUEEN - 1) {
                    getCount(n + 1);
                } else {
                    //找到一套完整的方案
                    num++;
                    printQueen();
                }
            }
        }
    }

    private static void printQueen() {
        System.out.println("第" + num + "套方案");
        for (int i = 0; i < MAXQUEEN; i++) {
            for (int j = 0; j < MAXQUEEN; j++) {
                if (i == cols[j]) {
                    System.out.print("0 ");
                } else {
                    System.out.print("+ ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        getCount(0);
    }
}