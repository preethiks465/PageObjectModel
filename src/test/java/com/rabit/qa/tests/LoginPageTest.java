package com.rabit.qa.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.rabit.qa.base.TestBase;
import com.rabit.qa.pages.HomePage;
import com.rabit.qa.pages.LoginPage;
import com.relevantcodes.extentreports.LogStatus;

import extentManager.CopyFile;

public class LoginPageTest extends TestBase {
	LoginPage loginpage;
	HomePage homepage;

	public LoginPageTest() {
		super();
	}

	@BeforeClass
	public void ReportStart() {

		test = rep.startTest("LoginPageTest");// Start this test
	}

	@BeforeMethod
	public void setup() {
		Initialization();
		loginpage = new LoginPage();
	}

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String loginPageTitle = loginpage.validateLoginPageTitle();
		Assert.assertEquals(loginPageTitle, "AutoRABIT Login");
	}

	@Test(priority = 2)
	public void loginTest() {
		homepage = loginpage.login(prop.getProperty("username"), prop.getProperty("password"));
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
