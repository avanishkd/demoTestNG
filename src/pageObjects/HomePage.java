package pageObjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utility.WebDriverhelper;

public class HomePage extends WebDriverhelper{
	
	
	
	@FindBy (xpath="//span[@id='header_account_desktop_txt']")
	private WebElement ButtonMyAccount;	
	
	@FindBy (xpath="//a[text()='Se connecter']")
	private WebElement ButtonLogin;
	@FindBy (xpath="//a[text()='Créer un compte']")
	private WebElement ButtonRegistration;
	@FindBy (xpath="//ul[@class='dropdown-menu header_desktop_menu container']/li[4]")
	private WebElement LinkMyModeofRepayment;
	@FindBy (xpath="//ul[@class='dropdown-menu header_desktop_menu container']/li[5]")
	private WebElement LinkLogOut;

	@FindBy (xpath="//span[@id='header_basket_desktop_txt']/span")
	private WebElement TextBasketAmount;
	@FindBy (xpath="(//span[@class='offer_tile_discount'])[1]")
	private WebElement OfferAmountFirstProduct;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean fromHometoRegistrationPage(String homePageURL){
		Assert.assertTrue(navigate(homePageURL));
		Assert.assertTrue(clickElement(ButtonRegistration));
		return true;
	}
	public boolean fromHometoLoginPage(String homePageURL){
		Assert.assertTrue(navigate(homePageURL));
		Assert.assertTrue(clickElement(ButtonLogin));
		return true;
	}
	
	public boolean logout(){
		
		Assert.assertTrue(clickElement(ButtonMyAccount));
		Assert.assertTrue(clickElement(LinkLogOut));
		
		return true;
	}
	
	
	
}
