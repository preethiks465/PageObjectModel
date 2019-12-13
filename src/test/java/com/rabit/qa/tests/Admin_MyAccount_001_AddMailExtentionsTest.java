package com.rabit.qa.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rabit.qa.base.TestBase;
import com.rabit.qa.pages.AdminPage;
import com.rabit.qa.pages.HomePage;
import com.rabit.qa.pages.LoginPage;
import com.rabit.qa.pages.MyAccountPage;
import com.rabit.qa.util.TestUtil;

import extentManager.CopyFile;

public class Admin_MyAccount_001_AddMailExtentionsTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	AdminPage adminpage;
	MyAccountPage myaccountpage;
	TestUtil testutil;
	String SheetName = "MyAccountDetails";
	List<String> ColumnDetails = new ArrayList<String>();

	public Admin_MyAccount_001_AddMailExtentionsTest() {
		super();
	}

	@BeforeClass
	public void ReportStart() {

		test = rep.startTest("Admin_MyAccount_001_AddMailExtentionsTest");// Start this test
	}

	@BeforeMethod
	public void Setup() {
		Initialization();
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
		adminpage = homepage.clickOnAdminMenu();
		testutil = new TestUtil();
	}

	@Test(priority = 1)
	public void verifyMyAccountDetailsTest() {
		myaccountpage = adminpage.clickOnMyAccountLink();
		Assert.assertTrue(myaccountpage.verifyMyAccountDetails());
	}

	@Test(priority = 2, dataProvider = "ARData")
	public void AddMailExtentionsTest(String ExtnName, String ExtnDesc, String SaveMsg) {
		myaccountpage = adminpage.clickOnMyAccountLink();
		myaccountpage.AddMailExtensions(ExtnName, ExtnDesc);
		Assert.assertTrue(testutil.verifyData(SaveMsg));
		testutil.ClickonOKButton();
	}

	@AfterMethod
	public void teardown() {
		if (rep != null) {
			rep.endTest(test);
			rep.flush();
			// The above two lines are compulsory else reports will not be generated
		}
		CopyFile.copyFileFromReports();
		driver.quit();
	}

	@DataProvider(name = "ARData")
	public Object[][] getARTestData() {
		ColumnDetails.add("ExtensionName");
		ColumnDetails.add("ExtensionDesc");
		ColumnDetails.add("NotificationMsg");
		return testutil.getdata3(SheetName, ColumnDetails);
	}

	@AfterClass
	public void copyFile() {
		if (rep != null) {
			rep.endTest(test);
			rep.flush();
			// The above two lines are compulsory else reports will not be generated
		}
		CopyFile.copyFileFromReports();
	}
}
