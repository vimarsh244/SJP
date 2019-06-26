import java.io.*;
public class q12
{   
    public static void main()throws IOException{
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        System.out.println("\fEnter Range:");
        int n=Integer.parseInt(br.readLine());
        for(int i=n;i>=1;i--){
            for(int j=i;j<=n;){
                System.out.print((i!=j)?j:n);
                if(j!=i)
                    j++;
            }
            System.out.println();
        }
    }
}
