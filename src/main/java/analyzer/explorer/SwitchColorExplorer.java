package analyzer.explorer;

import analyzer.ExplorerDecision;
import colormap.ColorMap;
import colormap.ColorMap.DIRECTIONS;

public class SwitchColorExplorer implements ExplorerDecision {

    private Integer initialColor;
    private Integer switchColor;

    private SwitchColorExplorer(Integer initialColor, Integer switchColor) {
        this.initialColor = initialColor;
        this.switchColor = switchColor;
    }

    public static SwitchColorExplorer switchBetween(Integer initialColor, Integer switchColor) {
        return new SwitchColorExplorer(initialColor, switchColor);
    }

    @Override
    public boolean shouldContinue(ColorMap colorMap, DIRECTIONS nextMove) {
        Integer currentPosColor = colorMap.getPosColor();
        if (currentPosColor == this.initialColor) {
            colorMap.getColorMap()
                    .get(colorMap.getPosX())
                    .set(colorMap.getPosY(), this.switchColor);
        }

        Integer nextColor = colorMap.getNextColor(nextMove);
        return currentPosColor == this.initialColor
                && (this.initialColor == nextColor || this.switchColor == nextColor);
    }

}
