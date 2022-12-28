import java.util.*;

public class rotateNumber{
    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt(); // e.g n = 562984
            int k = scn.nextInt(); // e.g k = 2
            int ans=0;

            int nod = 0; // number of digits
            int temp = n;
            while(temp > 0){
                temp /= 10;
                nod++;
            }

            k = k % nod;
            if(k < 0) k+=nod;


            int mf = 1; // Multiplying factor
            int df = 1; // dividing factor
            for(int i=1; i<=nod; i++){  // or using Pow function --> (int)Math.pow(10,k)
                if(i<=k) df *= 10;
                else mf *= 10;
            }
            // When come out of loop, mf = 1000, df=100 ->  for above example
            
            int rem = n%df;
            ans = rem*mf + (n/df);
            System.out.print(ans);
        }
        
    }

}