package GenericTree;

import java.util.*;

public class CustomGenericTree {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    public static void display(Node node) {
        String str = node.data + " -> ";
        for (Node child : node.children) {
            str += child.data + ", ";
        }
        str += ".";
        System.out.println(str);

        for (Node child : node.children) {
            display(child);
        }
    }

    public static int size(Node node) {
        if (node == null)
            return 0;
        int size = 1; // for current node
        for (Node child : node.children) {
            size += size(child);
        }
        return size;
    }

    public static int max(Node node) {
        int max = Integer.MIN_VALUE;
        for (Node child : node.children) {
            int max_of_child = max(child);
            max = Math.max(max_of_child, max);
        }
        max = Math.max(node.data, max);
        return max;
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1,
                -1 };

        Node root = null;
        Stack<Node> st = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                st.pop();
            } else {
                Node temp = new Node();
                temp.data = arr[i];

                if (st.size() == 0) {
                    root = temp;
                } else {
                    st.peek().children.add(temp);
                }

                st.push(temp);
            }
        }

        display(root);
    }
}