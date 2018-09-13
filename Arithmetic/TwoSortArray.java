/**
 * [ 8,  9  ,  1  ,  2  , 3，4，5，6，7],
 */
public class TwoSortArray {
    private int getPositon(int[] arr,int insert,int low,int high) {
        int mid  = (low + high) / 2;
        if (arr[mid] == insert)
            return mid;
        if ((insert > arr[low] && insert < arr[mid]) || ((insert > arr[low]) && (insert > arr[high]))) {
            return getPositon(arr,insert,low,mid - 1);
        } else {
            return getPositon(arr,insert,mid + 1,high);
        }
    }

    public static void main(String[] args) {
        TwoSortArray twoSortArray = new TwoSortArray();
        int insert = 5;
        int[] arr =  {8,9,1,2,3,4,5,6,7};
        System.out.println(twoSortArray.getPositon(arr,insert,0,arr.length - 1));
    }
}
