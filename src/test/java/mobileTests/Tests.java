package mobileTests;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pom.ChooseCoursesPOM;
import pom.ChooseGradePOM;
import pom.HomePagePOM;
import pom.InitailMessgePOM;
import pom.MobileButtonsPOM;
import pom.SettingHomePOM;
import pom.SignInPOM;
import pom.SignUpPOM;
import pom.SignUpOptionsPOM;
import requiredCapabilities.capabilities;
import resource.Methods;
import resource.dataUtil;

public class Tests extends capabilities {
	Methods method = new Methods();
	InitailMessgePOM startMessage;
	HomePagePOM homepage;
	SignUpOptionsPOM signupOptions;
	SignUpPOM signup;
	SettingHomePOM setting;
	MobileButtonsPOM mobileButtons;
	ChooseCoursesPOM chooseCourse;
	ChooseGradePOM grade;
	SignInPOM signIn;
	Hashtable<String, String> data;

	@BeforeTest
	public void beforeTest() {

		// Runtime.getRuntime().exec("taskkill /f /im node.exe");
		// method.wait(5);
		// AppiumDriverLocalService Service = Methods.startServer();

		driver = Methods.initiateDriver();
		startMessage = new InitailMessgePOM(driver);
		homepage = new HomePagePOM(driver);
		signupOptions = new SignUpOptionsPOM(driver);
		signup = new SignUpPOM(driver);
		setting = new SettingHomePOM(driver);
		mobileButtons = new MobileButtonsPOM(driver);
		chooseCourse = new ChooseCoursesPOM(driver);
		grade = new ChooseGradePOM(driver);
		signIn = new SignInPOM(driver);
		method.wait(10);

	}

	@Test(testName = "Dismiss Messages")
	public void dismissMessagesTest() {
		method.clickOnElement(driver, startMessage.dismissButton);
		method.clickOnElement(driver, homepage.covidMessageDismiss);
		method.wait(2);
		Assert.assertTrue(method.isElementPresent(driver, homepage.khanAcadamy));

	}

	@Test(testName = "Signup using email", dependsOnMethods = "dismissMessagesTest", priority = 1, enabled = false)
	public void signupUsingEmailTest() throws IOException, InterruptedException {
		method.clickOnElement(driver, homepage.signInButton);
		method.wait(5);
		driver.findElement(By.xpath("//*[@text='Sign up with email']")).click();
		method.wait(5);
		method.enterText(driver, signup.firstname, ("Fname" + method.generateRandomNumber(2)));
		method.enterText(driver, signup.lastname, ("Lname" + method.generateRandomNumber(2)));
		method.clickOnElement(driver, signup.birthday);
		method.wait(2);
		// method.scrollToElementAndClick(driver, signup.month, "Apr");
//		method.scrollToElementAndClick(driver, signup.date, "22");
//		method.scrollToElementAndClick(driver, signup.year, "1996");
		method.clickOnElement(driver, signup.okButton);
		method.enterText(driver, signup.email, method.randomEmailGenerator("Fname.Lname"));
		method.enterText(driver, signup.password, "password321987");
		method.clickOnElement(driver, signup.createButton);
		method.wait(10);
		Assert.assertTrue(method.isElementPresent(driver, homepage.khanAcadamy));
	}

	@Test(testName = "Add Courses", dependsOnMethods = "signupUsingEmailTest", priority = 2, enabled = false)
	public void AddCoursesTest() {
		method.scrollTillElementAndClick(driver, homepage.getStartedButtonText);
		method.scrollTillElementAndClick(driver, grade.fourththYearUniversityText);
		method.scrollTillElementAndClick(driver, chooseCourse.algebraBasicsText);
		method.scrollTillElementAndClick(driver, chooseCourse.electricalEngineeringText);
		method.scrollTillElementAndClick(driver, chooseCourse.macroeconomicsText);
		method.scrollTillElementAndClick(driver, chooseCourse.artHistoryText);
		method.scrollTillElementAndClick(driver, chooseCourse.computerProgramingText);
		method.scrollTillElementAndClick(driver, chooseCourse.mcatText);
		method.scrollTillElementAndClick(driver, chooseCourse.doneText);
		Assert.assertTrue(method.isElementPresentafterScrolling(driver, chooseCourse.algebraBasicsText));
		Assert.assertTrue(method.isElementPresentafterScrolling(driver, chooseCourse.electricalEngineeringText));
		Assert.assertTrue(method.isElementPresentafterScrolling(driver, chooseCourse.macroeconomicsText));
		Assert.assertTrue(method.isElementPresentafterScrolling(driver, chooseCourse.artHistoryText));
		Assert.assertTrue(method.isElementPresentafterScrolling(driver, chooseCourse.computerProgramingText));
		Assert.assertTrue(method.isElementPresentafterScrolling(driver, chooseCourse.mcatText));

	}

	@Test(testName = "Signout", priority = 3, enabled = false)
	public void signout() {
		method.clickOnElement(driver, homepage.settingbutton);
		method.wait(5);
		method.clickOnElement(driver, setting.signoutButton);
		method.clickOnElement(driver, setting.signOutConfirmButton);
		Assert.assertTrue(method.isElementPresent(driver, setting.signInButton));
		method.pressBackButton(driver);
		Assert.assertTrue(method.isElementPresent(driver, homepage.khanAcadamy));
	}
	
	
	@Test(testName = "Sign In and Sign Out using Data provider", dataProvider = "getData" ,dependsOnMethods = "dismissMessagesTest", priority = 4)
	public void SignInUsingDataProvider(Hashtable<String, String> data1) {
		data = data1;
		method.clickOnElement(driver, homepage.signInButton);
		method.clickOnElement(driver, signupOptions.signInButton);
		method.enterText(driver, signIn.emailTextBox, data.values().toString());
		method.enterText(driver, signIn.passwordTextBox, data.values().toString());
		method.clickOnElement(driver, signIn.signInButton);
		method.wait(5);
		Assert.assertTrue(method.isElementPresent(driver, homepage.khanAcadamy));
	}
	

	@AfterTest(alwaysRun = true)
	public void teardownSteps() {
//		method.pressAppSwitchButton(driver);
//		method.clickOnElement(driver, mobileButtons.clearAllButton);
	}
	
	@DataProvider(name = "getData")
	public Object[][] getData() {
		return dataUtil.getData();
	}
}
