package googlesearch;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import mainpackage.MainClass;
import pageobjectpackage.GoogleSearchObject;
@Listeners(listenerpackage.ListenerClass.class)

public class GoogleSearchClass extends MainClass{
	//private Logger log = Logger.getLogger(GoogleSearchClass.class);
	@Test(dataProvider="sampledata", description = "TestCheck", priority = 1, enabled = true)
	public void homepage_keysenter(String UserName,String Password) {
		logger = extent.startTest("homepage_keysenter");
		GoogleSearchObject obj=new GoogleSearchObject(driver,logger);
		obj.veryHeader(UserName);
	}
	
	@DataProvider(name="sampledata")
	public Object[][] sampledata(){Object[][] data = getExcelData("TestData.xlsx", "Credentials");
		return data;
	}
		
}
 