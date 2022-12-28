import java.io.*;
import java.util.*;

public class Celebrity {

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            for (int k = 0; k < n; k++) {
                arr[j][k] = line.charAt(k) - '0';
            }
        }

        findCelebrity(arr);

    }

    public static void findCelebrity(int[][] arr) {
        // if a celebrity is there print it's index (not position), if there is not then
        // print "none"

        // Approach --> 1st push all index into the stack. Pop 2 indices at a time and
        // eliminate 1 index which can't be celebrity. Push the other into the stack. At
        // last only 1 index remain in the stack which can be the possible celebrity.
        // Check that index to make sure
        // Time Complexity => O(n)

        Stack<Integer> cis = new Stack<>(); // cis => celebrity indexes stack
        for (int i = 0; i < arr.length; i++) {
            cis.push(i);
        }

        while (cis.size() > 1) {
            int ci1 = cis.pop();
            int ci2 = cis.pop();

            if (arr[ci1][ci2] == 1) {
                // ci1 knows ci2 => ci1 can't be the celebrity. So ignore it and push ci2 back
                // into the stack
                cis.push(ci2);
            } else {
                // ci1 doesn't know ci2 => ci2 can't be the celebrity. So ignore it and push ci1
                // back into the stack
                cis.push(ci1);
            }
        }

        int pci = cis.pop(); // pis => possible celebrity index

        for (int i = 0; i < arr.length; i++) {
            if (i != pci) {
                if (arr[i][pci] == 0 || arr[pci][i] == 1) {
                    System.out.println("none");
                    return;
                }
            } else { // if i == pci
                if (arr[pci][pci] == 1) {
                    System.out.println("none");
                    return;
                }
            }
        }

        System.out.println(pci);
    }

}