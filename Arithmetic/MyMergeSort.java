import java.util.Arrays;

/**
 * 归并排序
 */
public class MyMergeSort {
    public static void main(String[] args) {
        int[] arr = {5,15,2,-1,51,56,2,-1,52,66,-5};
        mergeSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
    private static void mergeSort(int[] arr,int low,int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            mergeSort(arr,low,mid);
            mergeSort(arr,mid + 1,high);
            merge(arr,low,mid,high);
        }
    }
    private static void merge(int[] arr,int low,int mid,int high) {
        int[] temp = new int[high - low + 1];
        int k = 0;
        int j = mid + 1;
        int i = low;
        while (i <= mid && j <= high) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= high) {
            temp[k++] = arr[j++];
        }
        for (int l = 0; l < temp.length; l++) {
            arr[l + low] = temp[l];
        }
    }
}
