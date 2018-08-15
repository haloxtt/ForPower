/**
 *
 * 给定两个链表分别代表两个非负整数，链表的每个结点分别存储整数的每位数字，且是逆序存储，即：
 * 数字最低位存储在链表表头，数字最高位存储在链表表尾。求解这两个整数的和并以相同的链表形式返回计算的结果。

 例如：   输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)   输出：7 -> 0 -> 8
 */
class twoSumLincked {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode((l1.val+l2.val)%10);//先获取个位数的值
        ListNode empty = new ListNode(0);
        ListNode resultNode = node;
        while (l1.next != null || l2.next != null) {
            if ((l1.val + l2.val) >= 10) {//判断当前位置相加是否大于10
                if (l1.next != null) {
                    l1.next.val++;//大于10选择其中一个链表的下一位+1
                } else if (l1.next == null && l2.next != null){
                    l2.next.val++;//其中一个为空则选择另外一个+1
                }
            }
            l1 = (l1.next != null)?l1.next:empty;
            l2 = (l2.next != null)?l2.next:empty;
            node.next = new ListNode((l1.val+l2.val)%10);//next依次再指向十位、百位...
            node = node.next;
        }
        if ((l1.val + l2.val ) >= 10) {
            node.next = new ListNode(1);//当两个数字的最高位相加也需要进位的时候
        }
        return resultNode;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(5);
        ListNode node6 = new ListNode(5);
        ListNode result = addTwoNumbers(node1,node6);

    }
}
