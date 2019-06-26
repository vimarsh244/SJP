import java.io.*;
public class q14
{   
    public static void main()throws IOException{
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        System.out.println("\fEnter Number:");
        int n=Integer.parseInt(br.readLine());
        int m=n;
        int d_o=0;
        while(m>0){
            d_o++;
            m/=10;
        }
        if(n*n%Math.pow(10,d_o)==n)
            System.out.println("Automorphic Number!");
        else    
            System.out.println("Non-Automorphic Number!");
    }
}
