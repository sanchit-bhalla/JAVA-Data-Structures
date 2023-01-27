package RecursionAndBacktracking;

import java.util.*;

public class StairPaths {

    public static void main(String[] args) throws Exception {
        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            ArrayList<String> res = getStairPaths(n);
            System.out.println(res);
        }

    }

    public static ArrayList<String> getStairPaths(int n) {
        if (n == 0) {
            ArrayList<String> ans = new ArrayList<>();
            ans.add("");
            return ans;
        }

        ArrayList<String> res_step1 = new ArrayList<>();
        ArrayList<String> res_step2 = new ArrayList<>();
        ArrayList<String> res_step3 = new ArrayList<>();
        ArrayList<String> allPaths = new ArrayList<>();

        // take 1 step
        if (n - 1 >= 0) {
            res_step1 = getStairPaths(n - 1);
        }
        // take 2 step
        if (n - 2 >= 0) {
            res_step2 = getStairPaths(n - 2);
        }
        // take 3 step
        if (n - 3 >= 0) {
            res_step3 = getStairPaths(n - 3);
        }

        for (String subPath : res_step1) {
            allPaths.add("1" + subPath);
        }
        for (String subPath : res_step2) {
            allPaths.add("2" + subPath);
        }
        for (String subPath : res_step3) {
            allPaths.add("3" + subPath);
        }

        return allPaths;
    }

}