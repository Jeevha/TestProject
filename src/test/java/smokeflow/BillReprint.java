package smokeflow;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mainpackage.MainClass;
import pageobjectpackage.BillingObject;
import pageobjectpackage.ItemSelection;
import pageobjectpackage.LoginObjects;
import pageobjectpackage.Payment;

public class BillReprint extends MainClass{
	
String billNumber;
	
	@Test(dataProvider="LoginData", description = "CashWrap_Login", priority = 1, enabled = true)
	public void Login(String UserName,String Password) {
		logger = extent.startTest("Login");
		LoginObjects obj=new LoginObjects(driver,logger);
		obj.login(UserName,Password);
	}
	
	@Test(dataProvider="CustomerData", description = "Customer_Selection", priority = 2, enabled = true)
	public void Customer_Selection(String PhoneNum,String Name,String Email) throws Exception {
		logger = extent.startTest("CustomerSelection");
		LoginObjects obj=new LoginObjects(driver,logger);
		obj.cust_Select(PhoneNum);
		ItemSelection itemobj=new ItemSelection(driver,logger);
		itemobj.click_view();
	}
	
	@Test(dataProvider="ItemData", description = "Item_Selection", priority = 3, enabled = true)
	public void ItemSelection(String ItemNo,String ItemCode,String ItemName,String ItemQty,String ActualAmount) throws Exception {
		logger = extent.startTest("ItemSelection");
		ItemSelection itemobj=new ItemSelection(driver,logger);
		itemobj.item_Select_3(ItemNo,ItemCode,ItemName,ItemQty,ActualAmount);
		
	}
	

	@Test(dataProvider="PaymentData",description = "Payment_cash_card", priority = 4, enabled = true)
	public void PaymentType(String CardAmount) throws Exception {
		logger = extent.startTest("PaymentType");
		Payment pay=new Payment(driver,logger);
		billNumber = pay.pay(CardAmount);
	} 
	
	@Test(description = "Bill_Reprint", priority = 5, enabled = true)
	public void BillReprint() throws Exception {
		logger = extent.startTest("BillCancel");
		BillingObject itemobj=new BillingObject(driver,logger);
		itemobj.bill_reprint(billNumber);
	} 
	
	@DataProvider(name="LoginData")
	public Object[][] sampledata(){Object[][] data = getExcelData("TestData.xlsx", "Login");
		return data;
	}
	
	@DataProvider(name="CustomerData")
	public Object[][] CustomerData(){Object[][] data = getExcelData("TestData.xlsx", "Customer");
		return data;
	}
	
	@DataProvider(name="PaymentData")
	public Object[][] PaymentData(){Object[][] data = getExcelData("TestData.xlsx", "Payment");
		return data;
	}
	
	@DataProvider(name="ItemData")
	public Object[][] ItemData(){Object[][] data = getExcelData("TestData.xlsx", "Item");
		return data;
	}

}
