package sports.acs560.performance_analyzer.analysis;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import sports.acs560.performance_analyzer.model.Analysis;
import sports.acs560.performance_analyzer.model.Team;

public class DataAnalyzer {
    public Analysis analyzeData(List<Team> teams) {
        Analysis analysis = new Analysis();
        calculatePointsStatistics(teams, analysis);
        findExtremeTeams(teams, analysis);
        return analysis;
    }

    private void calculatePointsStatistics(List<Team> teams, Analysis analysis) {
        int totalPoints = teams.stream().mapToInt(Team::getPoints).sum();
        double meanPoints = (double) totalPoints / teams.size();
        analysis.setMeanPoints(meanPoints);

        List<Team> sortedTeams = new ArrayList<>(teams);
        sortedTeams.sort(Comparator.comparingInt(Team::getPoints));
        int size = sortedTeams.size();
        double medianPoints = (size % 2 == 0)
            ? (sortedTeams.get(size / 2 - 1).getPoints() + sortedTeams.get(size / 2).getPoints()) / 2.0
            : sortedTeams.get(size / 2).getPoints();
        analysis.setMedianPoints(medianPoints);
    }

    private void findExtremeTeams(List<Team> teams, Analysis analysis) {
        analysis.setMostWinsTeam(teams.stream()
            .max(Comparator.comparingInt(Team::getWins))
            .orElse(null));
        
        analysis.setMostLossesTeam(teams.stream()
            .max(Comparator.comparingInt(Team::getLosses))
            .orElse(null));

        analysis.setHighestPointsTeam(teams.stream()
            .max(Comparator.comparingInt(Team::getPoints))
            .orElse(null));

        analysis.setLowestPointsTeam(teams.stream()
            .min(Comparator.comparingInt(Team::getPoints))
            .orElse(null));
    }
    
  }
