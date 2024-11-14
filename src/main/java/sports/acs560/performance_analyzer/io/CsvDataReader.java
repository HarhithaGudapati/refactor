package sports.acs560.performance_analyzer.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import sports.acs560.performance_analyzer.model.Team;

public class CsvDataReader {
    public List<Team> readDataFromCSV(String filename) {
        List<Team> teams = new ArrayList<>();
        File dataFile = new File(filename);
        try (Scanner fileScanner = new Scanner(dataFile)) {
            boolean isFirstLine = true;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                teams.add(parseTeamFromCsvLine(line));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return teams;
    }

    private Team parseTeamFromCsvLine(String line) {
        String[] data = line.split(",");
        return new Team(
            Integer.parseInt(data[0].trim()),
            data[1].trim(),
            data[2].trim(),
            Integer.parseInt(data[3].trim()),
            Integer.parseInt(data[4].trim()),
            Integer.parseInt(data[5].trim())
        );
    }
}
