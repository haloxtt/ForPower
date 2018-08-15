/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {99,61,4,83,22,39,13,56,77,85,69,82,23,12,33,11};
        int d = arr.length/2;//增量
        for (int i = 0; i < arr.length; i++) {
            while (d >= 1) {
                for (int j = 0; j+d < arr.length; j += d) {
                    if (arr[j] > arr[i+d]) {//交换两个需要换位的数
                        int temp = arr[j];
                        arr[j] = arr[j+d];
                        arr[j+d] = temp;
                    }
                }
                d = d/2;
            }
        }
        //为保证排序成功，最后再进行一次快排（采用二分插入排序）
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j-1]) {//需要进行二分插入
                    int left = 0;
                    int right = j-1;
                    int mid = (left+right)/2;
                    int temp = arr[j];
                    while (left <= right) {
                        if (arr[mid] < arr[j]) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                        mid = (left + right)/2;
                    }
                    for (int k = j; k >= left; k--) {//把插入位的后面的元素往后挪一位
                        arr[k] = arr[k-1];
                    }
                    arr[left] = temp;
                }
            }
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+",");
        }
    }
}
