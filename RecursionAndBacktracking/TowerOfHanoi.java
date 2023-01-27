package RecursionAndBacktracking;

import java.util.*;

public class TowerOfHanoi {
    public static void main(String[] args) throws Exception {
        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int t1id = scn.nextInt(); // id of tower 1
            int t2id = scn.nextInt(); // Id of tower 2
            int t3id = scn.nextInt(); // Id of tower 3
            toh(n, t1id, t2id, t3id);
        }
    }

    // toh(number of disks, src disk Id, dest disk Id, helper disk Id)
    public static void toh(int n, int t1id, int t2id, int t3id) {
        if (n == 0)
            return;
        toh(n - 1, t1id, t3id, t2id); // will print instructions to move n-1 disks from t1 to t3 using t2
        System.out.println(n + "[" + t1id + " -> " + t2id + "]");
        toh(n - 1, t3id, t2id, t1id);
    }
}
