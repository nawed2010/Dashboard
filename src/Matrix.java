import java.util.ArrayList;
import java.util.List;

/**
 * Created by nawed2010 on 18/06/17.
 */
public class Matrix {

    public static void main( String args[]){

        System.out.println("*** Welcome to Matrix Program ***********\n");


        int a[][] = {{1,2,3,4,5},{18,19,20,21,6},{17,28,29,22,7},{16,27,30,23,8},{15,26,25,24,9},{14,13,12,11,10}};
        System.out.println("No. of rows: "+a.length+" No. of Columns: "+a[0].length);
        printMatrix(a);

        spiralPrint(a);



        List list = new ArrayList();

        int result[] = new int[list.size()];
        for(int i = 0; i<list.size();i++){
            result[i] = Integer.parseInt(list.get(i).toString());
        }

    }

    public static void printMatrix(int a[][]){
        for(int i=0; i<a.length; i++){
            for(int j=0;j<a[i].length;j++){
                System.out.print(a[i][j]+"\t");
            }
            System.out.println("");
        }
    }

    public static void spiralPrint(int a[][]){
        int c1=0, c2=a[0].length-1, r1=0, r2=a.length-1;
        int dir = 0;

        System.out.println("Spiral Print: ");
        while(r1<=r2 && c1<=c2){

            if(dir == 0){
                for(int i= c1; i<=c2; i++){
                    System.out.print(a[r1][i]+" ");
                }
                r1++;

            }else if(dir == 1){
                for(int i=r1; i<=r2; i++){
                    System.out.print(a[i][c2]+" ");
                }
                c2--;
            }else if(dir == 2){
                for(int i=c2; i>=c1; i--){
                    System.out.print(a[r2][i]+" ");
                }
                r2--;
            }else if(dir == 3){
                for(int i=r2; i>=r1;i--){
                    System.out.print(a[i][c1]+" ");
                }
                c1++;
            }

            dir = ++dir%4;
        }

    }
}
