public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {1,4,51,2,5,6,8,23,11,53};
        int temp = 0;
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            int min = 1000;
            for (int j = i; j < arr.length; j++) {//往后遍历找到一个最小的丢到前面
               if (arr[j] < min) {
                   min = arr[j];
                   k = j;
               }
            }
            temp = arr[i];
            arr[i] = arr[k];
            arr[k] =temp;
        }
        for (int i : arr) {
            System.out.print(i+",");
        }
    }
}
