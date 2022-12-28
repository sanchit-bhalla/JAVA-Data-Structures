package LinkedList;

import java.io.*;
import java.util.*;

public class CustomLL {
    public static class Node {
        int data;
        Node next;
    }

    public static class LinkedList {
        Node head;
        Node tail;
        int size;

        void addLast(int val) {
            Node newNode = new Node();
            newNode.data = val;
            newNode.next = null;

            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }

            size++;
        }

        public int size() {
            return size;
        }

        public void display() {
            // for (Node temp = head; temp != null; temp = temp.next) {
            // System.out.print(temp.data + " ");
            // }
            // System.out.println();
            Node temp = head;
            while (temp != null) {
                System.out.print(temp.data + " ");
                temp = temp.next;
            }
            if (size > 0) {
                System.out.println();
            }
        }

        public void removeFirst() {
            if (size == 0) {
                System.out.println("List is empty");
            } else if (head.next == null) {
                // only 1 element
                head = tail = null;
                size = 0;
            } else {
                head = head.next;
                size--;
            }
        }

        public int getFirst() {
            if (size == 0) {
                System.out.println("List is empty");
                return -1;
            }
            return head.data;
        }

        public int getLast() {
            if (size == 0) {
                System.out.println("List is empty");
                return -1;
            }
            return tail.data;
        }

        public int getAt(int idx) {
            if (size == 0) {
                System.out.println("List is empty");
                return -1;
            } else if (idx < 0 || idx >= size) {
                System.out.println("Invalid arguments");
                return -1;
            } else {
                Node temp = head;
                // while (temp != null && idx > 0) {
                // temp = temp.next;
                // idx--;
                // }
                // return temp.data;

                for (int i = 0; i < idx; i++) {
                    temp = temp.next;
                }
                return temp.data;
            }
        }

        public void addFirst(int val) {
            Node newNode = new Node();
            newNode.data = val;
            newNode.next = head;
            head = newNode;

            if (size == 0) {
                tail = newNode;
            }

            size++;
        }

        public void addAt(int idx, int val) {
            if (idx < 0 || idx > size) {
                System.out.println("Invalid arguments");
            } else if (idx == 0) {
                addFirst(val);
            } else if (idx == size) {
                addLast(val);
            } else {
                Node newNode = new Node();
                newNode.data = val;

                Node prev = head;
                for (int i = 0; i < idx - 1; i++) {
                    prev = prev.next;
                }

                newNode.next = prev.next;
                prev.next = newNode;
                size++;
            }
        }

        public void removeLast() {
            if (size == 0) {
                System.out.println("List is empty");
            } else if (size == 1) {
                head = tail = null;
                size = 0;
            } else {
                Node temp = head;

                // Way1
                // while (temp.next.next != null) {
                // temp = temp.next;
                // }

                // Way2
                for (int i = 0; i < size - 2; i++) {
                    temp = temp.next;
                }

                temp.next = null;
                tail = temp;
                size--;
            }
        }

        public void removeAt(int idx) {
            if (size == 0) {
                System.out.println("List is empty");
            } else if (idx < 0 || idx >= size) {
                System.out.println("Invalid arguments");
            } else if (idx == 0) {
                removeFirst();
            } else if (idx == size - 1) {
                removeLast();
            } else {
                Node temp = head;
                for (int i = 0; i < idx - 1; i++) {
                    temp = temp.next;
                }
                temp.next = temp.next.next;
                size--;
            }
        }

        private Node getNodeAt(int idx) {
            Node temp = head;
            for (int i = 0; i < idx; i++) {
                temp = temp.next;
            }
            return temp;
        }

        // Reverse LL (iteratively) by changing data property
        public void reverseDI() {
            int li = 0;
            int ri = size - 1;

            while (li < ri) {
                Node left = getNodeAt(li);
                Node right = getNodeAt(ri);

                // swap
                int temp = left.data;
                left.data = right.data;
                right.data = temp;

                li++;
                ri--;
            }
        }

