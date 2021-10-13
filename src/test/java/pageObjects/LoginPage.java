package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		
		this.driver=driver;
		PageFactory.initElements(driver, this);		
		
	}
	
	
	@FindBy(id="Email")
	@CacheLookup
	WebElement txtEmailId;
	
	@FindBy(id="Password")
	@CacheLookup
	WebElement txtPassword;
	
	@FindBy(xpath="//button[text()='Log in']")
	WebElement btnLogin;
	
	
	/*@FindBy(xpath="//a[text()='Logout'")
	WebElement lnkLogout;*/
	
	@FindBy(linkText="Logout")
	WebElement lnkLogout;
	
	public void setUserEmailId(String email)
	
	{
		
		txtEmailId.clear();
		txtEmailId.sendKeys(email);
		
	}
	
	public void setUserPwd(String upwd)
	{
		txtPassword.clear();
		txtPassword.sendKeys(upwd);
		
		
	}
	
	
	public void loginButton()
	{
		
		btnLogin.click();
		
	}
	
	public void logoutLnk()
	{
		
		lnkLogout.click();
		
	}

}
