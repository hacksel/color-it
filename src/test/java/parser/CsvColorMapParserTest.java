package parser;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CsvColorMapParserTest {

    @Test()
    public void should_throw_error_when_csv_was_unparsable_because_of_separator() {
        // when
        Error actualException = assertThrows(Error.class, () -> {
            CsvColorMapParser.loadCSVInputAsColorMap("src/test/resources/unparsable_separator.csv");
        });

        // then
        assertEquals(actualException.getMessage(), "Unparsable CSV Input file.");
    }

    @Test()
    public void should_throw_error_when_csv_was_unparsable_because_of_nan() {
        // when
        Error actualException = assertThrows(Error.class, () -> {
            CsvColorMapParser.loadCSVInputAsColorMap("src/test/resources/unparsable_not_a_number.csv");
        });

        // then
        assertEquals(actualException.getMessage(), "Unparsable CSV Input file.");
    }

    @Test()
    public void should_parse_csv_input_file_as_color_map() {
        // when
        List<List<Integer>> colorMap = CsvColorMapParser.
                loadCSVInputAsColorMap("src/test/resources/parsable_color_map.csv");

        // then
        assertNotNull(colorMap);
        assertEquals(colorMap.size(), 4);

        List<Integer> expectedSecondColorMapLine = Arrays.asList(0,1,1,0);
        assertEquals(colorMap.get(1), expectedSecondColorMapLine);
    }

}