        // Reverse LL (iteratively) by changing next property (pointers) of Node
        public void reversePI() {
            Node prev = null;
            Node curr = head;

            while (curr != null) {
                Node next = curr.next;

                curr.next = prev;
                prev = curr;
                curr = next;
            }

            tail = head;
            head = prev;
        }

        // Return Kth node from last without using size data member directly or
        // indirectly
        // k is 0-based index
        // Assume that valid values of k will be passed.
        public int kthFromLast(int k) {
            // APPROACH: Use 2 pointers
            // 1st pointer start from head
            // 2nd pointer start from kth node from start
            // Both will traverse 1 step forward. When 2nd pointer reaches tail node, then
            // 1st pointer will be at kth node from last
            Node slow = head;
            Node fast = head;
            for (int i = 0; i < k; i++) {
                fast = fast.next;
            }

            while (fast != tail) {
                fast = fast.next;
                slow = slow.next;
            }

            return slow.data;
        }

        // Middle of Linked LIst
        // In linked list of even size, consider end of first half as mid.
        public int mid() {
            if (head == null) {
                return -1;
            }
            Node slow = head;
            Node fast = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            return slow.data;
        }

        // Merge two sorted LinkedList
        public static LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {
            LinkedList merged_ll = new LinkedList();
            Node temp1 = l1.head;
            Node temp2 = l2.head;
            while (temp1 != null && temp2 != null) {
                if (temp1.data <= temp2.data) {
                    merged_ll.addLast(temp1.data);
                    temp1 = temp1.next;
                } else {
                    merged_ll.addLast(temp2.data);
                    temp2 = temp2.next;
                }
            }

            while (temp1 != null) {
                merged_ll.addLast(temp1.data);
                temp1 = temp1.next;
            }

            while (temp2 != null) {
                merged_ll.addLast(temp2.data);
                temp2 = temp2.next;
            }

            return merged_ll;
        }

        public static Node midNode(Node head, Node tail) {
            Node s = head;
            Node f = head;
            while (f != tail && f.next != tail) {
                s = s.next;
                f = f.next.next;
            }
            return s;
        }

        public static LinkedList mergeSort(Node head, Node tail) {
            // Base case
            if (head == tail) {
                LinkedList baseLL = new LinkedList();
                baseLL.addLast(head.data);
                return baseLL;
            }

            LinkedList sortedLL = new LinkedList();
            Node mid = midNode(head, tail); // Find mid
            LinkedList l1 = mergeSort(head, mid);
            LinkedList l2 = mergeSort(mid.next, tail);
            sortedLL = mergeTwoSortedLists(l1, l2);
            return sortedLL;
        }

        // Remove dublicates from sorted Linked List
        // Way 1
        // public void removeDuplicates() {
        // if (size <= 1) {
        // return;
        // }

        // Node prev = head;
        // Node current = head.next;
        // while (current != null) {
        // if (prev.data == current.data) {
        // prev.next = null;
        // current = current.next;
        // size--;
        // } else {
        // prev.next = current;
        // prev = current;
        // current = current.next;
        // }
        // }
        // }

        // Way 2
        public void removeDuplicates() {
            LinkedList res = new LinkedList();

            while (this.size() > 0) {
                int val = this.getFirst();
                this.removeFirst();

                if (res.size() == 0 || res.tail.data != val) {
                    res.addLast(val);
                }
            }

            // we can't do this = res bcz this is read only
            this.head = res.head;
            this.tail = res.tail;
            this.size = res.size;
        }

        // Way 1
        // public void oddEven() {
        // if (size <= 1) {
        // return;
        // }

        // Node oh = null;
        // Node ot = null;
        // Node eh = null;
        // Node et = null;

