import java.util.*;
public class primeFactorization {
    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)){
            int n = scn.nextInt();
            if(n<=1) return;
            
            for(int div=2; div*div<=n; div++){
                // Keeps printing until div is a factor of n
                while(n%div == 0){
                    System.out.print(div + " ");
                    n /= div;
                }
            }
            if(n != 1){
                System.out.print(n);
            }
        }
    }
}
