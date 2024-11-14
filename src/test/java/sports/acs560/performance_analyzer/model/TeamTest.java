package sports.acs560.performance_analyzer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    private Team team;

    @BeforeEach
    void setUp() {
        team = new Team(2018, "Liverpool", "Premier League", 21, 2, 91);
    }

    @Test
    void testValidTeamCreation() {
        assertNotNull(team);
        assertEquals(2018, team.getYear());
        assertEquals("Liverpool", team.getName());
        assertEquals("Premier League", team.getLeague());
        assertEquals(21, team.getWins());
        assertEquals(2, team.getLosses());
        assertEquals(91, team.getPoints());
    }

    @Test
    void testInvalidYear() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(1799, "Test Team", "Test League", 10, 5, 30);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(2101, "Test Team", "Test League", 10, 5, 30);
        });
    }

    @Test
    void testInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(2018, null, "Premier League", 21, 2, 91);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(2018, "", "Premier League", 21, 2, 91);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(2018, "   ", "Premier League", 21, 2, 91);
        });
    }

    @Test
    void testInvalidLeague() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(2018, "Liverpool", null, 21, 2, 91);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(2018, "Liverpool", "", 21, 2, 91);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(2018, "Liverpool", "   ", 21, 2, 91);
        });
    }

    @Test
    void testInvalidWinsLossesPoints() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(2018, "Liverpool", "Premier League", -1, 2, 91);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(2018, "Liverpool", "Premier League", 21, -1, 91);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Team(2018, "Liverpool", "Premier League", 21, 2, -1);
        });
    }

    @Test
    void testSetters() {
        team.setYear(2019);
        assertEquals(2019, team.getYear());

        team.setName("New Team");
        assertEquals("New Team", team.getName());

        team.setLeague("New League");
        assertEquals("New League", team.getLeague());

        team.setWins(15);
        assertEquals(15, team.getWins());

        team.setLosses(8);
        assertEquals(8, team.getLosses());

        team.setPoints(45);
        assertEquals(45, team.getPoints());
    }

    @Test
    void testInvalidSetters() {
        assertThrows(IllegalArgumentException.class, () -> team.setYear(1799));
        assertThrows(IllegalArgumentException.class, () -> team.setName(null));
        assertThrows(IllegalArgumentException.class, () -> team.setLeague(""));
        assertThrows(IllegalArgumentException.class, () -> team.setWins(-1));
        assertThrows(IllegalArgumentException.class, () -> team.setLosses(-1));
        assertThrows(IllegalArgumentException.class, () -> team.setPoints(-1));
    }

    @Test
    void testEqualsAndHashCode() {
        Team sameTeam = new Team(2018, "Liverpool", "Premier League", 21, 2, 91);
        Team differentTeam = new Team(2018, "Other Team", "Premier League", 21, 2, 91);

        assertEquals(team, sameTeam);
        assertEquals(team.hashCode(), sameTeam.hashCode());
        assertNotEquals(team, differentTeam);
        assertNotEquals(team.hashCode(), differentTeam.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Team{year=2018, name='Liverpool', league='Premier League', wins=21, losses=2, points=91}";
        assertEquals(expected, team.toString());
    }
}
