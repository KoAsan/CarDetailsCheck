package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

	public static List<String> readCarRegsFromText(String fileName) throws IOException {
		List<String> registrationNumbers = new ArrayList<>();

		// Regular expression pattern for UK car registration numbers
		String regex = "\\b[A-Z]{1,2}\\d{2}[A-Z]{1,3}\\b|\\b[A-Z]{1,2}\\d{2} [A-Z]{1,3}\\b";
		Pattern pattern = Pattern.compile(regex);

		// Open the file and read the lines
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = reader.readLine()) != null) {
				// Check if the line contains any registration numbers
				Matcher matcher = pattern.matcher(line);
				while (matcher.find()) {
					// Add the matched registration number to the list
					registrationNumbers.add(matcher.group());
				}
			}

		}
		return registrationNumbers;
	}

	public static Map<String, String[]> readExpectedOutput(String filePath) throws IOException {
		Map<String, String[]> expectedDetails = new HashMap<>();
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line;
		while ((line = reader.readLine()) != null) {
			String[] parts = line.split(",");
			if (parts.length == 4) {
				expectedDetails.put(parts[0].trim(),
						new String[] { parts[1].trim(), parts[2].trim(), parts[3].trim() });
			}
		}
		reader.close();
		return expectedDetails;
	}
	
	// Method to compare extracted data with expected output
    public static void compareDetails(String reg, String make, String model, String year, String[] expected) {
        if (make.equalsIgnoreCase(expected[0]) && model.equalsIgnoreCase(expected[1]) && year.equals(expected[2])) {
        	System.out.println("expected make " +expected[0] + "/ Actual make "+ make);
        	System.out.println("expected model " +expected[1]+ "/ Actual model "+ model);
        	System.out.println("expected year " +expected[2]+ "/ Actual year "+ year);
            System.out.println("Test Passed for " + reg);
        } else {
            System.out.println("Test Failed for " + reg);
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Found: " + make + ", " + model + ", " + year);
        }
    }	

}
