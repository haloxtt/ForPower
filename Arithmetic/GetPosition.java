

/**
 *String[], 给你两个 字母 A，B，这两个字母在数组中有若干个，找出这两个字母之间最短的距离
 */
public class GetPosition {
    private int getMinPosition(String str,char a,char b) {
        char[] arr = str.toCharArray();
        int lasta = -1;//上一个A的位置
        int lastb = -1;//上一个B位置
        int min = Integer.MAX_VALUE;//最短距离
        for (int i = 0; i < arr.length; i++) {
            if (a == arr[i]) {//当前遍历到A字母
                if (lastb != -1) {//最近的b
                    min = Math.min(i - lastb - 1,min);
                }
                lasta = i;
            } else if (b == arr[i]) {
                if (lasta != -1) {//最近的a
                    min = Math.min(i - lasta - 1,min);
                }
                lastb = i;
            }
        }
        return min;
    }
    public static void main(String[] args) {
        GetPosition getPosition = new GetPosition();
        String str = "avnjnasknkasan";
        char a = "a".charAt(0);
        char b = "n".charAt(0);
        System.out.println(getPosition.getMinPosition(str,a,b));
    }
}
