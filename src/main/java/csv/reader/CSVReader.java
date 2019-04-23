package csv.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @desc Defines the behaviours for reading a csv file and the extraction of http
 * links from it.
 */
public class CSVReader {
    private static final Logger LOGGER = Logger.getLogger(CSVReader.class.getName());

    private static final String COMMA_DELIMITER = ",";
    private static final String GIT = ".git";
    //Add file path
    private static final String FILE_PATH = "C:\\Users\\Angelo Orru\\IdeaProjects\\github-wrapper\\src\\main\\resources\\PluginIndex.csv";

    /**
     * @return List<String> data
     * @desc Load into memory the csv file and add its content in the list.
     */
    public List<String> getDataFromCsv() {
        List<String> data = new ArrayList<>();

        try (Stream<String> stream = Files.lines(Paths.get(FILE_PATH))) {
            data = stream.collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
            e.printStackTrace();
        }
        return data;
    }

    /**
     * @return List<String> httpLinks
     * @desc Retrieves the git links from the parsed csv file.
     * This will be used in the request
     */
    //TODO://Need restructuring to be fully independent.
    public List<String> getHttpsLinksFromCsvList(List<String> data) {
        List<String> httpLinks = data.stream()
                .map(csvLine -> csvLine.split(COMMA_DELIMITER))
                .flatMap(Arrays::stream)
                .filter(item -> item.endsWith(GIT))
                .collect(Collectors.toList());

        return httpLinks;
    }
}
