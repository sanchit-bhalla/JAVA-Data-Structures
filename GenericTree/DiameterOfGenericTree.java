package GenericTree;

import java.io.*;
import java.util.*;

public class DiameterOfGenericTree {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();
    }

    static int diameter;

    public static int calculateDiameterAndReturnHeight(Node node) {
        if (node == null || node.children.size() == 0) {
            return 0; // height (max number of edges)
        }

        int max_child_height = 0;
        int second_max_child_height = 0;

        for (Node child : node.children) {
            int childHeight = calculateDiameterAndReturnHeight(child);
            if (childHeight > max_child_height) {
                second_max_child_height = max_child_height;
                max_child_height = childHeight;
            } else if (childHeight > second_max_child_height) {
                second_max_child_height = childHeight;
            }

            if (max_child_height + second_max_child_height + 2 > diameter) {
                diameter = max_child_height + second_max_child_height + 2;
            }
        }

        return max_child_height + 1; // +1 bcz height of node is 1 more than max height of its child
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

        diameter = 0;
        calculateDiameterAndReturnHeight(root);
        System.out.println(diameter);
    }

}