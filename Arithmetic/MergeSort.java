import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {50,10,90,30,70,40,80,60,20};
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
        // 把较小的数先移到新数组中
        while (i <= mid && j <= high) {
            temp[k++] = arr[i]<=arr[j]?arr[i++]:arr[j++];
        }
        // 把左边剩余的数移入数组
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        // 把右边边剩余的数移入数组
        while (j <= high) {
            temp[k++] = arr[j++];
        }
        // 把新数组中的数覆盖nums数组
        for (int l = 0; l < temp.length; l++) {
            arr[l + low] = temp[l];
        }
    }
}
