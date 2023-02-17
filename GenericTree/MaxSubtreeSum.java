package GenericTree;

import java.io.*;
import java.util.*;

public class MaxSubtreeSum {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    static Node maxSubtreeSumNode;
    static int maxSubtreeSum;

    // Return Subtree sum and find node having maximum subtree sum
    public static int returnSSAndCalMSS(Node node) {
        // corner case
        if (node == null)
            return 0;

        int ans = node.data;
        for (Node child : node.children) {
            ans += returnSSAndCalMSS(child);
        }

        if (ans > maxSubtreeSum) {
            maxSubtreeSum = ans;
            maxSubtreeSumNode = node;
        }

        return ans;
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

    public static Node construct(int[] arr) {
        Node root = null;

        Stack<Node> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                st.pop();
            } else {
                Node t = new Node();
                t.data = arr[i];

                if (st.size() > 0) {
                    st.peek().children.add(t);
                } else {
                    root = t;
                }

                st.push(t);
            }
        }

        return root;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }

        Node root = construct(arr);

        maxSubtreeSumNode = null;
        maxSubtreeSum = Integer.MIN_VALUE;
        returnSSAndCalMSS(root);
        System.out.println(maxSubtreeSumNode.data + "@" + maxSubtreeSum);
    }

}