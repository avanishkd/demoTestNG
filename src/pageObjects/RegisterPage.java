
package pageObjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utility.WebDriverhelper;

public class RegisterPage extends WebDriverhelper{
	@FindBy (id="register_email")
	private WebElement registerEmail;	
	@FindBy (id="register_password")
	private WebElement passwordEdit;
	
	@FindBy (id="register_pwconfirm")
	private WebElement confirmPasswordEdit;

	@FindBy (id="register_submit")
	private WebElement submitButton;
	
	public RegisterPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean register(String userName, String Password){
		Assert.assertTrue(sendText(registerEmail,userName));
		Assert.assertTrue(sendText(passwordEdit,Password));
		Assert.assertTrue(sendText(confirmPasswordEdit,Password));
		Assert.assertTrue(clickElement(submitButton));
		return true;
	}
	
	
}
