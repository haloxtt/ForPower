/**
 * 汉诺塔算法
 *
 */
public class Hanoi {
    public static void main(String[] args) {
        hanoi(3,1,2,3);
    }
    private static void hanoi(int n,int p1,int p2,int p3) {
        if (n == 1) {
            System.out.println("从"+p1+"移动到了"+p3);
        } else {
            hanoi(n-1,p1,p3,p2);
            System.out.println("从"+p1+"移动到了"+p3);
            hanoi(n-1,p2,p1,p3);
        }
    }
}
