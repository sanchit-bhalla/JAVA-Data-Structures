package Patterns;
import java.util.*;

/* 
    Pattern To Print
    1
    1   1
    1   2   1
    1   3   3   1
    1   4   6   4   1
    1   5   10  10  5   1
 */

public class pattern13 {
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)){
            int n = scn.nextInt();
            for(int i=0;i<n; i++){
                int icj = 1;
                for(int j=0; j<=i;j++){
                    System.out.print(icj + "\t");
                    int icjp1 = (icj*(i-j)) / (j+1); // calculating (iCj+1 = iCj * (i-j)) / (j+1)
                    icj = icjp1;
                }
                System.out.println();
            }
        }
    }
}
