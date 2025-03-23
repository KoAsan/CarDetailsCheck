package pages;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarDetailsSearchPage {
	WebDriver driver;
	private WebDriverWait wait;

	public CarDetailsSearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@FindBy(id = "subForm1")
	private WebElement searchRegField;

	@FindBy(xpath = "//button[contains(text(),'Check Now')]")
	private WebElement btnCheckNow;

	@FindBy(xpath = "//td[contains(text(),'Make')]/following::td[1]")
	private WebElement vehicleMake;

	@FindBy(xpath = "//td[contains(text(),'Model')]/following::td[1]")
	private WebElement vehicleModel;

	@FindBy(xpath = "//td[contains(text(),'Year of manufacture')]/following::td[1]")
	private WebElement vehicleYear;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	private WebElement regNotFoundAlert;

	private void waitForElementToBeVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public boolean isNotFoundMessageVisible() {
		try {
			waitForElementToBeVisible(regNotFoundAlert);
			return regNotFoundAlert.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void enterRegNumber(String regNumber) {
		waitForElementToBeVisible(searchRegField);
		searchRegField.clear();
		searchRegField.sendKeys(regNumber);
	}

	public boolean isVehicleDetailsVisible() {
		try {
			waitForElementToBeVisible(vehicleMake);
			return vehicleMake.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void clickSearch() {
		this.btnCheckNow.click();
	}

	public String getVehicleMake() {
		return this.vehicleMake.getText();
	}

	public String getVehicleModel() {
		return this.vehicleModel.getText();
	}

	public String getVehicleYear() {
		return this.vehicleYear.getText();
	}
}
