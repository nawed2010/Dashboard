package com.linkedlist;

public class ReorderList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;
        head.display();

        // Reorder expected o/p => 1 -> 5 -> 2 -> 4 -> 3
        reorderList(head);
        head.display();
    }

    public static void reorderList(ListNode head) {
        // Find the mid of the list
        ListNode mid = findMid(head); // 3
        ListNode headSecond = reverse(mid);

        // head => 1 -> 2 -> 3 -> 4 -> 5 -> END
        // headSecond => 5 -> 4 -> 3 -> END

        // First assignment done before entering the loop
        // because new list should contain first head at the starting
        ListNode current = head;
        head = head.next;
        boolean useFirst = false; // As first is already done
        while (head != null && headSecond != null) {
            if (useFirst) {
                current.next = head;
                head = head.next;
            } else {
                current.next = headSecond;
                headSecond = headSecond.next;
            }
            current = current.next;
            useFirst = !useFirst;
        }
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }

    private static ListNode findMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
