import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * leetcode 83
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

 示例 1:

 输入: 1->1->2
 输出: 1->2
 示例 2:

 输入: 1->1->2->3->3
 输出: 1->2->3
 */
public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        int last = head.val;
        ListNode node = head;
        ListNode result = node;
        while (head.next != null) {
            if (head.next.val != last) {
                node.next = head.next;
                node = node.next;
                last = head.next.val;
            }
            head = head.next;
        }
        if (head != null && head.val == last)
            node.next = null;
        return result;
    }

    public static void main(String[] args) {
        ListNode node0 = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(3);
        node0.next = node1;
        /*node1.next = node2;
        *//*node2.next = node3;
        node3.next = node4;
        node4.next = node5;*/
        DeleteDuplicates deleteDuplicates = new DeleteDuplicates();
        ListNode node = deleteDuplicates.deleteDuplicates(node0);
        while (node != null) {
            System.out.print(node.val + "-->");
            node = node.next;
        }
    }
}
