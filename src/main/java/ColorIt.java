import analyzer.MapExplorer;
import parser.CsvColorMapParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ColorIt {

    public static final Integer FILE_NAME_ARG_INDEX = 0;
    public static final Integer NB_COLOR_ARG_INDEX = 1;

    public static void main(String[] args) {
        validateArgs(args);
        List<List<Integer>> colorMap = CsvColorMapParser.loadCSVInputAsColorMap(args[FILE_NAME_ARG_INDEX]);
        Integer nbColors = Integer.parseInt(args[NB_COLOR_ARG_INDEX]);
        MapExplorer.play(colorMap, nbColors);
    }

    private static void validateArgs(String[] args) {
        if (args == null || args.length < NB_COLOR_ARG_INDEX + 1) {
            throw new Error(
                    "You must provide a CSV input filename as argument index "
                            + FILE_NAME_ARG_INDEX
                            + " and nb colors as argument index "
                            + NB_COLOR_ARG_INDEX
            );
        }

        if (!Files.exists(Path.of(args[FILE_NAME_ARG_INDEX]))) {
            throw new Error("The CSV Input file provided does not exists.");
        }

        try {
            Integer.parseInt(args[NB_COLOR_ARG_INDEX]);
        } catch (NumberFormatException ex) {
            throw new Error("Nb colors not parsable.");
        }
    }

}
