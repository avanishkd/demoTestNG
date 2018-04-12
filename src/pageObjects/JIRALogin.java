
package pageObjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utility.WebDriverhelper;

public class JIRALogin extends WebDriverhelper{
	
	@FindBy (id="username")
	private WebElement loginUserNameEdit;	
	
	@FindBy (xpath="//*[@type='submit']")
	private WebElement continueButton;
	
	@FindBy (id="password")
	private WebElement passwordEdit;
	public JIRALogin() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean launchJIRA(String homePageURL){
		driver.get("https://kalinga.atlassian.net/");
		return true;
	}
	public boolean fromHometoLoginPage(String homePageURL){
		Assert.assertTrue(navigate(homePageURL));
//		Assert.assertTrue(clickElement(homePageLoginLink));
		return true;
	}
	
	/*public boolean clickLoginButton(){
		
		Assert.assertTrue(clickElement(LoginButton));
		return true;
	}
	
	public boolean enterLoginUsername(String userName){
		
		Assert.assertTrue(sendText(userNameEdit, userName));
		return true;
	}
	*/
	public boolean enterLoginPassword(String password){
		
		Assert.assertTrue(sendText(passwordEdit, password));
		return true;
	}
	
	public boolean login(String userName,String password){
		
//		Assert.assertTrue(enterLoginUsername(userName));
		Assert.assertTrue(enterLoginPassword(password));
		return true;
	}
}
