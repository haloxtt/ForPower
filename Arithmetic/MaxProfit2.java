/**
 * @description:122，买卖股票的最佳时机2
 * @author: xietaotao
 * @create: 2018-11-15 10:25
 **/
public class MaxProfit2 {
    public int maxProfit(int[] prices) {
        if (prices.length < 2)
            return 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int last = 0;
        int totalProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < last) {
                if (max - min < 0) {
                    max = prices[i];
                    continue;
                }
                totalProfit += max - min;
                max = Integer.MIN_VALUE;
                min = Integer.MAX_VALUE;
            }
            if (prices[i] < min) {
                min = prices[i];
            }
            if (prices[i] > max) {
                max = prices[i];
            }
            last = prices[i];
        }
        return (max - min > 0) ? totalProfit + (max - min) : totalProfit;
    }

    public static void main(String[] args) {
        int[] arr = {7,6,4,3,9};
        MaxProfit2 maxProfit2 = new MaxProfit2();
        System.out.println(maxProfit2.maxProfit(arr));
    }
}