        // Node temp = head;
        // while (temp != null) {
        // if (temp.data % 2 == 0) {
        // if (eh == null) {
        // eh = temp;
        // et = temp;
        // } else {
        // et.next = temp;
        // et = temp;
        // }
        // } else {
        // if (oh == null) {
        // oh = temp;
        // ot = temp;
        // } else {
        // ot.next = temp;
        // ot = temp;
        // }
        // }
        // temp = temp.next;
        // }
        // if (ot != null)
        // ot.next = eh;
        // head = oh == null ? eh : oh;
        // tail = et != null ? et : ot;
        // tail.next = null;
        // }

        public void oddEven() {
            LinkedList odd = new LinkedList();
            LinkedList even = new LinkedList();

            while (this.size > 0) {
                int val = this.getFirst();
                this.removeFirst(); // removing node so that at a time 1 node will be present at max in one place so
                                    // that space complexity will not increase

                if (val % 2 == 0) {
                    even.addLast(val);
                } else {
                    odd.addLast(val);
                }
            }

            if (odd.size > 0 && even.size > 0) {
                odd.tail.next = even.head;
                this.head = odd.head;
                this.tail = even.tail;
                this.size = even.size + odd.size;
            } else if (odd.size > 0) {
                this.head = odd.head;
                this.tail = odd.tail;
                this.size = odd.size;
            } else if (even.size > 0) {
                this.head = even.head;
                this.tail = even.tail;
                this.size = even.size;
            }
        }

        // Way 1
        // public void kReverse(int k) {
        // if (k == 0 || k > this.size)
        // return;

        // Node dummyHead = null;
        // Node dummyTail = null;
        // int dummySize = this.size;

        // while (this.size > 0) {
        // LinkedList tempK = new LinkedList();

        // for (int i = 0; i < k && this.size > 0; i++) {
        // int val = this.getFirst();
        // this.removeFirst();

        // tempK.addLast(val);
        // }
        // if (this.size != 0 || tempK.size == k) {
        // tempK.reversePI();
        // }

        // if (dummyHead == null) {
        // dummyHead = tempK.head;
        // dummyTail = tempK.tail;
        // } else {
        // dummyTail.next = tempK.head;
        // dummyTail = tempK.tail;
        // }
        // }

        // this.head = dummyHead;
        // this.tail = dummyTail;
        // this.size = dummySize;
        // }

        // Way 2
        public void kReverse(int k) {
            LinkedList prev = null;

            while (this.size > 0) {
                LinkedList curr = new LinkedList();

                if (this.size >= k) {
                    for (int i = 0; i < k; i++) {
                        int val = this.getFirst();
                        this.removeFirst();

                        curr.addFirst(val); // so that new linked list will be reversed
                    }
                } else {
                    int remSize = this.size;
                    for (int i = 0; i < remSize; i++) {
                        int val = this.getFirst();
                        this.removeFirst();

                        curr.addLast(val); // new linked list will not be reversed
                    }
                }

                if (prev == null) {
                    prev = curr;
                } else {
                    prev.tail.next = curr.head;
                    prev.tail = curr.tail;
                    prev.size += curr.size;
                }
            }

            this.head = prev.head;
            this.tail = prev.tail;
            this.size = prev.size;
        }

        private void displayReverseHelper(Node node) {
            if (node == null)
                return;
            else {
                displayReverseHelper(node.next);
                System.out.print(node.data + " ");
            }
        }

        // Display Reverse LinkedList Without actually reversing it
        public void displayReverse() {
            displayReverseHelper(head);
            System.out.println();
        }

        // WAY 1
        private void reversePRHelper1(Node node) {
            if (node == null)
                return;
            else if (node.next == null) {
                this.head = node;
                this.tail = node;
            } else {
                reversePRHelper1(node.next);
                this.tail.next = node;
                this.tail = node;
            }
        }

        // Way 2
        private void reversePRHelper2(Node node) {
            if (node == null || node.next == null)
                return;
            else {
                reversePRHelper2(node.next);
                node.next.next = node;
            }
        }

        // Reverse the linked list by using recursion and changing the "next" data
        // member (i.e Pointers) of nodes
        // Way 1
        public void reversePR1() {
            reversePRHelper1(head);
        }

