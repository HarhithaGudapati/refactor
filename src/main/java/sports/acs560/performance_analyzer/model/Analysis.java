package sports.acs560.performance_analyzer.model;

public class Analysis {
    private double meanPoints;
    private double medianPoints;
    private Team mostWinsTeam;
    private Team mostLossesTeam;
    private Team highestPointsTeam;
    private Team lowestPointsTeam;

    // Builder pattern for creating Analysis objects
    public static class Builder {
        private final Analysis analysis;

        public Builder() {
            analysis = new Analysis();
        }

        public Builder withMeanPoints(double meanPoints) {
            if (meanPoints < 0) {
                throw new IllegalArgumentException("Mean points cannot be negative");
            }
            analysis.meanPoints = meanPoints;
            return this;
        }

        public Builder withMedianPoints(double medianPoints) {
            if (medianPoints < 0) {
                throw new IllegalArgumentException("Median points cannot be negative");
            }
            analysis.medianPoints = medianPoints;
            return this;
        }

        public Builder withMostWinsTeam(Team team) {
            if (team == null) {
                throw new IllegalArgumentException("Most wins team cannot be null");
            }
            analysis.mostWinsTeam = team;
            return this;
        }

        public Builder withMostLossesTeam(Team team) {
            if (team == null) {
                throw new IllegalArgumentException("Most losses team cannot be null");
            }
            analysis.mostLossesTeam = team;
            return this;
        }

        public Builder withHighestPointsTeam(Team team) {
            if (team == null) {
                throw new IllegalArgumentException("Highest points team cannot be null");
            }
            analysis.highestPointsTeam = team;
            return this;
        }

        public Builder withLowestPointsTeam(Team team) {
            if (team == null) {
                throw new IllegalArgumentException("Lowest points team cannot be null");
            }
            analysis.lowestPointsTeam = team;
            return this;
        }

        public Analysis build() {
            validateAnalysis(analysis);
            return analysis;
        }

        private void validateAnalysis(Analysis analysis) {
            if (analysis.meanPoints < 0 || analysis.medianPoints < 0) {
                throw new IllegalStateException("Statistical values cannot be negative");
            }
            if (analysis.mostWinsTeam == null || analysis.mostLossesTeam == null ||
                analysis.highestPointsTeam == null || analysis.lowestPointsTeam == null) {
                throw new IllegalStateException("All team references must be set");
            }
        }
    }

    // Private constructor to enforce use of Builder
    public Analysis() {}

    // Getters
    public double getMeanPoints() {
        return meanPoints;
    }
    public void setMeanPoints(double meanPoints) {
        this.meanPoints = meanPoints;
    }

    public double getMedianPoints() {
        return medianPoints;
    }
    
    public void setMedianPoints(double medianPoints) {
        this.medianPoints = medianPoints;
    }

    public Team getMostWinsTeam() {
        return mostWinsTeam;
    }
    
    public void setMostWinsTeam(Team mostWinsTeam) {
        this.mostWinsTeam = mostWinsTeam;
    }

    public Team getMostLossesTeam() {
        return mostLossesTeam;
    }
    

    public void setMostLossesTeam(Team mostLossesTeam) {
        this.mostLossesTeam = mostLossesTeam;
    }
    
    public Team getHighestPointsTeam() {
        return highestPointsTeam;
    }
    
    public void setHighestPointsTeam(Team HighestPointsTeam) {
        this.highestPointsTeam = HighestPointsTeam;
    }

    public Team getLowestPointsTeam() {
        return lowestPointsTeam;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Analysis other = (Analysis) o;
        return Double.compare(other.meanPoints, meanPoints) == 0 &&
               Double.compare(other.medianPoints, medianPoints) == 0 &&
               mostWinsTeam.equals(other.mostWinsTeam) &&
               mostLossesTeam.equals(other.mostLossesTeam) &&
               highestPointsTeam.equals(other.highestPointsTeam) &&
               lowestPointsTeam.equals(other.lowestPointsTeam);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Double.hashCode(meanPoints);
        result = 31 * result + Double.hashCode(medianPoints);
        result = 31 * result + mostWinsTeam.hashCode();
        result = 31 * result + mostLossesTeam.hashCode();
        result = 31 * result + highestPointsTeam.hashCode();
        result = 31 * result + lowestPointsTeam.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("Analysis{meanPoints=%.2f, medianPoints=%.2f, " +
                           "mostWinsTeam='%s', mostLossesTeam='%s', " +
                           "highestPointsTeam='%s', lowestPointsTeam='%s'}",
                           meanPoints, medianPoints,
                           mostWinsTeam.getName(), mostLossesTeam.getName(),
                           highestPointsTeam.getName(), lowestPointsTeam.getName());
    }

	public void setLowestPointsTeam(Team LowestPointsTeam ) {
		// TODO Auto-generated method stub
		this.lowestPointsTeam = LowestPointsTeam;
	}
}
