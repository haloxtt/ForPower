
public class BackPack {
    static int[] weight = {25, 30, 60, 35, 20, 50, 40};
    static int[] values = {20, 40, 45, 50, 10, 45, 50};
    static int capacity = 150;

    public static void main(String[] args) {
        backPack();
    }
    private static void backPack() {
        int size = values.length;
        double[] prices = new double[size];
        int[] tags = new int[size];
        for (int i = 0; i < size; i++) {
            prices[i] = (double) values[i]/weight[i];
            tags[i] = i;
        }

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (prices[i] < prices[j]) {
                    double temp = prices[i];
                    prices[i] = prices[j];
                    prices[j] = temp;
                    int tag = tags[i];
                    tags[i] = tags[j];
                    tags[j] = tag;
                }
            }
        }

        int pick = 0;
        for (int i = 0; i < size; i++) {
            while (weight[tags[i]] < capacity) {
                System.out.println(weight[tags[i]]);
                capacity -= weight[tags[i]];
            }
        }
    }
}
