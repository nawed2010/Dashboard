package com.linkedlist;

public class ReverseInKGroups {

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

        ListNode newHead = reverseKGroup(head, 2);
        newHead.display();

    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k < 2 || head == null)
            return head;
        int size = size(head);
        ListNode prev = null;
        ListNode current = head;
        ListNode next = head.next;
        ListNode prevEnd = current;
        ListNode nextNewHead = prev;
        int counter = k;
        boolean isHeadUpdated = false;
        while (size >= counter && current != null) {
            if (counter == 0) {
                counter = k; // reset the counter once k element is processed
                if (!isHeadUpdated) {
                    head = prev; // As this has to be done only in the first pass
                    isHeadUpdated = true;
                }
                prevEnd.next = nextNewHead;
            }
            if (counter == k) {
                prevEnd = current;
            }

            if (counter == 1)
                nextNewHead = current;

            next = current.next;
            current.next = prev;
            // For the next iteration
            prev = current;
            current = next;
            counter--;
            size--;
        }
        return head;
    }

    private static int size(ListNode head) {
        int size = 0;
        while (head != null) {
            head = head.next;
            size++;
        }
        return size;
    }
}
