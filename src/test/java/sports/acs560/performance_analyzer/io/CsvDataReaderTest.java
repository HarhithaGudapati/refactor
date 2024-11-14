package sports.acs560.performance_analyzer.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import sports.acs560.performance_analyzer.model.Team;

public class CsvDataReaderTest {

    private CsvDataReader reader;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        reader = new CsvDataReader();
    }

    @Test
    void testReadDataFromCSV() throws IOException {
        // Create test CSV file
        File testFile = tempDir.resolve("test_data.csv").toFile();
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("year,name,league,wins,losses,points\n");
            writer.write("2018,Liverpool,Premier League,21,2,91\n");
            writer.write("2018,Manchester Utd,Premier League,20,10,84\n");
        }

        List<Team> teams = reader.readDataFromCSV(testFile.getPath());

        assertEquals(2, teams.size());

        Team firstTeam = teams.get(0);
        assertEquals(2018, firstTeam.getYear());
        assertEquals("Liverpool", firstTeam.getName());
        assertEquals("Premier League", firstTeam.getLeague());
        assertEquals(21, firstTeam.getWins());
        assertEquals(2, firstTeam.getLosses());
        assertEquals(91, firstTeam.getPoints());
    }

    @Test
    void testReadDataFromNonExistentFile() {
        List<Team> teams = reader.readDataFromCSV("non_existent_file.csv");
        assertTrue(teams.isEmpty());
    }
}
