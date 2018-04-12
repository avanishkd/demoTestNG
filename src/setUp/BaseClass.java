package setUp;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utility.ExcelUtility;

public class BaseClass {
	protected ExtentReports extent;
	protected ExtentTest logger;
	
	public static WebDriver driver ;
	public static ExcelUtility testData;
	@BeforeMethod
	public void OpenBrowser(){
		System.setProperty("webdriver.chrome.driver","D:\\Tools\\SeleniumUtil\\chromedriver_win32\\chromedriver.exe");
		testData=new ExcelUtility(System.getProperty("user.dir")+"\\testData.xls");
		driver = new ChromeDriver();
//		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
//		driver.get("https://www.flipkart.com/");
//		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/myExtentReport.html", true);
		//extent.addSystemInfo("Environment","Environment Name")
		extent
                .addSystemInfo("Host Name", "A2ML17907")
                .addSystemInfo("Environment", "UAT")
                .addSystemInfo("User Name", "Avanish");
                //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
                //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
                extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	}
	
	
	public void getResult(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
			logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
		}else if(result.getStatus() == ITestResult.SKIP){
			logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
		}
		// ending test
		//endTest(logger) : It ends the current test and prepares to create HTML report
		extent.endTest(logger);
	}
	@AfterMethod
	public void clearBrowser(ITestResult result){
		getResult( result);
		driver.quit();
		extent.flush();
		extent.close();
	}
}
