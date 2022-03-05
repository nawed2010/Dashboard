import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by nawed2010 on 16/10/19.
 */
public class ExperimentalMain {


    enum Resource {
        CUTU, HO, MUMU, PAA
    };

    public static void main(String[] args) throws InterruptedException {
        int[][] arr = {{5,7,8,9}, {6,2,6,2},{8,9,1,4},{1,4,5,4}};
        List<Integer> list1 = IntStream.of(arr[0]).boxed().collect(Collectors.toList());
        List<Integer> list2 = IntStream.of(arr[1]).boxed().collect(Collectors.toList());
        List<Integer> list3 = IntStream.of(arr[2]).boxed().collect(Collectors.toList());
        List<Integer> list4 = IntStream.of(arr[3]).boxed().collect(Collectors.toList());


        List<List<Integer>> list = new ArrayList<>();

        list.add(list1);
        list.add(list2);
        list.add(list3);
        list.add(list4);

    }

    public static void main1(String[] args) throws InterruptedException {

        int[][] arr = {{5,7,8,9}, {6,2,6,2},{8,9,1,4},{1,4,5,4}};

        Map<Integer, String> map = new HashMap<>();

        //System.out.println(Resource.values()[1]);

        for(int i = 0; i < arr.length; i ++) {
            for(int j = 0; j < arr.length; j++) {
                assign(arr, i , j, map, "Resource: "+Resource.values()[i]+" Task: "+j+" Time: "+arr[i][j], arr[i][j], 0, 0);
            }
        }

        System.out.println("=============FINAL OUTPUT : COUNT : "+Collections.min(map.keySet())+"===========");

        System.out.println(map.get(Collections.min(map.keySet())));


    }

    private static void assign(int[][] arr, int i, int j, Map<Integer, String> map, String str,
                               int count, int m, int n) {
        if(m >= arr.length || n >= arr.length) {
            System.out.println("==========Count: "+count+" ============");
            System.out.println(str);
            map.put(count, str);
            return;
        }


        for(int x = 0; x < arr.length; x++) {
            for(int y = 0; y < arr.length; y++) {
                if(i != x && j != y) {

                }
            }
        }


        if(i != m && j != n) {

            str = str + "\nResource: "+Resource.values()[m]+" Task: "+n+" Time: "+arr[m][n];

            count = count + arr[m][n];

            assign(arr, i, j, map, str, count, ++m, ++n);

        } else {
            assign(arr, i, j, map, str, count, ++m, ++n);
        }

    }


}
