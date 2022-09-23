import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ColorItTest {

    @Test()
    public void should_throw_error_when_no_filename_provided() {
        // when
        Error actualException = assertThrows(Error.class, () -> {
            ColorIt.main(null);
        });

        // then
        assertEquals(actualException.getMessage(),
                "You must provide a CSV input filename as argument index " + ColorIt.FILE_NAME_ARG_INDEX);
    }

    @Test()
    public void should_throw_error_when_filename_does_not_exists() {
        // when
        Error actualException = assertThrows(Error.class, () -> {
            ColorIt.main(new String[]{"file-that-does-not-exits"});
        });

        // then
        assertEquals(actualException.getMessage(),
                "The CSV Input file provided does not exists.");
    }

    @Test
    public void should_play_complex_game() {
        ColorIt.main(new String[]{"src/test/resources/color-it.csv"});
    }

}