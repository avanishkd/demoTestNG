package executionClasses;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import setUp.BaseClass;

public class Register extends BaseClass {
	HomePage homePageObject;
	RegisterPage registerPageObject;
	HashMap<String, String> dict;
	String userName="abc@yopmail.com";
	
	@Test
	public void demo() throws Exception{
		SecureRandom random = new SecureRandom();
		String randStr= new BigInteger(64, random).toString(16);
		userName=randStr+userName;
		dict=new HashMap<String,String>();
		dict=testData.loadTestData(this.getClass().getPackage().getName(), this.getClass().getSimpleName());
		homePageObject = new HomePage();
		registerPageObject = new RegisterPage();
		
		homePageObject.fromHometoRegistrationPage(dict.get("applicationURL"));
		registerPageObject.register(userName, dict.get("Password"));
		homePageObject.logout();
		
	}
	
}
