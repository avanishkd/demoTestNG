
package executionClasses;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pageObjects.FlipkartLoginPage;
import setUp.BaseClass;
import utility.CSVUtility;
import utility.ExcelUtility;
import utility.WebDriverhelper;

public class FlipkartLogin extends BaseClass {
	FlipkartLoginPage flipkartLoginPageObject;
	
	HashMap<String, String> dict;
	ExcelUtility excelRead;
	@BeforeClass
	public void checkBeforeClass(){
		System.out.println("before class");
	}
	
	@AfterClass
	public void checkAfterClass(){
		System.out.println("after class");
	}
	@Test
	public void demo() throws Exception{
		
		logger = extent.startTest("FlipkartTest");
		
		
		dict=new HashMap<String,String>();
		dict=testData.loadTestData(this.getClass().getPackage().getName(), this.getClass().getSimpleName());
		flipkartLoginPageObject = new FlipkartLoginPage();
		logger.log(LogStatus.PASS, "launched flipkart URL");
		
		String screenshotPath = WebDriverhelper.getScreenshot(driver, "abc");
		//To add it in the extent report 
		logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
		flipkartLoginPageObject.fromHometoLoginPage(dict.get("applicationURL"));
		logger.log(LogStatus.PASS, "Navigated to home page");
		flipkartLoginPageObject.login(dict.get("UserId"),dict.get("Password"));
		
		logger.log(LogStatus.PASS, "Test Case (Pass) Status is passed");
		/*excelRead=new ExcelUtility("D:\\Java\\DemotestNG\\DemotestNG\\ReadExcel.xlsx");
		System.out.println(excelRead.getRowCount());
		 CSVUtility writeToCSV=new CSVUtility("number,message");
		 writeToCSV.closeCSVUtility();*/
		
		
	}
	
}
