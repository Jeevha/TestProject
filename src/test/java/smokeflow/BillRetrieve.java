package smokeflow;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mainpackage.MainClass;
import pageobjectpackage.BillingObject;
import pageobjectpackage.ItemSelection;
import pageobjectpackage.LoginObjects;
import pageobjectpackage.NewCustomer;

public class BillRetrieve extends MainClass {
	
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
	
	@Test(description = "Bill_Retrieve", priority = 4, enabled = true)
	public void BillRetrieve() throws Exception {
		logger = extent.startTest("BillRetrieve");
		BillingObject itemobj=new BillingObject(driver,logger);
		itemobj.bill_Retrieve();
		
	}
	
	@DataProvider(name="TotalData")
	public Object[][] TotalData(){Object[][] data = getExcelData("TestData.xlsx", "Total");
		return data;
	}

	
	@DataProvider(name="LoginData")
	public Object[][] sampledata(){Object[][] data = getExcelData("TestData.xlsx", "Login");
		return data;
	}
	
	@DataProvider(name="CustomerData")
	public Object[][] CustomerData(){Object[][] data = getExcelData("TestData.xlsx", "Customer");
		return data;
	}
	
	@DataProvider(name="ItemData")
	public Object[][] ItemData(){Object[][] data = getExcelData("TestData.xlsx", "Item");
		return data;
	}

}
