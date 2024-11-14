package sports.acs560.performance_analyzer.analysis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sports.acs560.performance_analyzer.model.Analysis;
import sports.acs560.performance_analyzer.model.Team;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataAnalyzerTest {

    private DataAnalyzer analyzer;
    private List<Team> testTeams;

    @BeforeEach
    void setUp() {
        analyzer = new DataAnalyzer();
        testTeams = Arrays.asList(
            new Team(2018, "Liverpool", "Premier League", 21, 2, 91),
            new Team(2018, "Manchester Utd", "Premier League", 20, 10, 84),
            new Team(2018, "Chelsea", "Premier League", 24, 3, 79),
            new Team(2018, "Manchester City", "Premier League", 28, 11, 97),
            new Team(2019, "Manchester City", "Premier League", 29, 11, 93),
            new Team(2019, "Chelsea", "Premier League", 32, 6, 92),
            new Team(2019, "Liverpool", "Premier League", 28, 7, 76),
            new Team(2019, "Manchester Utd", "Premier League", 20, 9, 82),
            new Team(2020, "Liverpool", "Premier League", 25, 10, 95),
            new Team(2020, "Chelsea", "Premier League", 21, 8, 79),
            new Team(2020, "Manchester City", "Premier League", 19, 12, 84),
            new Team(2020, "Manchester Utd", "Premier League", 30, 7, 99),
            new Team(2021, "Liverpool", "Premier League", 27, 13, 75),
            new Team(2021, "Chelsea", "Premier League", 26, 6, 78),
            new Team(2021, "Manchester City", "Premier League", 18, 3, 71),
            new Team(2021, "Manchester Utd", "Premier League", 20, 5, 93),
            new Team(2021, "Chelsea", "Premier League", 29, 3, 96)
        );
    }

    @Test
    void testAnalyzeData() {
        Analysis analysis = analyzer.analyzeData(testTeams);
        
        // Calculate expected mean points
        double totalPoints = 91 + 84 + 79 + 97 + 93 + 92 + 76 + 82 + 95 + 79 + 84 + 99 + 75 + 78 + 71 + 93 + 96;
        double expectedMeanPoints = totalPoints / testTeams.size(); // 1393 / 17 ≈ 82.53
        assertEquals(expectedMeanPoints, analysis.getMeanPoints(), 0.01);
        
        // Calculate expected median points
        double[] points = {91, 84, 79, 97, 93, 92, 76, 82, 95, 79, 84, 99, 75, 78, 71, 93, 96};
        Arrays.sort(points);
        double expectedMedianPoints;
        if (points.length % 2 == 0) {
            expectedMedianPoints = (points[points.length / 2 - 1] + points[points.length / 2]) / 2.0;
        } else {
            expectedMedianPoints = points[points.length / 2];
        }
        assertEquals(expectedMedianPoints, analysis.getMedianPoints(), 0.01);
        
        // Test extreme teams
        assertEquals("Chelsea", analysis.getMostWinsTeam().getName());
        assertEquals("Liverpool", analysis.getMostLossesTeam().getName());
        assertEquals("Manchester Utd", analysis.getHighestPointsTeam().getName());
        assertEquals("Manchester City", analysis.getLowestPointsTeam().getName());
    }
}