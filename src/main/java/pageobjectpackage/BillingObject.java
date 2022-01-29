package pageobjectpackage;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import mainpackage.MainClass;
import reusablepackage.ReusableFunction;

public class BillingObject {
	
	WebDriver driver;
	ExtentTest logger;
	ReusableFunction ReusFun=new ReusableFunction(driver, logger);
	
	@FindBy(xpath = "(//a[contains(text(), 'CUSTOMER')])[4]")
	WebElement pop_up_customer;
	
	@FindBy(xpath = "//button[@id='btnPayCustomerClose']")
	WebElement btn_pop_up_close;
	
	@FindBy(xpath = "//th[text()='HOLD ID ']")
	WebElement txt_table_hold_id;
	
	@FindBy(xpath = "(//table[@id='tblHoldList']/tbody/tr/td)[1]")
	WebElement table_bill_hold_id;
	
	@FindBy(xpath = "//h6[@id='lblTotalAmt']")
	WebElement value_net_amount;
	
	@FindBy(xpath = "//a[@id='btnProductHold']")
	WebElement btn_hold;
	
	@FindBy(xpath = "//a[@id='mobile-collapse']")
	WebElement btn_menu;
	
	@FindBy(xpath = "//span[text()='Dashboard']")
	WebElement txt_dashboard;
	
	@FindBy(xpath = "//li//a//span[text()='OPERATION']")
	WebElement btn_operation;
	
	@FindBy(xpath = "//li//a//span[text()='REPRINT']")
	WebElement btn_reprint;
	
	@FindBy(xpath = "//li//a//span[text()='BILL CANCEL']")
	WebElement btn_billCancel;
	
	@FindBy(xpath = "//input[@id='txtItemSearch']")
	WebElement txt_box_itemSearch;
	
	@FindBy(xpath = "//a[@id='btnProductCancel']")
	WebElement btn_Cancel;
	
	@FindBy(xpath = "//tr//td[text()='1']")
	WebElement txt_first_element_in_table;
	
	@FindBy(xpath = "//h2[text()='CANCEL BILL']")
	WebElement txt_cancel_popup;
	
	@FindBy(xpath = "//textarea[@id='txtaRemarks']")
	WebElement txt_box_remark;
	
	@FindBy(xpath = "//button[@id='btnPOS']")
	WebElement btn_popup_ok;
	
	@FindBy(xpath = "(//a[@class='waves-effect waves-light'])[3]")
	WebElement btn_store_admin;

	@FindBy(xpath = "//li[@id='mnuLogout']")
	WebElement list_logout;
	
	@FindBy(xpath = "//table[@id='tblVendorListHeader']")
	WebElement tbl_reprint;
	
	@FindBy(xpath = "//input[@id='txtsMobile']")
	WebElement text_box_reprint_bill_no;
	
	@FindBy(xpath = "//a[@id='btnPayment']")
	WebElement btn_reprintbill;
	
	@FindBy(xpath = "//button[@id='btnPOS']")
	WebElement btn_print;
	
	@FindBy(xpath = "//span[@id='lblBillNo']")
	WebElement bill_number;
	
	public BillingObject(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}
	
