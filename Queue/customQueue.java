import java.io.*;
// import java.util.*;

public class customQueue {

    public static class DynamicQueue {
        int[] data;
        int front;
        int size;

        public DynamicQueue(int cap) {
            data = new int[cap];
            front = 0;
            size = 0;
        }

        int size() {
            return size;
        }

        void display() {
            for (int i = 0; i < size; i++) {
                int idx = (front + i) % data.length;
                System.out.print(data[idx] + " ");
            }
            System.out.println();
        }

        // change this code
        void add(int val) {
            if (size == data.length) {
                // Create new data array with twice the capacity than data array
                int[] newData = new int[2 * data.length]; // or 2 * size

                // copy current data into newData
                for (int i = 0; i < size; i++) {
                    int idx = (front + i) % data.length;
                    newData[i] = data[idx];
                }

                // data now points to newData
                data = newData;

                // front is now in the start
                front = 0;
            }

            // Add new data in queue
            int idx = (front + size) % data.length;
            data[idx] = val;
            size++;

        }

        int remove() {
            if (size == 0) {
                System.out.println("Queue underflow");
                return -1;
            } else {
                int val = data[front];

                front = (front + 1) % data.length;
                size--;

                return val;
            }
        }

        int peek() {
            if (size == 0) {
                System.out.println("Queue underflow");
                return -1;
            } else {
                int val = data[front];
                return val;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        DynamicQueue qu = new DynamicQueue(n);

        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("add")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                qu.add(val);
            } else if (str.startsWith("remove")) {
                int val = qu.remove();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("peek")) {
                int val = qu.peek();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("size")) {
                System.out.println(qu.size());
            } else if (str.startsWith("display")) {
                qu.display();
            }
            str = br.readLine();
        }
    }
}