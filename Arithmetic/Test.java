import java.util.ArrayList;

public class Test {
        public static boolean Find(int target, int[][] array) {//二维数组中的查找
                int row = array.length-1;
                int lineLength = array[0].length-1;
                for (int i = row,j = 0; j <= lineLength && i >= 0 ;) {
                        if(target < array[i][j]) {
                                i--;
                        } else if(target > array[i][j]) {
                                j++;
                        } else {
                                return true;
                        }
                }
                return false;
        }
        public static String replaceSpace(String str) { //将字符串中的空格替换成%20
                StringBuffer sbf = new StringBuffer();
                for (int i = 0; i < str.length() ; i++) {
                        if(" ".equals(String.valueOf(str.charAt(i)))){
                                sbf.append("%20");
                        } else {
                                sbf.append(str.charAt(i));
                        }
                }
                return sbf.toString();
        }
        public class ListNode {//输入一个链表，从尾到头打印链表每个节点的值。
               int val;
               ListNode next = null;
               ListNode(int val) {
                  this.val = val;
                }
    }
        public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
                //输入一个链表，从尾到头打印链表每个节点的值。
                ArrayList<Integer> array = new ArrayList<>();
                while(listNode != null) {
                    printListFromTailToHead(listNode.next);
                    array.add(listNode.val);
                }
                return array;
        }
        public static void main(String[] args) {
                /*  二维数组中的查找
                int[][] arr = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
                int taget = 5;
                boolean result = Find(taget,arr);
                System.out.println(result);
                int i = 1;
                System.out.println(i++);
                System.out.println(++i);
                */
                System.out.println(replaceSpace("we are happy"));
        }

}
