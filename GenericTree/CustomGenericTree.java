package GenericTree;

import java.util.*;

public class CustomGenericTree {
    private static class Node {
        int data;
        ArrayList<Node> children = new ArrayList<>();

        // bcz when we give parameterized constructor, default constructor will also be
        // removed, so we need to give explicity
        Node() {
        }

        Node(int data) {
            this.data = data;
        }
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

    // Approach 1: Using 2 queues
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

    // Approach 2 - Delimiter Approach --> Using 1 Queue and node with data -1 (or
    // null) is used as delimiter to check whether level is completed or not
    public static void levelOrderLinewise2(Node node) {
        Queue<Node> mq = new ArrayDeque<>(); // main queue
        mq.add(node);
        mq.add(new Node(-1)); // -1 will tell us that one level has been completed

        while (mq.size() > 0) {
            Node front = mq.remove();

            if (front.data == -1) {
                // level has been completed
                if (mq.size() > 0) {
                    // add node with data -1 to indicate level completed for children
                    mq.add(new Node(-1));
                    System.out.println();
                }
            } else {
                System.out.print(front.data + " ");

                for (Node child : front.children) {
                    mq.add(child);
                }
            }
        }
    }

    // Approach 3 --> Count Approach --> Count number of elements in a particular
    // level and then run loop for that number of times and add their children in
    // queue
    public static void levelOrderLinewise3(Node node) {
        Queue<Node> mq = new ArrayDeque<>(); // main queue
        mq.add(node);
        while (mq.size() > 0) {
            int count = mq.size(); // count of node in current level
            for (int i = 0; i < count; i++) {
                Node front = mq.remove();
                System.out.print(front.data + " ");

                for (Node child : front.children) {
                    mq.add(child);
                }
            }
            System.out.println();
        }
    }

    // Approach 4 - Using Pair class of Node and level. Whenever level changes,
    // print in new line
    public static void levelOrderLinewise4(Node node) {
        Queue<Pair> mq = new ArrayDeque<>();
        mq.add(new Pair(node, 1));
        int curr_level = 1;

        while (mq.size() > 0) {
            Pair p = mq.remove();
            if (p.level != curr_level) {
                System.out.println();
                curr_level = p.level;
            }

            System.out.print(p.node.data + " ");
            for (Node child : p.node.children) {
                mq.add(new Pair(child, p.level + 1));
            }
        }
    }

    private static class Pair {
        Node node;
        int level;

        Pair(Node node, int level) {
            this.node = node;
            this.level = level;
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

    public static void mirror(Node node) {
        // corner case
        if (node == null)
            return;

        // Mirror sub trees
        for (Node child : node.children) {
            mirror(child);
        }

        // Now reverse node.children Array to mirror the whole tree
        for (int i = 0, j = node.children.size() - 1; i < j; i++, j--) {
            Node temp = node.children.get(i);
            node.children.set(i, node.children.get(j));
            node.children.set(j, temp);
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