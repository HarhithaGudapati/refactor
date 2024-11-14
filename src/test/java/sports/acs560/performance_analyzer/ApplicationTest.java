package sports.acs560.performance_analyzer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class ApplicationTest {

    private Application app;

    @BeforeEach
    void setUp() {
        app = new Application();
    }

    @Test
    void testRunWithValidData() throws IOException {
        // Create test input file in a temporary directory
        Path tempDir = Files.createTempDirectory("temp");
        File inputFile = tempDir.resolve("sports_data.csv").toFile();
        
        try (FileWriter writer = new FileWriter(inputFile)) {
            writer.write("year,name,league,wins,losses,points\n");
            writer.write("2018,Liverpool,Premier League,21,2,91\n");
            writer.write("2018,Manchester Utd,Premier League,20,10,84\n");
        }

        // Define the output path in the "test-output" directory within the project
        Path outputDir = Paths.get("test-output");
        if (!Files.exists(outputDir)) {
            Files.createDirectory(outputDir); // Create the directory if it doesn't exist
        }
        Path outputFilePath = outputDir.resolve("test_output.txt");
        File outputFile = outputFilePath.toFile();
        
        // Run the application
        app.run(inputFile.getPath(), outputFile.getPath());

        // Validate that the output file is created and contains expected content
        assertTrue(outputFile.exists());
        String content = new String(Files.readAllBytes(outputFilePath));
        assertTrue(content.contains("Mean Points"));
        assertTrue(content.contains("Median Points"));
        
        // Optionally, you could clean up by deleting the file if necessary
        // outputFile.delete();
    }
}

