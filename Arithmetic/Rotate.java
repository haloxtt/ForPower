import java.util.Arrays;

/**
 * leetcode 48
 *
 给定一个 n × n 的二维矩阵表示一个图像。

 将图像顺时针旋转 90 度。

 说明：

 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

 示例 1:

 给定 matrix =
 [
 [1,2,3],
 [4,5,6],
 [7,8,9]
 ],

 原地旋转输入矩阵，使其变为:
 [
 [7,4,1],
 [8,5,2],
 [9,6,3]
 ]
 */
public class Rotate {
    public void rotate(int[][] matrix) {
        int n = matrix[0].length;
        if (n <= 1)
            return;
        for (int i = 0; i < n; i++) {//先做对角线交换
            for (int j = 0; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][n - i - 1];
                matrix[n - j - 1][n - i - 1] = temp;
            }
        }
        for (int i = 0; i < n / 2; i++) {//再做竖直变换
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[ n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        Rotate rotate = new Rotate();
        int[][] arr = new int[3][3];
        arr[0][0] = 1;
        arr[0][1] = 2;
        arr[0][2] = 3;
        arr[1][0] = 4;
        arr[1][1] = 5;
        arr[1][2] = 6;
        arr[2][0] = 7;
        arr[2][1] = 8;
        arr[2][2] = 9;
        rotate.rotate(arr);
    }
}
