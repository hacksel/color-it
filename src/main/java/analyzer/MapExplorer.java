package analyzer;

import analyzer.explorer.SameColorExplorer;
import analyzer.explorer.SwitchColorExplorer;
import colormap.ColorMap;
import colormap.ColorMap.DIRECTIONS;

import java.util.*;

import static colormap.ColorMap.DIRECTIONS.*;
import static java.util.Arrays.asList;
import static utils.ColorMapUtils.copyColorMap;

public class MapExplorer {

    public static void main(String[] args) {
        // given
        List<List<Integer>> currentMap = asList(
                asList(1, 1, 1, 1, 0, 2),
                asList(1, 1, 5, 4, 3, 2),
                asList(0, 0, 7, 7, 7, 0),
                asList(2, 2, 9, 9, 9, 1),
                asList(2, 2, 2, 2, 1, 1),
                asList(2, 2, 2, 2, 1, 1)
        );

        play(currentMap, 10);
    }

    public static void play(List<List<Integer>> currentMap, Integer nbColors) {
        Integer mapSize = getMapSize(currentMap);
        Integer currentColorArea = 1;
        Integer tour = 1;

        List<Integer> partyChoices = new ArrayList<>();

        while (mapSize > currentColorArea) {
            Integer nextColor = computeBestNextColor(currentMap, nbColors);
            partyChoices.add(nextColor);
            ColorMap nextColorMap = switchMapColorTo(currentMap, nextColor);
            currentColorArea = nextColorMap.getInitialColorArea();
            currentMap = nextColorMap.getColorMap();
            //nextColorMap.print();
            tour++;
        }

        partyChoices.stream().forEach((choice) -> System.out.print(choice + " "));
    }

    private static Integer getMapSize(List<List<Integer>> colorMap) {
        return (colorMap.size() * colorMap.get(0).size());
    }

    public static Integer computeBestNextColor(List<List<Integer>> colorMapInput, Integer nbColors) {
        ColorMap colorMap = ColorMap.get(colorMapInput).withPos(0, 0).build();
        Integer initialColor = colorMap.getInitialColor();
        Integer bestNextColor = 0;
        Integer bestColorArea = 0;

        for (Integer currentNextColor = 0; currentNextColor <= nbColors; currentNextColor++) {
            if (currentNextColor == initialColor) {
                continue;
            }

            colorMap = switchMapColorTo(copyColorMap(colorMapInput), currentNextColor);
            if (colorMap.getInitialColorArea() > bestColorArea) {
                bestNextColor = currentNextColor;
                bestColorArea = colorMap.getInitialColorArea();
            }
        }

        return bestNextColor;
    }

    public static Integer computeCurrentColorArea(List<List<Integer>> colorMapInput) {
        Integer area = 1;
        ColorMap colorMap = ColorMap.get(colorMapInput).withPos(0, 0).build();
        SameColorExplorer sameColorExplorer = SameColorExplorer
                .compareWith(colorMap.getInitialColor());

        return nextMove(area, colorMap, INIT, sameColorExplorer);
    }

    public static ColorMap switchMapColorTo(List<List<Integer>> currentMap, Integer switchColor) {
        ColorMap colorMap = ColorMap.get(currentMap).withPos(0, 0).build();
        SwitchColorExplorer switchColorExplorer = SwitchColorExplorer
                .switchBetween(colorMap.getInitialColor(), switchColor);

        colorMap.setInitialColorArea(nextMove(1, colorMap, INIT, switchColorExplorer));

        return colorMap;
    }

    private static Integer nextMove(Integer area, ColorMap colorMap, DIRECTIONS origin, ExplorerDecision explorerDecision) {
        ColorMap subColorMap = ColorMap.get(colorMap.getColorMap())
                .withPos(colorMap.getPosX(), colorMap.getPosY())
                .withExploredMap(colorMap.getExploredMap())
                .build();

        subColorMap.moveFrom(origin);
        area += explorePaths(origin.getNextOptions(), subColorMap, explorerDecision);
        return area;
    }

    private static Integer explorePaths(List<DIRECTIONS> pathList, ColorMap subColorMap, ExplorerDecision explorerDecision) {
        return pathList.stream().map(directionToCheck -> {
            if (explorerDecision.shouldContinue(subColorMap, directionToCheck)) {
                return nextMove(1, subColorMap, directionToCheck.getOposite(), explorerDecision);
            }
            return 0;
        }).reduce(Integer::sum).get();
    }

}
