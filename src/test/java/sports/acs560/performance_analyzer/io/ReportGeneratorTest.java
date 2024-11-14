package sports.acs560.performance_analyzer.io;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import sports.acs560.performance_analyzer.model.Analysis;
import sports.acs560.performance_analyzer.model.Team;

public class ReportGeneratorTest {

    private ReportGenerator generator;
    private Analysis testAnalysis;
    
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        generator = new ReportGenerator();
        
        // Create a test analysis with sample data
        Team team1 = new Team(2018, "Liverpool", "Premier League", 21, 2, 91);
        Team team2 = new Team(2018, "Manchester Utd", "Premier League", 20, 10, 84);
        
        testAnalysis = new Analysis.Builder()
            .withMeanPoints(87.5)
            .withMedianPoints(87.5)
            .withMostWinsTeam(team1)
            .withMostLossesTeam(team2)
            .withHighestPointsTeam(team1)
            .withLowestPointsTeam(team2)
            .build();
    }

    @Test
    void testGenerateReport() throws IOException {
        // Create a temporary output file for the report
        File outputFile = tempDir.resolve("test_report.txt").toFile();
        
        // Generate the report
        generator.createReport(testAnalysis, outputFile.getPath());
        
        // Check if the report file was created
        assertTrue(outputFile.exists());
        
        // Optionally, check the content of the report
        String content = new String(java.nio.file.Files.readAllBytes(outputFile.toPath()));
        assertTrue(content.contains("Mean Points: 87.5"));
        assertTrue(content.contains("Median Points: 87.5"));
        assertTrue(content.contains("Most Wins Team: Liverpool"));
        assertTrue(content.contains("Most Losses Team: Manchester Utd"));
        assertTrue(content.contains("Highest Points Team: Liverpool"));
        assertTrue(content.contains("Lowest Points Team: Manchester Utd"));
    }

   

   
}
