package com.rabit.qa.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rabit.qa.base.TestBase;

public class LoginPage extends TestBase {
	@FindBy(xpath = "//input[@id='UserEmail']")
	WebElement userName;

	@FindBy(xpath = "//input[@id='UserPassword']")
	WebElement password;

	@FindBy(xpath = "//*[@id='logincheck']")
	WebElement loginButton;

	public LoginPage() {
		super();
		PageFactory.initElements(driver, this);

	}

	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public HomePage login(String un, String pwd) {
		userName.sendKeys(un);
		password.sendKeys(pwd);
		loginButton.click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new HomePage();
	}
}
