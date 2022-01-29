package saucepack;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SauceLab {
	public static final String URL="https://oauth-saimakeup123-9ad9b:688d5ee5-13b7-4f07-9abc-ac12a0a29820@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

	public static void main(String[] args) throws MalformedURLException {
		MutableCapabilities caps = new MutableCapabilities();
		caps.setCapability("platformName", "Android");
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("appium:deviceName", "Android GoogleAPI Emulator");
		caps.setCapability("appium:platformVersion", "10.0");
		MutableCapabilities sauceOptions = new MutableCapabilities();
		sauceOptions.setCapability("appiumVersion", "1.20.2");
		caps.setCapability("sauce:options", sauceOptions);
		 WebDriver driver = new RemoteWebDriver(new java.net.URL(URL), caps);
		//driver.get("https://www.guardiandirect.com/");
		driver.get("https://www.facebook.com/");
		//locator
		//x path
		driver.findElement(By.xpath("//input[@name='email']")).click();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys("8870655123");
		driver.findElement(By.xpath("//input[@name='pass']")).click();
		driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("Cheran@5482");
		driver.findElement(By.xpath("//button[@name='login']")).click();

}
}
