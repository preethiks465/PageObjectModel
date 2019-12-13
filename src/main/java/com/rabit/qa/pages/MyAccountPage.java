package com.rabit.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rabit.qa.base.TestBase;

public class MyAccountPage extends TestBase {

	@FindBy(xpath = "//h1[contains(text(),'Account Contact Details')]")
	WebElement adminMyAccountText;

	@FindBy(xpath = "//div[@id='addMailExtn']")
	WebElement adminAddExtentionButton;

	@FindBy(xpath = "(//input[@name='extnName'])[last()]")
	WebElement adminMailExtentionName;

	@FindBy(xpath = "(//input[@name='extnDesc'])[last()]")
	WebElement adminMailExtentionDescription;

	@FindBy(xpath = "//div[@id='saveMailExtensions']")
	WebElement adminSaveExtentionButton;

	public MyAccountPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyMyAccountDetails() {
		return adminMyAccountText.isDisplayed();
	}

	public void AddMailExtensions(String ExtnName, String ExtnDesc) {

		adminAddExtentionButton.click();
		adminMailExtentionName.sendKeys(ExtnName);
		adminMailExtentionDescription.sendKeys(ExtnDesc);
		adminSaveExtentionButton.click();

	}
}
