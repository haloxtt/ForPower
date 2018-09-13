import java.util.List;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

 示例：

 输入：1->2->5, 1->3->4
 输出：1->1->2->3->4->4
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        //创建新结点的头结点和要返回的链表
        ListNode head,listNode;
        if (l1.val > l2.val) {
            head = new ListNode(l2.val);
            l2 = l2.next;
        } else {
            head = new ListNode(l1.val);
            l1 = l1.next;
        }
        listNode = head;
        while (l1 != null && l2 != null) {
            ListNode node;
            if (l1.val > l2.val) {
                node = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                node = new ListNode(l1.val);
                l1 = l1.next;
            }
            listNode.next = node;
            listNode = node;
        }
        if (l1 != null) {
            listNode.next = l1;
        }
        if (l2 != null) {
            listNode.next = l2;
        }
        return head;
    }

    public static void main(String[] args) {
        MergeTwoLists mergeTwoLists = new MergeTwoLists();
        ListNode node0 = new ListNode(-9);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(5);
        ListNode node3 = new ListNode(7);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        node0.next = node1;
        node2.next = node3;
        ListNode node = mergeTwoLists.mergeTwoLists(node0,node2);
        while (node != null) {
            System.out.print(node.val + ",");
            node = node.next;
        }
    }
}
