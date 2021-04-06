package pom;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SettingHomePOM {

	public SettingHomePOM(AndroidDriver<AndroidElement> driver) {
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "android.widget.TextView")
	public List<AndroidElement> allOptionsinSetting;
	public List<AndroidElement> getOptionsInSetting(){
		return allOptionsinSetting;
	}
	
	@AndroidFindBy(xpath = "//*[@text='Sign out']")
	public AndroidElement signoutButton;
	
	@AndroidFindBy(xpath = "//*[@resource-id='android:id/button1']")
	public AndroidElement signOutConfirmButton;
	
	@AndroidFindBy(xpath = "//*[@text='Sign in']")
	public AndroidElement signInButton;
	

}
