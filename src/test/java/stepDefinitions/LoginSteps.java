package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class LoginSteps extends BaseClass {
	
	@Before
	public void setUp() {
		logger = Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("log4j.properties");
		configProp = new Properties();
		FileInputStream configPropFile;
		try {
			configPropFile = new FileInputStream("config.properties");
			configProp.load(configPropFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String browser = configProp.getProperty("browser");
		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			System.out.println("Invalid browser name");
		}
		driver.manage().window().maximize();
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@Given("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("*****Opening URL*****");
		driver.get(url);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("*****Entering email and password*****");
		loginPage.enterUsername(email);
		loginPage.enterPassword(password);
	}
	
	@And("Clicks on LOG IN")
	public void clicks_on_log_in() {
		logger.info("*****Clicking login*****");
		loginPage.clickLogin();
	}

	@Then("Page title should be {string}")
	public void page_title_should_be(String expectedPageTitle) {
		String actualPageTitle = driver.getTitle();
		Assert.assertEquals(expectedPageTitle, actualPageTitle);
	}

	@When("user clicks on Logout")
	public void user_clicks_on_logout() {
		logger.info("*****Clicking logout*****");
		homePage.clickLogout();
	}

	@After
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
}
