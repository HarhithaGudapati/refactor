package sports.acs560.performance_analyzer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import sports.acs560.performance_analyzer.analysis.DataAnalyzer;
import sports.acs560.performance_analyzer.io.CsvDataReader;
import sports.acs560.performance_analyzer.io.ReportGenerator;
import sports.acs560.performance_analyzer.model.Analysis;
import sports.acs560.performance_analyzer.model.Team;

public class Application {

	 private final CsvDataReader dataReader;
	    private final DataAnalyzer dataAnalyzer;
	    private final ReportGenerator reportGenerator;

	    public Application() {
	        this.dataReader = new CsvDataReader();
	        this.dataAnalyzer = new DataAnalyzer();
	        this.reportGenerator = new ReportGenerator();
	    }

	    public static void main(String[] args) {
	        Application app = new Application();
	        app.run("sports_data.csv", "analysis_report.txt");
	    }

	    public void run(String csvFilename, String reportFilename) {
	        List<Team> teams = dataReader.readDataFromCSV(csvFilename);
	        if (teams.isEmpty()) {
	            System.out.println("No data found. Exiting.");
	            return;
	        }
	        
	        Analysis analysis = dataAnalyzer.analyzeData(teams);
	        reportGenerator.createReport(analysis, reportFilename);
	        System.out.println("Analysis complete. Report saved to " + reportFilename);
	    }
	}
