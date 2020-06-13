package cdma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StationGenerator {

    public static List<String> tempArray;

    public static List<String> generate(int n) {
        tempArray = new ArrayList<String>();
        tempArray.add("1");         //tempArray[1] = [1];
        tempArray.add("1 1,1 -1");         //tempArray[2] = [[1, 1],[1, -1]]
        return h(n);
    }

    private static List<String> h(int order) {

        if (tempArray.size() >= order) {
            return new ArrayList<String>(Arrays.asList(tempArray.get(order - 1).split(",")));
        } else {
            List<String> positive = order > 4 ? h(order - 4) : h(2);    //var positive = order > 4 ? h(order - 4) : h(2);
            int antOrder = order > 4 ? order - 4 : 2;                               //var antOrder = order > 4 ? order - 4 : 2;


//            List<String> result = new ArrayList<String>();                          //var result = positive
//            for(String s : positive){
//                result.add(s);
//            }
            List<String> result = positive;

            for (int i = 0, a = 0; a < antOrder; i++, a++) {
                for (int j = 0, b = antOrder; b < order; j++, b++) {
                    result.set(a, result.get(a).concat(" ").concat(Arrays.asList(positive.get(i).split(" ")).get(j)));      //result[a].push(positive[i][j]);
                }
            }

            for (int i = 0, a = antOrder; a < order; i += 1, a += 1) {
                result.add("");                                                     //result.push([]);
                for (int j = 0, b = 0; b < antOrder; j += 1, b += 1) {
                    result.set(a, result.get(a).concat((j == 0) ? "" : " ").concat(Arrays.asList(positive.get(i).split(" ")).get(j)));      //result[a].push(positive[i][j]);
                }
            }

            for (int i = 0, a = antOrder; a < order; i += 1, a += 1) {
                for (int j = 0, b = antOrder; b < order; j += 1, b += 1) {
                    int index = Integer.parseInt(Arrays.asList(positive.get(i).split(" ")).get(j));
                    result.set(a, result.get(a).concat(" ").concat(-1 * index + ""));              //result[a].push(-1*positive[i][j]);
                }
            }
            //tempArray[order] = result;
            return result;
//            return null;
        }
    }
}

