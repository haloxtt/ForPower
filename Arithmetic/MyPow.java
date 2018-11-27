/**
 * @description:50. Pow(x, n)
 * @author: xietaotao
 * @create: 2018-11-06 18:21
 **/
public class MyPow {

    public double myPow(double x, int n) {
        double result = 1;
        for (int i = 0; i < n; i++) {
            result *= x;
        }
        return result;
    }

    public static void main(String[] args) {
        double x = 5.5;
        int n = Integer.MAX_VALUE;
        MyPow myPow = new MyPow();
        System.out.println(myPow.myPow(x,n));
    }
}
