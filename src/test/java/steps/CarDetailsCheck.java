package steps;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.CarDetailsSearchPage;
import utils.Utilities;

public class CarDetailsCheck {
	ChromeDriver driver = null;
	List<String> regNumbers = new ArrayList<>();
	String projectPath = System.getProperty("user.dir");

	@Before
	public void setup() {
		
		// Create an instance of ChromeOptions
		ChromeOptions options = new ChromeOptions();
		System.setProperty("webdriver.chrome.driver", projectPath + "/src/test/resources/Drivers/chromedriver");
		driver = new ChromeDriver(options);
		// Maximize the window
		driver.manage().window().maximize();
		// Implicit wait until elements are found
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

	}

	@After
	public void teardown() {
		driver.close();
	}

	@Given("I have all car registration numbers from the text file")
	public void i_have_all_car_registration_numbers_from_the_text_file() {

		// Read car registration numbers from the input file
		try {
			regNumbers = Utilities.readCarRegsFromText(projectPath + "/src/test/resources/Files/car_input - V6.txt");
			System.out.println("Registration Numbers: " + regNumbers);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@When("I perform car details search for each car registration number and compare details with output file")
	public void i_perform_car_details_search_for_each_car_registration_number_and_compare_details_with_output_file() throws InterruptedException, IOException {

		CarDetailsSearchPage carValuationSearchPage = new CarDetailsSearchPage(driver);

		for (String reg : regNumbers) {
			driver.get("https://car-checking.com/");
			System.out.println("Checking registration: " + reg);			
			try {
				carValuationSearchPage.enterRegNumber(reg);
			} catch (Exception e) {
				driver.navigate().refresh();
				carValuationSearchPage.enterRegNumber(reg);
			}
			carValuationSearchPage.clickSearch();

			if (carValuationSearchPage.isVehicleDetailsVisible()) {
				String make = carValuationSearchPage.getVehicleMake();
				String model = carValuationSearchPage.getVehicleModel();
				String year = carValuationSearchPage.getVehicleYear();

				Map<String, String[]> expectedDetails = Utilities
						.readExpectedOutput(projectPath + "/src/test/resources/Files/car_output - V6.txt");

				// Compare extracted details with expected output
				String[] expected = expectedDetails.get(reg);
				if (expected != null) {
					Utilities.compareDetails(reg, make, model, year, expected);

				} else {
					System.out.println("No expected details found for registration: " + reg);
				}

			} else
				System.out.println("Registration Number not found: " + reg);
			}
		}

}
