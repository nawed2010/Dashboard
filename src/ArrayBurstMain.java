import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nawed2010 on 25/11/19.
 */
public class ArrayBurstMain {

    public static void main(String[] args) {

        //List<String> input = Arrays.asList("a","b","c","c","c","d", "e", "e");
        //List<String> input = Arrays.asList("a","b","c","c","c","d", "e", "e","e");
        List<String> input = Arrays.asList("a","b","c","d","e","e", "e", "e","d","d","c","b","f","f","f","f");
        //List<String> input = Arrays.asList("a","b","c","d","e","e", "e", "e","d","d","c","b","f","g","f");

        for(String a : input) {
            System.out.print(a +" ");
        }

        System.out.println("=====");

        List<String> list = getShrunkArray(input, 3, 1);

        for(String a : list) {
            System.out.print(a +" ");
        }

}

public static List<String> getShrunkArray(List<String> inputArray, int burstLength,int deleteCount) {

    if(deleteCount < 1) {
        return inputArray;
    }

    int count = 0;
    deleteCount = 0;
    String prv = null;

    List<String> temp = new ArrayList<>();

    for (int i = 0; i < inputArray.size(); i++) {

        boolean addToTemp = true;

        if (i == 0) {
            prv = inputArray.get(0);
            count = 1;
            temp.add(inputArray.get(0));
            continue;
        }

        if (prv == inputArray.get(i)) {
            count++;
        }

        if ((prv != inputArray.get(i) && count >= burstLength) ) {
            while (count > 0) {
                temp.remove(temp.size() - 1);
                deleteCount++;
                count--;
            }
            count = 1;
            prv = inputArray.get(i);
        } else if((i == inputArray.size()-1 && count >= burstLength)) {
            while (count > 1) {
                temp.remove(temp.size() - 1);
                deleteCount++;
                count--;
            }
            count = 1;
            prv = inputArray.get(i);
            addToTemp = false;
        } else {
            prv = inputArray.get(i);
            //count = 1;
        }

        if(addToTemp)
            temp.add(inputArray.get(i));

    }

    return getShrunkArray(temp, burstLength, deleteCount);
}
}
