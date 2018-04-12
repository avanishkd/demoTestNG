
package pageObjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import utility.WebDriverhelper;

public class FlipkartLoginPage extends WebDriverhelper{
	
	@FindBy (linkText="Log In")
	private WebElement homePageLoginLink;	
	
	@FindBy (linkText="Signup")
	private WebElement homePageSignUpLink;
	
	@FindBy (xpath="//div/form[not(boolean(@method))]//*[@type='text' and @autocomplete='off']")
	private WebElement userNameEdit;
	
	@FindBy (xpath="//div/form[not(boolean(@method))]//*[@type='text' and @autocomplete='off']/following::input[@type='password']")
	private WebElement passwordEdit;
	
	@FindBy (xpath="//div/form[not(boolean(@method))]//*[@type='submit']")
	private WebElement LoginButton;
	
	public FlipkartLoginPage() {
		AjaxElementLocatorFactory factory=new AjaxElementLocatorFactory(driver, 100);
		PageFactory.initElements(factory, this);
	}
	
	public boolean fromHometoRegistrationPage(String homePageURL){
		Assert.assertTrue(navigate(homePageURL));
		Assert.assertTrue(clickElement(homePageSignUpLink));
		return true;
	}
	public boolean fromHometoLoginPage(String homePageURL){
		Assert.assertTrue(navigate(homePageURL));
//		Assert.assertTrue(clickElement(homePageLoginLink));
		return true;
	}
	
	public boolean clickLoginButton(){
		
		Assert.assertTrue(clickElement(LoginButton));
		return true;
	}
	
	public boolean enterLoginUsername(String userName){
		
		Assert.assertTrue(sendText(userNameEdit, userName));
		return true;
	}
	
	public boolean enterLoginPassword(String password){
		
		Assert.assertTrue(sendText(passwordEdit, password));
		return true;
	}
	
	public boolean login(String userName,String password){
		
		Assert.assertTrue(enterLoginUsername(userName));
		Assert.assertTrue(enterLoginPassword(password));
		return true;
	}
}
