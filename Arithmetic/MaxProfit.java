/**
 * @description:121，买卖股票的最佳时机
 * @author: xietaotao
 * @create: 2018-11-14 18:45
 **/
public class MaxProfit {
    public int maxProfit(int[] prices) {
        int maxPrice = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                int price = prices[i] - min;
                if (price > maxPrice)
                    maxPrice = prices[i] - min;
            }
        }
        return maxPrice;
    }
    public static void main(String[] args) {
        MaxProfit maxProfit = new MaxProfit();
        int[] arr = {1,7};
        System.out.println(maxProfit.maxProfit(arr));

    }
}
