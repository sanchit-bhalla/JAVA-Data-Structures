import java.util.*;
public class BenjaminBulbs {

    static boolean isPerfectSquare(int n){
        int start=1, end=n;
        while(start <= end){
            int mid = start+ (end - start)/2;
            if(mid*mid == n) return true;
            if(mid*mid > n){
                end = mid-1;
            }else{
                start = mid + 1;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            for(int i=1;i*i<=n;i++){
                System.out.println(i*i);
            }
        }
    }
}
