package GenericTree;

import java.io.*;
import java.util.*;

public class IterativePreAndPostOrder {
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

  static class Pair {
    Node node;
    int state;

    Pair(Node node, int state) {
      this.node = node;
      this.state = state;
    }
  }

  /*
   * Approach:
   * state == -1 ---> PreOrder, state++
   * 0 <= state < no. of children ---> InOrder, state++, push to statck
   * state == no. of children ---> PostOrder, pop from stack
   * Other Approaches from GFG
   */
  public static void iterative_pre_and_post_order(Node node) {
    Stack<Pair> st = new Stack<>();
    st.push(new Pair(node, -1));

    String pre = "";
    String post = "";

    while (st.size() > 0) {
      Pair top = st.peek();
      if (top.state == -1) {
        pre += top.node.data + " ";
        top.state = 0;
      } else if (top.state == top.node.children.size()) {
        post += top.node.data + " ";
        st.pop();
      } else {
        Pair child_pair = new Pair(top.node.children.get(top.state), -1);
        st.push(child_pair);

        top.state++; // Here top still referes to the Pair below child_pair in stack
      }
    }

    System.out.println(pre);
    System.out.println(post);
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
    iterative_pre_and_post_order(root);
  }

}