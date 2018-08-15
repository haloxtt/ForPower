import java.lang.System;
import java.util.Arrays;

public class Demo7 {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        int[] arr1 = new int[5];
        int[] arr2 =  new int[3];
        int[] arr3 = Arrays.copyOf(arr,5);
        for (int i:arr3) {
            System.out.println(i);
        }
    }
}
