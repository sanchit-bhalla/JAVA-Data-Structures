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

    public static int height(Node node) {
        int ht = -1; // initially

        for (Node child : node.children) {
            int child_height = height(child);
            ht = Math.max(child_height, ht);
        }

        ht += 1; // for edge between root and subtree
        return ht;
    }

    public static void traversals(Node node) {
        // Euler's left === on the way deep in the recursion === Node's pre area
        System.out.println("Node Pre " + node.data);

        for (Node child : node.children) {
            // edge pre
            System.out.println("Edge Pre " + node.data + "--" + child.data);

            traversals(child);

            // edge post
            System.out.println("Edge Post " + node.data + "--" + child.data);
        }

        // Euler's right === on the way out in the recursion === Node's post area
        System.out.println("Node Post " + node.data);
    }

    public static void levelOrder(Node node) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(node);

        // Order ==> Remove from queue --> print --> add in queue
        while (q.size() > 0) {
            Node front = q.remove();
            System.out.print(front.data + " ");
            for (Node child : front.children) {
                q.add(child);
            }
        }
        System.out.print(".");
    }

    public static void levelOrderLinewise(Node node) {
        Queue<Node> mq = new ArrayDeque<>(); // main queue
        Queue<Node> cq = new ArrayDeque<>(); // child Queue
        mq.add(node);

        while (mq.size() > 0) {
            Node front = mq.remove();
            System.out.print(front.data + " ");

            for (Node child : front.children) {
                cq.add(child);
            }

            if (mq.size() == 0) {
                // level completed. So now we print another level in another line
                System.out.println();
                mq = cq;
                cq = new ArrayDeque<>();
            }
        }
    }

    public static void levelOrderLinewiseZZ(Node node) {
        Stack<Node> ms = new Stack<>(); // main stack
        Stack<Node> cs = new Stack<>(); // cs = child stack
        int level = 1;
        ms.push(node);

        while (ms.size() > 0) {
            Node top = ms.pop();
            System.out.print(top.data + " ");

            if (level % 2 == 1) {
                // Add child from right to left
                for (int i = 0; i < top.children.size(); i++) {
                    Node child = top.children.get(i);
                    cs.add(child);
                }
            } else {
                // Add child from left to right
                for (int i = top.children.size() - 1; i >= 0; i--) {
                    Node child = top.children.get(i);
                    cs.add(child);
                }
            }

            if (ms.size() == 0) {
                // One level completed. Next level will start from next row
                System.out.println();
                ms = cs;
                cs = new Stack<>();
                level++;
            }
        }
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