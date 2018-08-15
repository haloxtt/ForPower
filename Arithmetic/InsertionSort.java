/**
 * 直接插入排序
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {7,12,1,687,3,4,87,35,61,46,22,8903,44,65};
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i-1]) {//开始出现的不是有序的位置说明要重新插入
                int temp = arr[i];//记录要插入的值
                int k = i;
                for (int j = i-1; j >= 0 && arr[j] > temp; j--,k--) {//往前遍历寻找插入的位置
                    arr[k] = arr[k-1];//大于插入数的往后挪位
                }
                arr[k] = temp;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
    }
}
