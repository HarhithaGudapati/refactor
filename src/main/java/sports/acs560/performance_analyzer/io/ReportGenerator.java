package sports.acs560.performance_analyzer.io;

import java.io.FileWriter;
import java.io.IOException;

import sports.acs560.performance_analyzer.model.Analysis;
import sports.acs560.performance_analyzer.model.Team;

public class ReportGenerator {
    public void createReport(Analysis analysis, String filename) {
        try (FileWriter fileWriter = new FileWriter(filename)) {
            writeStatistics(fileWriter, analysis);
            writeTeamExtremes(fileWriter, analysis);
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }

    private void writeStatistics(FileWriter writer, Analysis analysis) throws IOException {
        writer.write(String.format("Mean Points: %.2f%n", analysis.getMeanPoints()));
        writer.write(String.format("Median Points: %.2f%n", analysis.getMedianPoints()));
    }

    private void writeTeamExtremes(FileWriter writer, Analysis analysis) throws IOException {
        writeTeamStat(writer, "Most Wins Team", analysis.getMostWinsTeam(), "wins");
        writeTeamStat(writer, "Most Losses Team", analysis.getMostLossesTeam(), "losses");
        writeTeamStat(writer, "Highest Points Team", analysis.getHighestPointsTeam(), "points");
        writeTeamStat(writer, "Lowest Points Team", analysis.getLowestPointsTeam(), "points");
    }

    private void writeTeamStat(FileWriter writer, String label, Team team, String statType) throws IOException {
        if (team != null) {
            int statValue = switch (statType) {
                case "wins" -> team.getWins();
                case "losses" -> team.getLosses();
                case "points" -> team.getPoints();
                default -> 0;
            };
            writer.write(String.format("%s: %s (%d %s)%n", label, team.getName(), statValue, statType));
        } else {
            writer.write("Problem in finding the teams.\n");
        }
    }
}

