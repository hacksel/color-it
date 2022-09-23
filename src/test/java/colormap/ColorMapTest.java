package colormap;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ColorMapTest {

    @Test
    public void should_navigate_right_in_map() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 0, 0, 0),
                asList(0, 1, 0, 0),
                asList(2, 2, 0, 1),
                asList(0, 0, 0, 1)
        );

        ColorMap colorMap = ColorMap.get(currentMap).withPos(2, 1).build();

        // when
        Integer actualColor = colorMap.right();

        // then
        assertEquals(actualColor, 0);
    }

    @Test
    public void should_navigate_bottom_in_map() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 0, 0, 0),
                asList(0, 1, 0, 0),
                asList(2, 2, 0, 1),
                asList(0, 0, 0, 1)
        );

        ColorMap colorMap = ColorMap.get(currentMap).withPos(1, 1).build();

        // when
        Integer actualColor = colorMap.down();

        // then
        assertEquals(actualColor, 2);
    }


    @Test
    public void should_navigate_top_edge_in_map() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 0, 0, 0),
                asList(0, 1, 0, 0),
                asList(2, 2, 0, 1),
                asList(0, 0, 0, 1)
        );

        ColorMap colorMap = ColorMap.get(currentMap).withPos(0, 0).build();

        // when
        Integer actualColor = colorMap.up();

        // then
        assertEquals(actualColor, -1);
    }

    @Test
    public void should_navigate_top_in_map() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 0, 0, 0),
                asList(0, 1, 0, 0),
                asList(2, 2, 0, 1),
                asList(0, 0, 0, 1)
        );

        ColorMap colorMap = ColorMap.get(currentMap).withPos(2, 1).build();

        // when
        Integer actualColor = colorMap.up();

        // then
        assertEquals(actualColor, 1);
    }

    @Test
    public void should_navigate_left_in_map() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 0, 0, 0),
                asList(0, 1, 0, 0),
                asList(2, 2, 0, 1),
                asList(0, 0, 0, 1)
        );

        ColorMap colorMap = ColorMap.get(currentMap).withPos(2, 1).build();

        // when
        Integer actualColor = colorMap.left();

        // then
        assertEquals(actualColor, 2);
    }

    @Test
    public void should_print_current_map_position() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 0, 0, 0),
                asList(0, 1, 0, 0),
                asList(2, 2, 0, 1),
                asList(0, 0, 0, 1)
        );

        ColorMap colorMap = ColorMap.get(currentMap).withPos(1, 1).build();

        // when
        colorMap.print();

        // then
    }

    @Test
    public void should_print_current_map_position_1() {
        // given
        List<List<Integer>> currentMap = asList(
                asList(0, 0, 0, 0),
                asList(0, 1, 0, 0),
                asList(2, 2, 0, 1),
                asList(0, 0, 0, 1)
        );

        ColorMap colorMap = ColorMap.get(currentMap).withPos(0, 0).build();

        // when
        colorMap.print();

        // then
    }

}