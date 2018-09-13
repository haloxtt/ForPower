/**
 * 最长子字符串
 */
public class LCSE {
    static int max = 0;
    private static int lCSE(String A,String B) {
        int n = A.length();
        int m = B.length();
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) {
                dp[i][0] = 1;
                for (int j = i + 1; j < n; j++) {//第一列的初始化
                    dp[j][0] = 1;
                }
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (a[0] == b[i]) {
                dp[0][i] = 1;
                for (int j = i + 1; j < m; j++) {//第一行的初始化
                    dp[0][j] = 1;
                }
                break;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) { //动态方程
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                 max = dp[i][j] > max ? dp[i][j] : max;
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        return dp[n - 1][m - 1];
    }
    public static void main(String[] args) {
        System.out.println(lCSE("android","random"));
        System.out.println("最大长度为："+max);
    }
}
