package executionClasses;

import java.util.HashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import setUp.BaseClass;

public class Login extends BaseClass {
	HomePage homePageObject;
	LoginPage loginPageObject;
	HashMap<String, String> dict;
	
	@Test
	public void demo() throws Exception{
		dict=new HashMap<String,String>();
		dict=testData.loadTestData(this.getClass().getPackage().getName(), this.getClass().getSimpleName());
		homePageObject = new HomePage();
		loginPageObject = new LoginPage();
		
		homePageObject.fromHometoLoginPage(dict.get("applicationURL"));
		loginPageObject.login(dict.get("UserId"),dict.get("Password"));
		homePageObject.logout();
	}
	
}
