package Patterns;
import java.util.*;

/* Pattern To print (for n=5)
            *
        *       *
    *               *
        *       *
            *
 */
public class pattern10 {
    // Another Solution from video no. 54
    public static void main(String[] args){
        try(Scanner scn = new Scanner(System.in)){
            int n= scn.nextInt();
            int s1,s2;
            s1 = s2 = (n/2+1);
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(j==s1 || j==s2){
                        System.out.print("*\t");
                    }else{
                        System.out.print("\t");
                    }
                }
                if(i<= n/2){
                    s1--;
                    s2++;
                } else{
                    s1++;
                    s2--;
                }
                System.out.println();
            }
        }
    }
}
