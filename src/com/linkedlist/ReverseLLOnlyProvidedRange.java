package com.linkedlist;

public class ReverseLLOnlyProvidedRange {
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

        ListNode headNew = reverseBetween(head, 2, 4);
        //Original => 1 -> 2 -> 3 -> 4 -> 5 -> END
        // Expected => 1 -> 4 -> 3 -> 2 -> 5 -> END
        headNew.display();
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right)
            return head;
        ListNode prev = null;
        ListNode current = head;

        for (int i = 0; current != null && i < left - 1; i++) {
            prev = current;
            current = current.next;
        }

        ListNode newEnd = current; // left starting node to point to remaining of the list hence storing it to fix the pointer later on
        // Reverse the list till right - left + 1 elements
        ListNode tempPrev = null;
        ListNode next = null;
        for (int i = 0; current != null && i < right - left + 1; i++) {
            next = current.next;
            current.next = tempPrev;
            tempPrev = current;
            current = next;
        }

        // Fix the pointers
        newEnd.next = next; // Because next is pointing to the element of the right index
        if (prev != null) {
            prev.next = tempPrev; // Prev is before the left index hence pointing to the new intermediate head
        } else {
            // If someone enters left index as Zero then prev will be null and temp prev should be the new head
            head = tempPrev;
        }

        return head;
    }

//    public static ListNode reverseBetween(ListNode head, int left, int right) {
//        ListNode leftNode = head;
//        ListNode remainig = head;
//        //Get hold of left element
//        while (left > 1) {
//            leftNode = leftNode.next;
//            left--;
//            remainig = remainig.next;
//
//        }
//
//        ListNode leftHead = reverse(leftNode);
//
//        // Skip till the (length - right index)
//        int skipCount = size(head) - right;
//
//        while (skipCount > 0)
//            leftHead = leftHead.next;
//        int counter = 0;
//        ListNode current = head;
//        ListNode temp = head;
//        while (head != null && leftHead != null && counter >= left && counter <= right) {
//            if (counter >= left && counter <= right) {
//                current.next = leftHead;
//                leftHead = leftHead.next;
//            } else {
//                current.next = temp;
//            }
//            temp = temp.next;
//        }
//
//        return head;
//    }

    private static int size(ListNode head) {
        int size = 0;
        while (head != null) {
            head = head.next;
            size++;
        }

        return size;
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = prev;
            // For the next iteration
            prev = current;
            current = temp;
        }
        return prev;
    }
}
