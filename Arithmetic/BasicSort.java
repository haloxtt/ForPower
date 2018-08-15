import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基数排序
 */
public class BasicSort {
    public static void main(String[] args) {
        int[] arr = {2,151,1,212,415,63,121,41,456,375,2342,121,12,341,441,2,151,2,46,14,234,23,23,24};
        basicSort(arr);
    }
    private static void basicSort(int[] arr) {
        List<ArrayList> list = new ArrayList<ArrayList>();
        for (int i = 0; i < 10; i++) {//构建list
            ArrayList q = new ArrayList();
            list.add(q);
        }
        int max = 0; 
        for (int i = 0; i < arr.length; i++) {//找出最大值
            if (arr[i] > max)
                max = arr[i];
        }
        int times = 0;//比较次数
        while (max > 0) {
            max = max / 10;
            times++;
        }
        for (int i = 0; i < times; i++) {
            for (int j = 0; j < arr.length; j++) {
                int num = arr[j] % (int)Math.pow(10,i + 1) / (int)Math.pow(10,i);
                ArrayList que = list.get(num);
                que.add(arr[j]);
            }
            int count = 0;
            for (int j = 0; j < 10; j++) {
                ArrayList<Integer> que = list.get(j);
                while (que.size() > 0) {
                    arr[count++] = que.get(0);
                    que.remove(0);
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
