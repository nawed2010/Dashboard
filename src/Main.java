import java.util.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by nawed2010 on 06/02/17.
 */
public class Main {

    public static void main(String[] args) {
       LinkedListNew list = new LinkedListNew("END",1,2,3,4,5);

        Scanner scan = new Scanner(System.in);

        do{
            System.out.println("\n********* Singly Linked List Operations ***********\n");
            System.out.println("1. insert at beginning");
            System.out.println("2. insert at end");
            System.out.println("3. insert at position");
            System.out.println("4. delete at position");
            System.out.println("5. check empty");
            System.out.println("6. get size");
            System.out.println("7. display");
            list.printList();
            System.out.println(" ********************************************************");
            int choice = scan.nextInt();
            switch (choice){
                case 1 :
                    System.out.println("Enter integer element to insert at Head");
                    list.addToStart(scan.nextInt());
                    break;
                case 2 :
                    System.out.println("Enter integer element to insert at Tail");
                    list.addToEnd(scan.nextInt());
                    break;
                case 3 :
                    System.out.println("Enter integer element to insert at specific position -- (value , position)");
                    list.addToPosition(scan.nextInt(), scan.nextInt() );
                    break;
                case 4 :
                    System.out.println("Delete  element from position");
                    list.deleteNode( scan.nextInt());
                    break;
                case 5 :
                    if(list.isEmpty()){
                        System.out.println("List is empty.");
                    }else{
                        System.out.println("List is not empty, with size: "+list.length);
                    }
                    break;
                case 6 :
                    System.out.println("Size of list: "+list.size());
                    break;
                case 7:
                    list.printList();
                    break;
            }

        }while(true);

        /*Set<Integer> set = new HashSet<Integer>();

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(3);

        System.out.println(set);

        for(Integer temp : set){
            System.out.print(temp+" ");
        }

        System.out.println("\n====ITR====");

        Iterator<Integer> itr = set.iterator();

        while(itr.hasNext()){
            System.out.print(itr.next()+" ");
        }

        System.out.println("\n====MAP====");

        Map<String, Integer> map = new HashMap<>();

        map.put("first",1);
        map.put("second",2);
        map.put("third",3);
        map.put("fourth",4);
        System.out.println(map);

        System.out.println("\n====MAP ITR====");

        Set<String> keySet = map.keySet();
        Iterator<String> itrMap = keySet.iterator();
        int i = 1;
        while(itrMap.hasNext()){
            System.out.println(i+" : "+itrMap.next()+" : "+itrMap.next());
            i++;
        }

        System.out.println("\n====Array List====");

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        System.out.println(list);
        */

         /*
        int array[] = { 5, 3, 6, 8, 2, 9} ;
        //Bubble sort
        int n = array.length;
        int temp =0;
        for( int i = 0; i < n; i++){
            for( int j = 1; j < n-i ; j++){
                if(array[j-1] > array[j]){
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
        //Insertion Sort
        /*for (int i = 1; i < input.length; i++) {
            for(int j = i ; j > 0 ; j--){
                if(input[j] < input[j-1]){
                    temp = input[j];
                    input[j] = input[j-1];
                    input[j-1] = temp;
                }
            }
        }

        for(int i : array){
            System.out.print(i+" ");
        }

        int array1[] = { 5, 3, 6, 8, 2, 9} ;

        List<Integer> list = new LinkedList<>();

        for(int i = 0; i< array1.length ; i++){

            if(i==0){
                list.add(array1[0]);
                continue;
            }else if(array1[i]>=list.get(list.size()-1)){
                list.add(array1[i]);
            }else{
                temp = list.get(list.size()-1);
                list.remove(list.size()-1);
                list.add(array1[i]);
                list.add(temp);
            }
        }
        System.out.println(list);*/
    }
}
