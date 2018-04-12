
package pageObjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utility.WebDriverhelper;

public class LoginPage extends WebDriverhelper{
	
	@FindBy (id="login_form_submit")
	private WebElement LoginButton;	
	@FindBy (id="login_email_input")
	private WebElement UserNameEdit;
	
	@FindBy (xpath="//input[@type='password']")
	private WebElement passwordEdit;

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean login(String userName, String Password){
		Assert.assertTrue(sendText(UserNameEdit,userName));
		Assert.assertTrue(sendText(passwordEdit,Password));
		Assert.assertTrue(clickElement(LoginButton));
		return true;
	}
	
	
}
