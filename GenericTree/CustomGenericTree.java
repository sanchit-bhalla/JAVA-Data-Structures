package GenericTree;

import java.io.*;
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

    public static class GenericTree implements Iterable<Integer> {
        Node root;

        GenericTree(Node root) {
            this.root = root;
        }

        public Iterator<Integer> iterator() {
            Iterator<Integer> obj = new GTPreorderIterator(root);
            return obj;
        }
    }

    public static class GTPreorderIterator implements Iterator<Integer> {
        Integer nval;
        Stack<NodeStatePair> st;

        public GTPreorderIterator(Node root) {
            st = new Stack<>();
            st.push(new NodeStatePair(root, -1));
            next(); // bcz initially nval is null; we want to set the 1st value which comes in
                    // preorder
        }

        public boolean hasNext() {
            if (nval == null) {
                return false;
            } else {
                return true;
            }
        }

        public Integer next() {
            Integer fr = nval; // fr = for return

            // moves nval forward so that next time it will give new node data in preorder,
            // if not possible sets it to null
            nval = null;
            while (st.size() > 0) {
                NodeStatePair top = st.peek();
                if (top.state == -1) {
                    nval = top.node.data;
                    top.state++;
                    break;
                } else if (top.state == top.node.children.size()) {
                    st.pop();
                } else {
                    NodeStatePair cp = new NodeStatePair(top.node.children.get(top.state), -1);
                    st.push(cp);

                    top.state++;
                }
            }

            return fr;
        }
    }

    private static class NodeStatePair {
        Node node;
        int state;

        NodeStatePair(Node node, int state) {
            this.node = node;
            this.state = state;
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

    // Approach 2 - Delimiter Approach a/--> Using 1 Queue and node with data -1
    // (ora/
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

    public static void removeLeaves(Node node) {
        // Remove leaves from first level
        // Run reverse loop bcz when we remove something from array, array also gets
        // smaller and elements get shifted to left and some elements left untracked
        for (int i = node.children.size() - 1; i >= 0; i--) {
            Node child = node.children.get(i);
            if (child.children.size() == 0) {
                // Child is a leaf node
                node.children.remove(i); // or node.children.remove(child)
            }
        }

        // Remove leaves of subtree
        // *** Removing leaves from subtree cannot be done before removing leaf of root
        // because it is possible that after removing laeves from subtree, some child of
        // root becomes leaf and when we removes leaves of root that node will also be
        // removed ***
        for (Node child : node.children) {
            removeLeaves(child);
        }
    }

    private static Node getTail(Node node) {
        while (node.children.size() == 1) {
            node = node.children.get(0);
        }
        return node;
    }

    // Approach 1 - T.C --> O(n^2)
    public static void linearize(Node node) {
        for (Node child : node.children) {
            linearize(child);
        }

        /*
         * Way 1
         * // Now subtree has been linearized. we now need to linearize whole tree
         * // i.e remove curr Child and add it child to the last node(tail) of prevChild
         * // Also run loop in reverse direction bcz we are removing from aray
         * for (int i = node.children.size() - 1; i >= 1; i--) {
         * Node prevChild = node.children.get(i - 1);
         * Node currChild = node.children.get(i);
         * 
         * // Remove curr child
         * node.children.remove(i);
         * 
         * // Add curr child to tail of prev child
         * while (prevChild.children.size() > 0) {
         * prevChild = prevChild.children.get(0);
         * }
         * prevChild.children.add(currChild);
         * }
         */

        // Way 2
        // Remove last child and add it to the tail of seconlast child.
        // Keep doing it until only 1 child left
        while (node.children.size() > 1) {
            Node lc = node.children.remove(node.children.size() - 1); // Remove and give last child
            Node slc = node.children.get(node.children.size() - 1); // second last child
            Node slc_tail = getTail(slc); // tail of second last child
            slc_tail.children.add(lc);
        }
    }

    // Approach 2: Effecient Approach --> Along with linearizinig subtree, it will
    // also return tail So we don't need to traverse subtree again to get tail
    public static Node linearize2(Node node) {
        // base case - If node is a leaf
        if (node.children.size() == 0)
            return node;

        // linearize subtree and return tail of last child
        Node lc_tail = linearize2(node.children.get(node.children.size() - 1));

        while (node.children.size() > 1) {
            Node lc = node.children.remove(node.children.size() - 1); // remove and last child
            Node slc = node.children.get(node.children.size() - 1); // second last child
            Node slc_tail = linearize2(slc); // linearize second last child and return its tail
            slc_tail.children.add(lc);
        }

        return lc_tail; // tail
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

    public static boolean find(Node node, int data) {
        // corner case
        if (node == null)
            return false;

        // base case
        if (node.data == data)
            return true;

        for (Node child : node.children) {
            if (find(child, data)) {
                return true;
            }
        }

        return false;
    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
        // base case
        if (node.data == data) {
            ArrayList<Integer> base_path = new ArrayList<>();
            base_path.add(data);
            return base_path;
        }

        for (Node child : node.children) {
            ArrayList<Integer> subPath = nodeToRootPath(child, data); // subPath === Path till child
            if (subPath.size() > 0) {
                // add current Node data and return
                subPath.add(node.data);
                return subPath;
            }
        }
        return new ArrayList<>(); // If we reach here, it means no path is present
    }

    // Lowest Common Ancestor
    public static int lca(Node node, int d1, int d2) {
        ArrayList<Integer> path1 = nodeToRootPath(node, d1); // d1 to root path
        ArrayList<Integer> path2 = nodeToRootPath(node, d2); // d2 to root path

        int i = path1.size() - 1;
        int j = path2.size() - 1;

        if (i == -1 || j == -1) {
            return -1; // d1 or d2 not present in tree
        }

        while (i >= 0 && j >= 0 && path1.get(i) == path2.get(j)) {
            i--;
            j--;
        }

        return path1.get(i + 1); // or path2.get(j+1)
    }

    public static int distanceBetweenNodes(Node node, int d1, int d2) {
        // distance b/w d1 and d2 doesn't include number of edges b/w lca and root
        ArrayList<Integer> p1 = nodeToRootPath(node, d1); // d1 to root path
        ArrayList<Integer> p2 = nodeToRootPath(node, d2); // d2 to root path

        int i = p1.size() - 1;
        int j = p2.size() - 1;

        while (i >= 0 && j >= 0 && p1.get(i) == p2.get(j)) {
            i--;
            j--;
        }

        i++;
        j++;

        // Now p1.get(i) || p2.get(j) is our lca
        // i now represent number of edges between lca and d1
        // j now represent number of edges between lca and d2
        return i + j;
    }

    // Don't compare data. Just compare shape
    public static boolean areSimilar(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            // corner case 1
            return true;
        } else if (n1 == null || n2 == null) {
            // corner case 2
            return false;
        } else {
            // check number of children
            if (n1.children.size() != n2.children.size())
                return false;

            // check corresponding subtree are similar or not
            for (int i = 0; i < n1.children.size(); i++) {
                if (!areSimilar(n1.children.get(i), n2.children.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    // Don't compare data. Just compare shape
    public static boolean areMirror(Node n1, Node n2) {
        if (n1 == null && n2 == null) {
            // corner case 1
            return true;
        } else if (n1 == null || n2 == null) {
            // corner case 2
            return false;
        } else {
            int num_child_n1 = n1.children.size();
            int num_child_n2 = n2.children.size();

            // check number of children equal or not
            if (num_child_n1 != num_child_n2)
                return false;

            // check corresponding mirrored subtree are mirror or not
            int i = 0;
            int j = num_child_n2 - 1;
            while (i < num_child_n1 && j >= 0) {
                if (!areMirror(n1.children.get(i), n2.children.get(j)))
                    return false;
                i++;
                j--;
            }
            return true;
        }
    }

    // if a vertical line is drawn through the centre of the tree then it should act
    // like a mirror.
    // tree is symmetric whenever the left half of the tree is the mirror image of
    // the right half of the tree
    // we can also check for whether our tree is the mirror image of itself or not.
    // If a tree is a mirror image of itself then it will surely be symmetric.
    // e.g Human face if symmetric but hand is not
    public static boolean IsSymmetric(Node node) {
        return areMirror(node, node);
    }

    static Node predecessor;
    static Node successor;
    static int state;

    public static void predecessorAndSuccessor(Node node, int data) {
        if (state == 0) {
            if (node.data == data) {
                state = 1;
            } else {
                predecessor = node;
            }
        } else if (state == 1) {
            successor = node;
            state = 2;
        }

        for (Node child : node.children) {
            predecessorAndSuccessor(child, data);
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

    // public static void main(String[] args) throws Exception {
    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // int n = Integer.parseInt(br.readLine());
    // int[] arr = new int[n];
    // String[] values = br.readLine().split(" ");
    // for (int i = 0; i < n; i++) {
    // arr[i] = Integer.parseInt(values[i]);
    // }

    // Node root = construct(arr);

    // diameter = 0;
    // calculateDiameterAndReturnHeight(root);
    // System.out.println(diameter);
    // }

    public static void main(String[] args) throws Exception {
        // int[] arr = { 10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110, -1, 120, -1,
        // -1, 90, -1, -1, 40, 100, -1, -1,
        // -1 };

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] values = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(values[i]);
        }

        Node root = construct(arr);
        GenericTree gt = new GenericTree(root);

        // syntactical sugar dependent on Iterable
        for (int val : gt) {
            System.out.println(val);
        }
        // It is equivalent to
        // Iterator<Integer> gti = gt.iterator();
        // while (gti.hasNext() == true) {
        // System.out.println(gti.next());
        // }

        display(root);

        predecessor = null;
        successor = null;
        state = 0;
        predecessorAndSuccessor(root, 110);
        if (predecessor == null) {
            System.out.println("Predecessor = Not found");
        } else {
            System.out.println("Predecessor = " + predecessor.data);
        }

        if (successor == null) {
            System.out.println("Successor = Not found");
        } else {
            System.out.println("Successor = " + successor.data);
        }
    }
}