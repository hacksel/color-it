package analyzer;

import colormap.ColorMap;

public interface ExplorerDecision {

    boolean shouldContinue(ColorMap colorMap, ColorMap.DIRECTIONS nextMove);

}
