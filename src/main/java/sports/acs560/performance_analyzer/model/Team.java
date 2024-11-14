package sports.acs560.performance_analyzer.model;

public class Team {
    private int year;
    private String name;
    private String league;
    private int wins;
    private int losses;
    private int points;

    public Team(int year, String name, String league, int wins, int losses, int points) {
        validateYear(year);
        validateName(name);
        validateLeague(league);
        validateWins(wins);
        validateLosses(losses);
        validatePoints(points);
        
        this.year = year;
        this.name = name;
        this.league = league;
        this.wins = wins;
        this.losses = losses;
        this.points = points;
    }

    // Getters
    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getLeague() {
        return league;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getPoints() {
        return points;
    }

    // Setters with validation
    public void setYear(int year) {
        validateYear(year);
        this.year = year;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name;
    }

    public void setLeague(String league) {
        validateLeague(league);
        this.league = league;
    }

    public void setWins(int wins) {
        validateWins(wins);
        this.wins = wins;
    }

    public void setLosses(int losses) {
        validateLosses(losses);
        this.losses = losses;
    }

    public void setPoints(int points) {
        validatePoints(points);
        this.points = points;
    }

    // Validation methods
    private void validateYear(int year) {
        if (year < 1800 || year > 2100) {
            throw new IllegalArgumentException("Year must be between 1800 and 2100");
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be null or empty");
        }
    }

    private void validateLeague(String league) {
        if (league == null || league.trim().isEmpty()) {
            throw new IllegalArgumentException("League name cannot be null or empty");
        }
    }

    private void validateWins(int wins) {
        if (wins < 0) {
            throw new IllegalArgumentException("Wins cannot be negative");
        }
    }

    private void validateLosses(int losses) {
        if (losses < 0) {
            throw new IllegalArgumentException("Losses cannot be negative");
        }
    }

    private void validatePoints(int points) {
        if (points < 0) {
            throw new IllegalArgumentException("Points cannot be negative");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Team team = (Team) o;
        return year == team.year &&
               wins == team.wins &&
               losses == team.losses &&
               points == team.points &&
               name.equals(team.name) &&
               league.equals(team.league);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + year;
        result = 31 * result + name.hashCode();
        result = 31 * result + league.hashCode();
        result = 31 * result + wins;
        result = 31 * result + losses;
        result = 31 * result + points;
        return result;
    }

    @Override
    public String toString() {
        return String.format("Team{year=%d, name='%s', league='%s', wins=%d, losses=%d, points=%d}",
                           year, name, league, wins, losses, points);
    }

	public Integer getId() {
		// TODO Auto-generated method stub
		return null;
	}
}