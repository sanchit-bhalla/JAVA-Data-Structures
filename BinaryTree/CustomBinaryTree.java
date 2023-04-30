package BinaryTree;

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

    public static int size(Node node) {
        if (node == null)
            return 0;

        return 1 + size(node.left) + size(node.right);
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;

        return node.data + sum(node.left) + sum(node.right);
    }

    // Preorder Traversal
    public static void traversalPre(Node node) {
        if (node == null)
            return;

        System.out.println(node.data);
        traversalPre(node.left);
        traversalPre(node.right);
    }

    // Inorder Traversal
    public static void traversalIn(Node node) {
        if (node == null)
            return;

        traversalIn(node.left);
        System.out.println(node.data);
        traversalIn(node.right);
    }

    // Postorder Traversal
    public static void traversalPost(Node node) {
        if (node == null)
            return;

        traversalPost(node.left);
        traversalPost(node.right);
        System.out.println(node.data);
    }

    public static int max(Node node) {
        if (node == null)
            return Integer.MIN_VALUE;

        int lm = max(node.left);
        int rm = max(node.right);
        int ans = Math.max(node.data, Math.max(lm, rm));
        return ans;
    }

    public static int height(Node node) {
        if (node == null)
            return -1; // -1 for edges, 0 for nodes

        int lsh = height(node.left); // Left Subtree height
        int rsh = height(node.right); // Right Subtree height
        return Math.max(lsh, rsh) + 1;
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

        // traversalIn(root);
    }
}
