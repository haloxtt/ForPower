/**
 * 循环赛算法
 *
 */
public class RoundMatch {

    private static void roundMatch(int[][] table,int n) {
        if (n == 1) {
            table[0][0] = 1;
            return;
        }
        int m = n / 2;
        //先填充左上区域
        roundMatch(table,m);
        //右上区域
        for (int i = 0; i < m; i++) {
            for (int j = m; j < n; j++) {
                table[i][j] = table[i][j - m] + m;
            }
        }
        //左下区域
        for (int i = m; i < n; i++) {
            for (int j = 0; j < m; j++) {
                table[i][j] = table[i - m][j] + m;
            }
        }
        //右下区域
        for (int i = m; i < n; i++) {
            for (int j = m; j <n; j++) {
                table[i][j] = table[i - m][j - m];
            }
        }
    }

    public static void main(String[] args) {
        RoundMatch roundMatch = new RoundMatch();
        int size = 8;
        int[][] table = new int[size][size];
        roundMatch.roundMatch(table, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }
}
