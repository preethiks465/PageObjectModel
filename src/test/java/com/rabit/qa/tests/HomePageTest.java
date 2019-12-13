package com.rabit.qa.tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rabit.qa.base.TestBase;
import com.rabit.qa.pages.AdminPage;
import com.rabit.qa.pages.HomePage;
import com.rabit.qa.pages.LoginPage;

import extentManager.CopyFile;

public class HomePageTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;
	AdminPage adminpage;

	public HomePageTest() {
		super();
	}

	@BeforeClass
	public void ReportStart() {

		test = rep.startTest("HomePageTest");// Start this test
	}

	@BeforeMethod
	public void Setup() {
		Initialization();
		loginpage = new LoginPage();
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void verifyHomePageTitleTest() {
		String HomePageTitle = homepage.verifyHomePageTitle();
		Assert.assertEquals(HomePageTitle, "AutoRABIT", "HomePage Title Not Matched");
	}

	@Test(priority = 2)
	public void verifyCorrectUserName() {
		Assert.assertTrue(homepage.verifyCorrectUserName());
	}

	@Test(priority = 3)
	public void verifyAdminMenuClickTest() {
		adminpage = homepage.clickOnAdminMenu();
	}

	@AfterMethod
	public void teardown() {
		driver.quit();
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
