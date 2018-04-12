

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
public class ChromeDriverTest {
	
	
	public static void main(String[] args) throws MalformedURLException {
		/* This is the remote webdriver object used to interact with remote web browser.*/
		String hubUrl = "http://localhost:5555/wd/hub";
		
		 
		 RemoteWebDriver remoteFirefoxDriver = null;
		 
		 /* dc include desired test web browser's information.*/
		 DesiredCapabilities dc = null;
		 
		 System.out.println("Create Google Chrome DesiredCapabilities object.");
		 /* If user want to test google Chrome. */
		 dc = DesiredCapabilities.chrome();
		 remoteFirefoxDriver = new RemoteWebDriver(new URL(hubUrl), dc);
		 System.out.println("Maxmize the web browser window");
		 /* Maximize the web browser window. */
		 remoteFirefoxDriver.manage().window().maximize();
		 
		 System.out.println("Browse web page " + "https://www.google.co.in/");
		 /* Browse the test Url. */
		 remoteFirefoxDriver.navigate().to("https://www.google.co.in/");
	}
 
}