package com.linkedlist;

public class RotateList {
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
        rotate(head, 2);
        head.display();
    }

    public static void rotate(ListNode head, int k) {


    }
}
