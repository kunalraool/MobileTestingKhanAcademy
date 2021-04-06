package pom;



import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignInPOM {

	public SignInPOM(AndroidDriver<AndroidElement> driver) {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(accessibility = "Enter an e-mail address or username")
	public AndroidElement emailTextBox;
	
	@AndroidFindBy(accessibility = "Password")
	public AndroidElement passwordTextBox;
	
	@AndroidFindBy(accessibility = "Sign in")
	public AndroidElement signInButton;
	
}
