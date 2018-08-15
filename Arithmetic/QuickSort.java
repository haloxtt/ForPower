import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {7,5,15,51,2,42,1,24,-1};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    private static void quickSort(int[] arr,int low,int high) {
        if (low < high) {
            int middle = getMiddle(arr,low,high);
            quickSort(arr,low,middle -1);
            quickSort(arr,middle + 1,high);
        }
    }
    private static int getMiddle(int[] arr,int low,int high) {
        int temp = arr[low];
        while (low < high) {
            while (low < high && temp <= arr[high]) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && temp >= arr[low]) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = temp;
        return low;
    }
}
