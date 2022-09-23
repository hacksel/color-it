package utils;

import java.util.ArrayList;
import java.util.List;

public class ColorMapUtils {

    public static List<List<Integer>> copyColorMap(List<List<Integer>> colorMapInput) {
        List<List<Integer>> internalCopy = new ArrayList<>();
        for (List<Integer> mapLine: colorMapInput) {
            List<Integer> yCopy = new ArrayList<>();
            for (Integer integer: mapLine) {
                yCopy.add(integer);
            }
            internalCopy.add(yCopy);
        }
        return internalCopy;
    }


}
