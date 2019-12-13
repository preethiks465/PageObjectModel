package com.rabit.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rabit.qa.base.TestBase;

public class AdminPage extends TestBase {
	@FindBy(xpath = "//label[text()='My Account']")
	WebElement adminMyAccountLink;

	public AdminPage() {
		PageFactory.initElements(driver, this);
	}

	public MyAccountPage clickOnMyAccountLink() {
		adminMyAccountLink.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new MyAccountPage();
	}

}