	public void bill_Retrieve() 
			throws Exception{
		
		/****** This method is used to hold & Retrieve the bill in the middle **********/
		
		WebDriverWait wait = new WebDriverWait(driver,60);
		String net_Amount = value_net_amount.getText();
		//System.out.println("Net Amount: "+net_Amount);
		btn_hold.click();
		Thread.sleep(2000);
		driver.switchTo().alert();
		Thread.sleep(3000);
		//Bill Hold Successfully.
		String screenshotPath = MainClass.getScreenshot(driver, "Bill Hold popup.");
		logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
		String actual_Alert_text = driver.switchTo().alert().getText();
		System.out.println(actual_Alert_text);
		driver.switchTo().alert().accept();
		Payment pay=new Payment(driver,logger);
		pay.mob_pop_close();
		/*
		try {
		wait.until(ExpectedConditions.visibilityOf(btn_pop_up_close));
		if(btn_pop_up_close.isDisplayed())
		{
			btn_pop_up_close.click(); */
		try {
			
			wait.until(ExpectedConditions.visibilityOf(btn_hold));
			btn_hold.click();
			 screenshotPath = MainClass.getScreenshot(driver, "Bill Hold Successfully.");
			logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
			wait.until(ExpectedConditions.visibilityOf(txt_table_hold_id));
				if(txt_table_hold_id.isDisplayed()) {
					table_bill_hold_id.click();
					wait.until(ExpectedConditions.visibilityOf(btn_hold));
					}
				else {
					throw new SkipException("Hold Table is not poping up");
				}
		/*}
		
		else {
			//need to throw error no popup appears for new customer
			throw new SkipException("Unable to hold the bill");
		}*/
		}
		catch(Exception e)
		{
			System.out.println("Billing part failed");
		}
		 screenshotPath = MainClass.getScreenshot(driver, "Bill amount after hold");
			logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
		String net_Amount_after_hold = value_net_amount.getText();
		System.out.println("After hold: "+net_Amount_after_hold);
		Assert.assertEquals(net_Amount, net_Amount_after_hold);
	}
	
	public void bill_cancel(String billNumber) 
			throws Exception{
		
		/****** This method is used to Cancel the bill **********/
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		btn_menu.click();
		wait.until(ExpectedConditions.visibilityOf(txt_dashboard));
		btn_operation.click();
		wait.until(ExpectedConditions.visibilityOf(btn_billCancel));
		btn_billCancel.click();
		wait.until(ExpectedConditions.visibilityOf(btn_Cancel));
		txt_box_itemSearch.sendKeys(billNumber);
		Thread.sleep(2000);
		txt_box_itemSearch.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.visibilityOf(txt_first_element_in_table));
		btn_Cancel.click();
		wait.until(ExpectedConditions.visibilityOf(txt_cancel_popup));
		txt_box_remark.click();
		txt_box_remark.sendKeys("Testing");
		btn_popup_ok.click();
		Thread.sleep(3000);
		try{
		    driver.switchTo().alert();
		    // If it reaches here, it found a popup
		    
		  String expected_alert_text = "Bill Cancelled Successfully.";
			String actual_Alert_text = driver.switchTo().alert().getText(); 
			System.out.println(actual_Alert_text);
			Assert.assertEquals(expected_alert_text, actual_Alert_text);
		    
		} catch(NoAlertPresentException e){}
		driver.switchTo().alert().accept();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(btn_Cancel));
		btn_store_admin.click();
		list_logout.click();
	}
	
	public void bill_reprint(String billNumber)
	throws Exception{
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		btn_menu.click();
		wait.until(ExpectedConditions.visibilityOf(txt_dashboard));
		btn_operation.click();
		wait.until(ExpectedConditions.visibilityOf(btn_billCancel));
		btn_reprint.click();
		wait.until(ExpectedConditions.visibilityOf(tbl_reprint));
		Thread.sleep(2000);
		text_box_reprint_bill_no.sendKeys(billNumber);
		Thread.sleep(2000);
		WebElement bill_id = driver.findElement(By.xpath("//tr//td[text()='"+billNumber+"']"));
		wait.until(ExpectedConditions.visibilityOf(bill_id));
		bill_id.click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(btn_reprintbill));
		btn_reprintbill.click();
		wait.until(ExpectedConditions.visibilityOf(btn_print));
		btn_print.click();
		Thread.sleep(5000);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		String reprint_billNumber = bill_number.getText();
		System.out.println(reprint_billNumber);
		driver.close();
		driver.switchTo().window(tabs2.get(0));
		
		Assert.assertEquals(billNumber, reprint_billNumber);
		wait.until(ExpectedConditions.visibilityOf(pop_up_customer));
		btn_pop_up_close.click();
		Thread.sleep(2000);
		btn_store_admin.click();
		list_logout.click();
		
		
	}

}
