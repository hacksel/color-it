package analyzer.explorer;

import analyzer.ExplorerDecision;
import colormap.ColorMap;

import static colormap.ColorMap.*;

public class SameColorExplorer implements ExplorerDecision {
    private Integer comparedTo;

    private SameColorExplorer(Integer comparedTo) {
        this.comparedTo = comparedTo;
    }

    @Override
    public boolean shouldContinue(ColorMap colorMap, DIRECTIONS nextMove) {
        return colorMap.getNextColor(nextMove) == this.comparedTo;
    }

    public static SameColorExplorer compareWith(Integer nextColor) {
        return new SameColorExplorer(nextColor);
    }
}
