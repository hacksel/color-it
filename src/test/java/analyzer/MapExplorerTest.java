package analyzer;

import colormap.ColorMap;
import org.junit.jupiter.api.Test;

import java.util.List;

import static analyzer.MapExplorer.computeBestNextColor;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MapExplorerTest {

    @Test
    public void should_compute_current_map_area() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(1, 2, 0, 0),
                asList(0, 1, 1, 0),
                asList(2, 2, 0, 1),
                asList(0, 0, 0, 1)
        );

        // when
        Integer computedArea = MapExplorer.computeCurrentColorArea(currentMap);

        // then
        assertEquals(1, computedArea);
    }

    @Test
    public void should_compute_current_map_area_1() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(1, 1, 0, 0),
                asList(0, 1, 1, 0),
                asList(2, 2, 0, 1),
                asList(0, 0, 0, 1)
        );

        // when
        Integer computedArea = MapExplorer.computeCurrentColorArea(currentMap);

        // then
        assertEquals(4, computedArea);
    }

    @Test
    public void should_compute_current_map_area_2() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(1, 1, 0, 0),
                asList(1, 1, 1, 0),
                asList(2, 2, 0, 1),
                asList(0, 0, 0, 1)
        );

        // when
        Integer computedArea = MapExplorer.computeCurrentColorArea(currentMap);

        // then
        assertEquals(5, computedArea);
    }

    @Test
    public void should_compute_current_map_area_3() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 0, 0, 0),
                asList(0, 1, 0, 0),
                asList(2, 2, 0, 1),
                asList(0, 0, 0, 1)
        );

        // when
        Integer computedArea = MapExplorer.computeCurrentColorArea(currentMap);

        // then
        assertEquals(11, computedArea);
    }

    @Test
    public void should_compute_current_map_area_4() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 0, 0, 0),
                asList(0, 0, 0, 0),
                asList(0, 2, 0, 1),
                asList(0, 0, 0, 1)
        );

        // when
        Integer computedArea = MapExplorer.computeCurrentColorArea(currentMap);

        // then
        assertEquals(13, computedArea);
    }

    @Test
    public void should_compute_current_map_area_5() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 0, 0, 0),
                asList(0, 0, 0, 0),
                asList(2, 2, 2, 2),
                asList(2, 2, 2, 2)
        );

        // when
        Integer computedArea = MapExplorer.computeCurrentColorArea(currentMap);

        // then
        assertEquals(8, computedArea);
    }

    @Test
    public void should_compute_current_map_area_6() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 1, 0, 0),
                asList(0, 0, 0, 0),
                asList(2, 2, 2, 2),
                asList(2, 2, 2, 2)
        );

        // when
        Integer computedArea = MapExplorer.computeCurrentColorArea(currentMap);

        // then
        assertEquals(7, computedArea);
    }

    @Test
    public void should_switch_to_given_color() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 1, 0, 0),
                asList(0, 0, 0, 0),
                asList(2, 2, 2, 2),
                asList(2, 2, 2, 2)
        );

        List<List<Integer>> expectedMap = asList(
                asList(2, 1, 2, 2),
                asList(2, 2, 2, 2),
                asList(2, 2, 2, 2),
                asList(2, 2, 2, 2)
        );

        // when
        ColorMap result = MapExplorer.switchMapColorTo(currentMap, 2);

        // then
        assertNotNull(result);
        result.print();
        assertEquals(expectedMap, result.getColorMap());
    }

    @Test
    public void should_switch_to_given_color_1() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 0, 0, 0),
                asList(1, 1, 1, 1),
                asList(0, 0, 0, 0),
                asList(2, 2, 2, 2)
        );

        List<List<Integer>> expectedMap = asList(
                asList(2, 2, 2, 2),
                asList(1, 1, 1, 1),
                asList(0, 0, 0, 0),
                asList(2, 2, 2, 2)
        );

        // when
        ColorMap result = MapExplorer.switchMapColorTo(currentMap, 2);

        // then
        assertNotNull(result);
        result.print();
        assertEquals(expectedMap, result.getColorMap());
    }

    @Test
    public void should_switch_to_given_color_2() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 0, 0, 0),
                asList(1, 1, 1, 1),
                asList(0, 0, 0, 0),
                asList(2, 2, 2, 2)
        );

        List<List<Integer>> expectedMap = asList(
                asList(1, 1, 1, 1),
                asList(1, 1, 1, 1),
                asList(0, 0, 0, 0),
                asList(2, 2, 2, 2)
        );

        // when
        ColorMap result = MapExplorer.switchMapColorTo(currentMap, 1);

        // then
        assertNotNull(result);
        result.print();
        assertEquals(expectedMap, result.getColorMap());
    }

    @Test
    public void should_compute_best_next_color() {
        // given
        Integer nbColors = 2;
        List<List<Integer>> currentMap = asList(
                asList(0, 0, 0, 0),
                asList(1, 1, 1, 1),
                asList(0, 0, 0, 0),
                asList(2, 2, 2, 2)
        );

        // when
        Integer nextColor = computeBestNextColor(currentMap, nbColors);

        // then
        assertEquals(nextColor, 1);
    }

    @Test
    public void should_compute_best_next_color_1() {
        // given
        Integer nbColors = 2;
        List<List<Integer>> currentMap = asList(
                asList(1, 1, 0, 0),
                asList(1, 1, 0, 0),
                asList(2, 2, 2, 2),
                asList(2, 2, 0, 0)
        );

        // when
        Integer nextColor = computeBestNextColor(currentMap, nbColors);

        // then
        assertEquals(2, nextColor);
    }

    @Test
    public void should_compute_best_next_color_2() {
        // given
        Integer nbColors = 5;
        List<List<Integer>> currentMap = asList(
                asList(1, 1, 1, 1),
                asList(1, 1, 1, 1),
                asList(1, 1, 1, 5),
                asList(1, 1, 1, 5)
        );

        // when
        Integer nextColor = computeBestNextColor(currentMap, nbColors);

        // then
        assertEquals(5, nextColor);
    }

}