import java.util.HashMap;
import java.util.Map;

/**
 * 泊松分酒
 *
 *
 */
public class ShareWine {
    static int b1 = 12;//第一个瓶子的容量
    static int b2 = 8;//第二个瓶子的容量
    static int b3 = 5;//第三个瓶子的容量
    static int  m = 13;//目标容量
    static Map<String,String> map = new HashMap<String, String>();
    public static void main(String[] args) {
        shareWine(12,0,0,map);
    }

    /**
     *
     * @param bl1 第一个瓶子当前酒量
     * @param bl2 第二个瓶子当前酒量
     * @param bl3 第三个瓶子当前酒量
     */
    private static void shareWine(int bl1, int bl2, int bl3, Map<String,String> map) {
        System.out.println("当前结果bl1:"+bl1+" bl2:"+bl2+" bl3:"+bl3);
        if (bl1 == m || bl2 == m || bl3 == m) {
            System.out.println("结果bl1:"+bl1+" bl2:"+bl2+" bl3:"+bl3);
            return;
        }
        String key = String.valueOf(bl1) + String.valueOf(bl2) + String.valueOf(bl3);
        if (map.containsKey(key)) {
            System.out.println("无法倒出结果，已经进入死循环");
            return;
        } else {
            map.put(key,"");
        }
        if (bl2 != 0 && bl3 != b3) {//第二个瓶子有酒，第三个瓶子未满
            if (bl2 + bl3 <= b3) {//可将第二个瓶子的酒全倒入第三个瓶子
                shareWine(bl1,0,bl2 + bl3,map);
            } else {
                shareWine(bl1,bl2 - (b3 - bl3),b3,map);
            }
        } else if (bl3 == b3) {//瓶子三满了
            if (bl1 + b3 <= b1) {//可将瓶子三的酒都倒入瓶子一
                shareWine(bl1 + bl3,bl2,0,map);
            } else {
                shareWine(b1,bl2,bl3 - (b1 - bl1),map);
            }
        } else if (bl2 == 0) {//从瓶子一倒入瓶子二
            if (bl1 > b2) {
                shareWine(bl1 - b2,b2,bl3,map);
            } else {
                shareWine(0,bl2 + bl1,bl3,map);
            }
        }
    }
}