        // Way 2
        public void reversePR2() {
            reversePRHelper2(head);
            head.next = null; // bcz head still points to next node which was the next node before reversing

            // Swap head and tail after reversing
            Node temp = head;
            head = tail;
            tail = temp;
        }

        private boolean IsPalindromeHelper(Node node) {
            if (node == null) {
                return true;
            }

            boolean rres = IsPalindromeHelper(node.next);
            if (rres == false) {
                return false;
            } else if (pleft.data != node.data) {
                return false;
            } else {
                pleft = pleft.next;
                return true;
            }
        }

        // Check whether Linked List is Palindrome or not
        /*
         * Iterative WAY --> Create new Reverse LL and then compare the 2 LL
         * Complexity - TC --> O(n) SC --> O(n)
         */
        // Recursive Way
        Node pleft; // Created in Heap --> It will track left node and pLeft will track right node

        public boolean IsPalindrome() {
            pleft = head;
            return IsPalindromeHelper(head);
        }

        private void fold(Node right, int floor) {
            if (right == null) {
                return;
            }

            fold(right.next, floor + 1);

            if (floor > size / 2) {
                Node temp = fleft.next;
                fleft.next = right;
                right.next = temp;
                fleft = temp;
            } else if (floor == size / 2) {
                tail = right;
                tail.next = null;
            }
        }

        Node fleft; // created in heap

        public void fold() {
            if (size <= 2) {
                return;
            }
            fleft = head;
            fold(head, 0);
        }

        private static int addLLHelper(Node one, int pv1, Node two, int pv2, LinkedList res) {
            // pv1 --> place value of LL one
            // pv2 --> place value of LL two
            if (one == null && two == null) {
                return 0;
            }

            int carry = 0;
            if (pv1 < pv2) {
                carry = addLLHelper(one, pv1, two.next, pv2 - 1, res);

                int s = two.data + carry;
                res.addFirst(s % 10);
                return s / 10; // this will act as carry for prev call

            } else if (pv1 > pv2) {
                carry = addLLHelper(one.next, pv1 - 1, two, pv2, res);

                int s = one.data + carry;
                res.addFirst(s % 10);
                return s / 10; // this will act as carry for prev call

            } else {
                carry = addLLHelper(one.next, pv1 - 1, two.next, pv2 - 1, res);

                int s = two.data + one.data + carry;
                res.addFirst(s % 10);
                return s / 10; // this will act as carry for prev call
            }
        }

        /*
         * Add 2 linked list
         * 1. Don't reverse the linked lists in order to access them from least
         * significant digit
         * to most significant.
         * 2. Don't use arrays or explicit extra memory.
         * 3. Don't convert linked lists to number, add them up and convert the result
         * back
         * to a linked list.
         */
        public static LinkedList addTwoLists(LinkedList one, LinkedList two) {
            LinkedList res = new LinkedList();
            int c = addLLHelper(one.head, one.size, two.head, two.size, res);
            if (c > 0) {
                res.addFirst(c);
            }

            return res;
        }

        public static int findIntersection(LinkedList one, LinkedList two) {
            int diff = Math.abs(one.size - two.size);
            Node t1 = one.head;
            Node t2 = two.head;

            if (one.size > two.size) {
                for (int i = 0; i < diff; i++) {
                    t1 = t1.next;
                }
            } else {
                for (int i = 0; i < diff; i++) {
                    t2 = t2.next;
                }
            }

            while (t1 != t2) {
                t1 = t1.next;
                t2 = t2.next;
            }
            return t1.data;
        }
    }

    public static void testList(LinkedList list) {
        for (Node temp = list.head; temp != null; temp = temp.next) {
            System.out.println(temp.data);
        }
        System.out.println(list.size);

        if (list.size > 0) {
            System.out.println(list.tail.data);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList list = new LinkedList();

        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("addLast")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                list.addLast(val);
            }
            str = br.readLine();
        }

        testList(list);
    }

}
