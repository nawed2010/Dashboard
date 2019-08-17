/**
 * Created by nawed2010 on 07/04/17.
 */
public class Main1 {

    public static void main(String[] args){

        int[] input = {4,2,5,3,2,0,9,1};

        insertionSort(input);

        display(input);


    }

    public static void insertionSort(int[] input){

        for(int i = 1 ; i< input.length; i++){
            for(int j=i; j>0; j--){
                if(input[j-1]>input[j]){
                    int temp = input[j-1];
                    input[j-1] = input[j];
                    input[j] = temp;
                }
            }
        }
    }

    public static void bubbleSort(int[] input){

        for(int i=0; i<input.length; i++) {
            for (int j = 1; j < input.length - i; j++) {
                if (input[j - 1] > input[j]) {
                    int temp = input[j - 1];
                    input[j - 1] = input[j];
                    input[j] = temp;
                }
            }
        }
    }

    public static void display(int[] input){
        for(int a : input){
            System.out.print(a+" ");
        }
    }


}
