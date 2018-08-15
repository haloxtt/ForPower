import java.util.Arrays;

/**
 * 堆排序
 */
public class MyHeapSort {
    public static void main(String[] args) {
        int[] arr = {6,1,355,26,72,833,115,63,74,743,12,57,36,89,13,65,9,46,87,43};
        for (int i = arr.length / 2 -1; i >= 0; i--) {
            MaxHeap(arr,i,arr.length - 1);
        }
        for (int i = arr.length - 1; i >= 0 ; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            MaxHeap(arr,0,i-1);
        }
        System.out.println(Arrays.toString(arr));
    }
    private static void MaxHeap(int[] arr,int index,int len) {//构建大顶堆
        int j;
        int temp = arr[index];
        for (j = index * 2; j < len; j *= 2) {
            if (arr[j] < arr[j + 1])
                j++;//小于左子节点
            if (temp >= arr[j])
                break;//说明temp已经是最大的
            arr[index] = arr[j];
            index = j;
        }
        arr[index] = temp;
    }
}
