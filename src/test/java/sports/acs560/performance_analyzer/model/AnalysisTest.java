package sports.acs560.performance_analyzer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnalysisTest {
    private Team team1;
    private Team team2;
    private Analysis.Builder builder;

    @BeforeEach
    void setUp() {
        team1 = new Team(2018, "Liverpool", "Premier League", 21, 2, 91);
        team2 = new Team(2018, "Manchester Utd", "Premier League", 20, 10, 84);
        builder = new Analysis.Builder()
            .withMeanPoints(87.5) // Example mean points based on provided data
            .withMedianPoints(87.5) // Example median points based on provided data
            .withMostWinsTeam(team1)
            .withMostLossesTeam(team2)
            .withHighestPointsTeam(team1)
            .withLowestPointsTeam(team2);
    }

    @Test
    void testValidAnalysisCreation() {
        Analysis analysis = builder.build();
        
        assertEquals(87.5, analysis.getMeanPoints());
        assertEquals(87.5, analysis.getMedianPoints());
        assertEquals(team1, analysis.getMostWinsTeam());
        assertEquals(team2, analysis.getMostLossesTeam());
        assertEquals(team1, analysis.getHighestPointsTeam());
        assertEquals(team2, analysis.getLowestPointsTeam());
    }

    @Test
    void testNegativeMeanPoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.withMeanPoints(-1.0);
        });
    }

    @Test
    void testNegativeMedianPoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.withMedianPoints(-1.0);
        });
    }

    @Test
    void testNullTeams() {
        assertThrows(IllegalArgumentException.class, () -> {
            builder.withMostWinsTeam(null);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            builder.withMostLossesTeam(null);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            builder.withHighestPointsTeam(null);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            builder.withLowestPointsTeam(null);
        });
    }

    @Test
    void testIncompleteAnalysis() {
        Analysis.Builder incompleteBuilder = new Analysis.Builder()
            .withMeanPoints(87.5)
            .withMedianPoints(87.5);
            
        assertThrows(IllegalStateException.class, incompleteBuilder::build);
    }

    @Test
    void testEqualsAndHashCode() {
        Analysis analysis1 = builder.build();
        Analysis analysis2 = new Analysis.Builder()
            .withMeanPoints(87.5)
            .withMedianPoints(87.5)
            .withMostWinsTeam(team1)
            .withMostLossesTeam(team2)
            .withHighestPointsTeam(team1)
            .withLowestPointsTeam(team2)
            .build();

        assertEquals(analysis1, analysis2);
        assertEquals(analysis1.hashCode(), analysis2.hashCode());
    }

    @Test
    void testToString() {
        Analysis analysis = builder.build();
        String expected = String.format("Analysis{meanPoints=%.2f, medianPoints=%.2f, " +
                                      "mostWinsTeam='%s', mostLossesTeam='%s', " +
                                      "highestPointsTeam='%s', lowestPointsTeam='%s'}",
                                      87.5, 87.5,
                                      team1.getName(), team2.getName(),
                                      team1.getName(), team2.getName());
        assertEquals(expected, analysis.toString());
    }
}