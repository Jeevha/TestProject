package smokeflow;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mainpackage.MainClass;
import pageobjectpackage.ItemSelection;
import pageobjectpackage.LoginObjects;
import pageobjectpackage.NewCustomer;

public class ItemMoreQOH extends MainClass {
	
	@Test(dataProvider="LoginData", description = "CashWrap_Login", priority = 1, enabled = true)
	public void Login(String UserName,String Password) {
		logger = extent.startTest("Login");
		LoginObjects obj=new LoginObjects(driver,logger);
		obj.login(UserName,Password);
	}
	
	@Test(dataProvider="CustomerData", description = "NewCustomer", priority = 2, enabled = true)
	public void New_Cust_Creation(String PhoneNum,String Name,String Email) throws Exception {
		logger = extent.startTest("New Customer Creation");
		NewCustomer obj=new NewCustomer(driver,logger);
		obj.Customer_creation(PhoneNum,Name,Email);
		ItemSelection itemobj=new ItemSelection(driver,logger);
		itemobj.click_view();
	}
	
	@Test(dataProvider="ItemData", description = "Item_Selection_over_QOH_while_adding", priority = 3, enabled = true)
	public void ItemSelection(String ItemNo,String ItemCode,String ItemName,String ItemQty,String ActualAmount) throws Exception {
		logger = extent.startTest("Item_Selection_over_QOH_while_adding");
		ItemSelection itemobj=new ItemSelection(driver,logger);
		itemobj.item_Select_overQOH1(ItemCode);
	}
	
	@Test(dataProvider="ItemData", description = "Item_Selection_over_QOH_while_adding_in_table", priority = 4, enabled = true)
	public void ItemSelection2(String ItemNo,String ItemCode,String ItemName,String ItemQty,String ActualAmount) throws Exception {
		logger = extent.startTest("Item_Selection_over_QOH_while_adding_in_table");
		ItemSelection itemobj=new ItemSelection(driver,logger);
		itemobj.item_Select_overQOH2(ItemCode);
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
