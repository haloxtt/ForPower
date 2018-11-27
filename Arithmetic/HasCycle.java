/**
 * @description:141.是否有环
 * @author: xietaotao
 * @create: 2018-11-26 10:53
 **/
public class HasCycle {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != null && fast != null && fast.next != null) {
            if (slow == fast)
                return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
    private class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public static void main(String[] args) {
        HasCycle hasCycle = new HasCycle();
        ListNode l0 = hasCycle.new ListNode(3);
        ListNode l1 = hasCycle.new ListNode(2);
        ListNode l2 = hasCycle.new ListNode(0);
        ListNode l3 = hasCycle.new ListNode(-4);
        l0.next = l1;
        l1.next = l2;
//        l2.next = l3;
//        l3.next = l4;
//        l4.next = l0;
        System.out.println(hasCycle.hasCycle(l0));
    }
}
