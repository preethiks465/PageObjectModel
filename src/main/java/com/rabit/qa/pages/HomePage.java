package com.rabit.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rabit.qa.base.TestBase;

public class HomePage extends TestBase {
	@CacheLookup
	@FindBy(xpath = "//div[@data-name='VersionControl']")
	WebElement versionControlMenu;
	@FindBy(xpath = "//div[@data-name='CIJobs']")
	WebElement CIJobs;
	@FindBy(xpath = "//div[@data-name='Deployment']")
	WebElement deploymentMenu;
	@FindBy(xpath = "//div[@data-name='Dataloader']")
	WebElement dataLoaderMenu;
	@FindBy(xpath = "//div[@data-name='TAF']")
	WebElement TAFMenu;
	@FindBy(xpath = "//div[@data-name='SFDX']")
	WebElement SFDX;
	@FindBy(xpath = "//div[@data-name='Reports']")
	WebElement reportsMenu;
	@FindBy(xpath = "//div[@data-name='EnvProvisioning']")
	WebElement environmentProvisoningMenu;
	@FindBy(xpath = "//div[@data-name='Admin']")
	WebElement adminMenu;
	@FindBy(xpath = "//div[@id='Index_ProfileName' and text()='Sreekanth Bandi']")
	WebElement UserNameLabel;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public boolean verifyCorrectUserName() {
		return UserNameLabel.isDisplayed();

	}

	public AdminPage clickOnAdminMenu() {
		adminMenu.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new AdminPage();
	}

	public VersionControlPage clickOnVerionControlMenu() {
		versionControlMenu.click();
		return new VersionControlPage();
	}

	public CIJobPage clickOnCIJobMenu() {
		CIJobs.click();
		return new CIJobPage();
	}
}
