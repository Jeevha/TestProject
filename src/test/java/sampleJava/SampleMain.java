package sampleJava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SampleMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://cashwrap.expertsoftsys.com/");
		ObjectRep obj=new ObjectRep();
         driver.findElement(obj.inp_txtbox).click();
         
  }

}
