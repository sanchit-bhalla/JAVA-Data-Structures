import java.util.*;

public class gcdAndLcm {

    public static int gcd(int a, int b){
        int  divisor = a;
        int dividend = b;
        int rem = dividend % divisor;
        while(rem != 0){
            dividend = divisor;
            divisor = rem;
            rem = dividend % divisor;
        }
        return divisor;
    }

    public static int lcm(int a, int b){
        int ans = b;
        for(int i=b; ; i+=b){
            if(i%a == 0 ){
                ans = i;
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args){
        try (Scanner scn = new Scanner(System.in)) {
            int n1 = scn.nextInt();
            int n2 = scn.nextInt();

            int gcd = gcd(n1,n2);

            // Way 1
            // int lcm = lcm(n1,n2);

            // Way 2 --> lcm * gcd = n1 * n2;
            int lcm = (n1*n2)/gcd;
            System.out.println(gcd);
            System.out.println(lcm);
        }
    }
}
