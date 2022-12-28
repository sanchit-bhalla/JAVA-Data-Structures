package Patterns;
import java.util.*;

/*

*	*	*		*	*	*	
*	*				*	*	
*						*	
*	*				*	*	
*	*	*		*	*	*	

 */
public class p1 {
    static void printInput(int n, String s){
        for(int i=1;i<=n;i++){
            System.out.print(s+"\t");
        }
    }

    public static void main(String[] args) {
        try(Scanner scn = new Scanner(System.in)){
            int n = scn.nextInt();
            int st1 = n/2 + 1;
            int st2 = st1;
            int sp = 1;
            for(int i=1;i<=n; i++){
                printInput(st1,"*");
                printInput(sp, " ");
                printInput(st2,"*");
                
                if(i<= n/2){
                    st1--;
                    st2--;
                    sp+=2;
                }else{
                    st1++;
                    st2++;
                    sp-=2;
                }
                System.out.println("");
            }
        }

    }
}
