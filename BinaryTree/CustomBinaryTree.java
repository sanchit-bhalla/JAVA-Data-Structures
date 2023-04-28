package BinaryTree;

import java.io.*;
import java.util.*;

public class CustomBinaryTree {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void display(Node node) {
        if (node == null) {
            return;
        }

        String str = "";

        str += node.left == null ? "." : node.left.data + "";
        str += " <- " + node.data + " -> ";
        str += node.right == null ? "." : node.right.data + "";

        System.out.println(str);

        display(node.left);
        display(node.right);
    }

    public static void main(String[] args) throws Exception {
        Integer[] arr = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
                null };

        Node root = new Node(arr[0], null, null);
        Pair rp = new Pair(root, 1);

        Stack<Pair> st = new Stack<>();
        st.push(rp);

        int idx = 0;
        while (st.size() > 0) {
            Pair top = st.peek();
            if (top.state == 1) {
                idx++;
                if (arr[idx] != null) {
                    Node ln = new Node(arr[idx], null, null); // left Node
                    top.node.left = ln;

                    Pair left_pair = new Pair(ln, 1);
                    st.push(left_pair);
                } else {
                    top.node.left = null;
                }
                top.state++;

            } else if (top.state == 2) {
                idx++;
                if (arr[idx] != null) {
                    Node rn = new Node(arr[idx], null, null); // Right Node
                    top.node.right = rn;

                    Pair right_pair = new Pair(rn, 1);
                    st.push(right_pair);
                } else {
                    top.node.right = null;
                }
                top.state++;
            } else { // top.state == 3
                st.pop();
            }
        }

        display(root);
    }
}
