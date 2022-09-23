package colormap;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;

public class ColorMap {

    public enum DIRECTIONS {
        INIT,
        RIGHT,
        BOTTOM,
        LEFT,
        TOP;

        public DIRECTIONS getOposite() {
            return switch(this) {
                case LEFT -> RIGHT;
                case RIGHT -> LEFT;
                case BOTTOM -> TOP;
                case TOP -> BOTTOM;
                case INIT -> null;
            };
        }

        public List<DIRECTIONS> getNextOptions() {
            return switch (this) {
                case LEFT -> asList(RIGHT, BOTTOM, TOP);
                case RIGHT -> asList(TOP, LEFT, BOTTOM);
                case BOTTOM -> asList(TOP, LEFT, RIGHT);
                case TOP -> asList(BOTTOM, LEFT,  RIGHT);
                case INIT -> asList(BOTTOM, RIGHT);
            };
        }
    }

    private final List<List<Integer>> colorMap;
    private final Map<Integer, Map<Integer, Boolean>> exploredMap;

    private Integer posX;
    private Integer posY;
    private Integer posColor;
    private Integer initialColor;
    private Integer initialColorArea;

    private ColorMap(List<List<Integer>> colorMap, Integer posX, Integer posY, Map<Integer, Map<Integer, Boolean>> exploredMap) {
        this.colorMap = colorMap;
        this.posX = posX;
        this.posY = posY;
        this.exploredMap = exploredMap == null
                ? new HashMap<>()
                : exploredMap;
        this.initialColor = setPosColor();
    }

    public void setInitialColorArea(Integer initialColorArea) {
        this.initialColorArea = initialColorArea;
    }

    public Integer getInitialColorArea() {
        return initialColorArea;
    }

    public Integer getNextColor(DIRECTIONS direction) {
        return switch (direction) {
            case LEFT -> this.getNextLeftColor();
            case RIGHT -> this.getNextRightColor();
            case TOP -> this.getNextTopColor();
            case BOTTOM -> this.getNextBottomColor();
            case INIT -> null;
        };
    }

    public Map<Integer, Map<Integer, Boolean>> getExploredMap() {
        return exploredMap;
    }

    public List<List<Integer>> getColorMap() {
        return colorMap;
    }

    public Integer getPosX() {
        return posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public Integer getPosColor() {
        return posColor;
    }

    private Integer setPosColor() {
        this.posColor = this.colorMap.get(this.posX).get(this.posY);
        setExplored();
        return this.posColor;
    }

    private void setExplored() {
        if (!this.exploredMap.containsKey(this.posX)) {
            this.exploredMap.put(this.posX, new HashMap<>());
        }

        this.exploredMap.get(this.posX).put(this.posY, true);
    }

    private Boolean isExplored(Integer x, Integer y) {
        return this.exploredMap.containsKey(x) && this.exploredMap.get(x).containsKey(y);
    }

    public Integer getInitialColor() {
        return initialColor;
    }

    public Integer getNextRightColor() {
        if (isPosOutOfBound(this.posX, this.posY + 1) || isExplored(this.posX, this.posY + 1)) {
            return -1;
        }

        return this.colorMap.get(this.posX).get(this.posY + 1);
    }

    public Integer getNextBottomColor() {
        if (isPosOutOfBound(this.posX + 1, this.posY) || isExplored(this.posX + 1, this.posY)) {
            return -1;
        }

        return this.colorMap.get(this.posX + 1).get(this.posY);
    }

    public Integer getNextLeftColor() {
        if (isPosOutOfBound(this.posX, this.posY - 1) || isExplored(this.posX, this.posY - 1)) {
            return -1;
        }

        return this.colorMap.get(this.posX).get(this.posY - 1);
    }

    public Integer getNextTopColor() {
        if (isPosOutOfBound(this.posX - 1, this.posY) || isExplored(this.posX -1, this.posY)) {
            return -1;
        }

        return this.colorMap.get(this.posX - 1).get(this.posY);
    }

    public Integer moveFrom(DIRECTIONS origin) {
        switch (origin) {
            case LEFT : {
                right();
                break;
            }
            case RIGHT: {
                left();
                break;
            }
            case BOTTOM: {
                up();
                break;
            }
            case TOP: {
                down();
                break;
            }
            case INIT: {
                break;
            }
        }

        return this.getPosColor();
    }

    public Integer right() {
        if (isColumnOutOfBound(this.posY + 1)) {
            return -1;
        }

        this.posY++;
        return this.setPosColor();
    }

    public Integer down() {
        if (isColumnOutOfBound(this.posX + 1)) {
            return -1;
        }

        this.posX++;
        return this.setPosColor();
    }

    public Integer left() {
        if (isColumnOutOfBound(this.posY - 1)) {
            return -1;
        }

        this.posY--;
        return this.setPosColor();
    }

    public Integer up() {
        if (isLineOutOfBound(this.posX - 1)) {
            return -1;
        }

        this.posX--;
        return this.setPosColor();
    }

    public boolean isPosOutOfBound(Integer x, Integer y) {
        if (x < 0 || x >= this.colorMap.size()) {
            return true;
        } else if (y < 0 || y >= this.colorMap.get(x).size()) {
            return true;
        }

        return false;
    }

    public boolean isColumnOutOfBound(Integer posY) {
        return posY < 0 || this.colorMap.get(this.posX).size() <= posY;
    }

    public boolean isLineOutOfBound(Integer posX) {
        return posX < 0 || this.colorMap.size() <= posX;
    }

    public static ColorMapBuilder get(List<List<Integer>> map) {
        return ColorMapBuilder.get(map);
    }

    public void print() {
        for (int x = 0; x < this.colorMap.size(); x++) {
            List<Integer> mapLine = this.colorMap.get(x);
            System.out.println();
            for (int y = 0; y < mapLine.size(); y++) {
                if (x == this.posX && y == this.posY) {
                    System.out.print("[" + mapLine.get(y) + "]");
                } else {
                    System.out.print((y == 0 ? "|" : "") + mapLine.get(y) + ((y + 1 == this.posY && this.posX == x) ? "" : "|"));
                }
            }
            this.printBorderLine();
        }
        System.out.println();
    }

    private void printBorderLine() {
        System.out.print("\n");
        IntStream.range(0, this.colorMap.get(this.posX).size() - 1)
                .forEach((i) -> System.out.print("---"));
    }

    public static class ColorMapBuilder {
        private final List<List<Integer>> map;
        private Integer posX;
        private Integer posY;
        private Map<Integer, Map<Integer, Boolean>> exploredMap;

        private ColorMapBuilder(List<List<Integer>> map) {
            this.map = map;
        }

        public ColorMapBuilder withPos(Integer x, Integer y) {
            if (x == null || this.map.size() <= x) {
                throw new Error("Cannot build ColorMap with this X position " + posX);
            }

            if (y == null || this.map.get(0).size() <= y) {
                throw new Error("Cannot build ColorMap with this Y position " + posY);
            }

            this.posX = x;
            this.posY = y;
            return this;
        }

        public ColorMapBuilder withExploredMap(Map<Integer, Map<Integer, Boolean>> exploredMap) {
            this.exploredMap = exploredMap;
            return this;
        }

        public static ColorMapBuilder get(List<List<Integer>> map) {
            return new ColorMapBuilder(map);
        }

        public ColorMap build() {
            ColorMap instance = new ColorMap(this.map, this.posX, this.posY, this.exploredMap);
            return instance;
        }
    }

}
