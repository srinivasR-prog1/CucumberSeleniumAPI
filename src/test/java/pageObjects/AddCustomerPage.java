package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

	public WebDriver driver;

	public AddCustomerPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//p[contains(text(),'Customers')])[1]")
	@CacheLookup
	WebElement btnCustomer;

	@FindBy(xpath = "//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]")
	@CacheLookup
	WebElement lnkCustomer;

	@FindBy(xpath = "//a[@href='/Admin/Customer/Create']")
	@CacheLookup
	WebElement btnAddNew;

	@FindBy(id = "Email")
	@CacheLookup
	WebElement txtEmail;

	@FindBy(id = "Password")
	@CacheLookup
	WebElement txtPassword;

	@FindBy(id = "FirstName")
	@CacheLookup
	WebElement txtFirstName;

	@FindBy(id = "LastName")
	@CacheLookup
	WebElement txtLastName;

	@FindBy(id = "Gender_Male")
	@CacheLookup
	WebElement radGenderMale;

	@FindBy(id = "Gender_Female")
	@CacheLookup
	WebElement radGenderFemale;

	@FindBy(name = "DateOfBirth")
	@CacheLookup
	WebElement txtDoB;

	By slctRoles = By.xpath("//div[@class='k-widget k-multiselect k-multiselect-clearable k-state-hover']");
	By clsRegister = By.xpath("//span[@title='delete']");
	By lstItemAdministrators = By.xpath("//li[text()='Administrators']");
	By lstItemModerators = By.xpath("//li[text()='Forum Moderators']");
	By lstItemGuests = By.xpath("//li[text()='Guests']");
	By lstItemRegistered = By.xpath("//li[text()='Registered']");
	By lstItemVendors = By.xpath("//li[text()='Vendors']");

	// WebElement slctRoles;

	By drpMgrofVendor = By.id("VendorId");
    By btnSave= By.name("save");

	public void setClickonCustomersMenu() {

		btnCustomer.click();

	}

	public void setClickonCustomersMenuItem() {

		lnkCustomer.click();

	}

	public void setClickonAddNew() {

		btnAddNew.click();

	}

	public void setEmail(String email) {

		txtEmail.clear();
		txtEmail.sendKeys(email);

	}

	public void setPassword(String pwd) {

		txtPassword.clear();
		txtPassword.sendKeys(pwd);

	}

	public void setFirstName(String firstName) {

		txtFirstName.clear();
		txtFirstName.sendKeys(firstName);

	}

	public void setLastName(String lastName) {

		txtLastName.clear();
		txtLastName.sendKeys(lastName);

	}

	public void setGender(String gender) {

		if (gender == "Male") {
			radGenderMale.click();

		} else if (gender == "Female") {
			radGenderFemale.click();

		} else {

			radGenderMale.click();

		}

	}

	public void setCustomerRoles(String role) throws InterruptedException {

		if (role.equals("Guests")) {

			driver.findElement(clsRegister).click();

		}

		driver.findElement(slctRoles).click();

		WebElement listItem;

		Thread.sleep(3000);

		if (role.equals("Administrators")) {

			listItem = driver.findElement(lstItemAdministrators);

		} else if (role.equals("Moderators")) {

			listItem = driver.findElement(lstItemModerators);

		} else if (role.equals("Guests")) {

			listItem = driver.findElement(lstItemGuests);

		} else if (role.equals("Registered")) {

			listItem = driver.findElement(lstItemRegistered);

		} else {

			listItem = driver.findElement(lstItemVendors);

		}

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", listItem);

	}
	
	public void saveBtn()
	{
		
		driver.findElement(btnSave).click();
		
	}

	public void setCustVendors(String value) {

		Select drp = new Select(driver.findElement(drpMgrofVendor));
		drp.selectByVisibleText(value);

	}

}
