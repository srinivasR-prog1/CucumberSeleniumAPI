package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	
	
	@Before
	public void setUp() throws IOException {
		logger = Logger.getLogger("nopCommerce");
		PropertyConfigurator.configure("log4j.properties");		
		
		configProp = new Properties();
		FileInputStream fis = new FileInputStream("config.properties");
		configProp.load(fis);


		String br = configProp.getProperty("browser");

		if (br.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (br.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(br.equals("edge")){
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

		}
		logger.info("************** Launching Browser******************");

	}

	@Given("^User Launch Chrome browser$")
	public void user_Launch_Chrome_browser() throws Throwable {

		
		lp = new LoginPage(driver);
		driver.manage().window().maximize();
	}

	@When("^User opens URL \"([^\"]*)\"$")
	public void user_opens_URL(String url) throws Throwable {
		logger.info("************** Opening URL******************");
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

	}

	@When("^User enters Email as \"([^\"]*)\" and Password as \"([^\"]*)\"$")
	public void user_enters_Email_as_and_Password_as(String email, String password) throws Throwable {
		logger.info("************** Providing Login Details******************");
		lp.setUserEmailId(email);
		lp.setUserPwd(password);
	}

	@When("^Click on Login$")
	public void click_on_Login() throws Throwable {
		logger.info("************** Login Started******************");
		lp.loginButton();
	}

	@Then("^Page Title should be \"([^\"]*)\"$")
	public void page_Title_should_be(String title) throws Throwable {
		if (driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();

			logger.info("************** Login Failed******************");
			Assert.assertTrue(false);

		} else {

			logger.info("************** Login Passed******************");

			Assert.assertEquals(title, getTitle());
		}
	}

	@When("^User click on Logout link$")
	public void user_click_on_Logout_link() throws Throwable {
		logger.info("************** Click on Logout link******************");
		lp.logoutLnk();
		Thread.sleep(3000);
	}

	@Then("close browser")
	public void close_browser() throws Throwable {
		logger.info("************** Closing Browser******************");
	//	driver.close();
	}

	// Customers feature step
	// definition..........................................

	@Then("User can view Dashboard")
	public void user_can_view_Dashboard() {

		addCust = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", getTitle());

	}

	@Then("User click on Customers menu")
	public void user_click_on_Customers_menu() throws InterruptedException {

		Thread.sleep(2000);
		addCust.setClickonCustomersMenu();

	}

	@Then("click on Customers menu item")
	public void click_on_Customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
		addCust.setClickonCustomersMenuItem();

	}

	@Then("click on Add new customer button")
	public void click_on_Add_new_customer_button() throws InterruptedException {
		addCust.setClickonAddNew();
		Thread.sleep(2000);

	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() {

		Assert.assertEquals("Add a new customer / nopCommerce administration", getTitle());

	}

	@When("User enter Customer info")
	public void user_enter_Customer_info() throws InterruptedException {

		logger.info("************** Adding new Customer******************");
		logger.info("************** Providing Customer Details******************");

		String email = randomString() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		addCust.setFirstName("Pavan");
		addCust.setLastName("Khera");
		addCust.setGender("Male");
		// addCust.setCustomerRoles("Registered");
		// Thread.sleep(2000);

	}

	@When("click on Save button")
	public void click_on_Save_button() throws InterruptedException {
		logger.info("**************Saving Customer Data ******************");
		addCust.saveBtn();
		Thread.sleep(2000);

	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {

		Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
				.contains("The new customer has been added successfully."));

	}

	// Search customer by email

	@When("Enter customer Email")
	public void enter_customer_Email() {
		logger.info("************** Searching customer by emailid******************");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {

		searchCust.clickSearch();
		Thread.sleep(3000);

	}

	@Then("User should found Email in the search table")
	public void user_should_found_Email_in_the_search_table() {

		boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);

	}

	// Search Customer by firstname and lastname

	@When("User Enter FirstName")

	public void user_Enter_FirstName() {
		
		logger.info("************** Searching Customer by Name******************");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");

	}

	@When("User Enter LastName")
	public void user_Enter_LastName() {
		searchCust.setLastName("Terces");

	}

	@Then("User should found Name in the search table")
	public void user_should_found_Name_in_the_search_table() {

		boolean status1 = searchCust.searchCustomerFnameandLname("Victoria Terces");
		Assert.assertEquals(true, status1);

	}
	
	@After
	public void tearDown()
	{
		driver.quit();;
		System.out.println("This will run after closing the browser for every Scenario");
		
	}

}
