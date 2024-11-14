package sports.acs560.performance_analyzer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

class ApplicationTest {

    private Application app;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        app = new Application();
    }

    @Test
    void testRunWithValidData() throws IOException {
        // Create test input file
        File inputFile = tempDir.resolve("test_input.csv").toFile();
        try (FileWriter writer = new FileWriter(inputFile)) {
            writer.write("year,name,league,wins,losses,points\n");
            writer.write("2018,Liverpool,Premier League,21,2,91\n");
            writer.write("2018,Manchester Utd,Premier League,20,10,84\n");
        }

        File outputFile = tempDir.resolve("test_output.txt").toFile();
        app.run(inputFile.getPath(), outputFile.getPath());

        assertTrue(outputFile.exists());
        String content = new String(java.nio.file.Files.readAllBytes(outputFile.toPath()));
        assertTrue(content.contains("Mean Points"));
        assertTrue(content.contains("Median Points"));
    }
}