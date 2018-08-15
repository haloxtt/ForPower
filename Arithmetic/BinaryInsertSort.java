/**
 * 二分插入排序
 */
public class BinaryInsertSort {
    public static void main(String[] args) {
        int[] arr = {7,36,1,27,2,84,6,12,88,37,33,62};
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i-1]) {
                int left = 0;
                int right = i-1;
                int mid = (left + right)/2;
                int temp = arr[i];
                while (left <= right) {//注意边界，相等的时候也需要判断最后一次与mid的大小
                    if (arr[mid] < temp) {
                        left = mid + 1;
                    } else {
                        right = mid -1;
                    }
                    mid = (left+right)/2;
                }
                for (int j = i; j >= left && j > 0; j--) {
                    arr[j] = arr[j-1];
                }
                arr[left] =temp;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
    }
}
