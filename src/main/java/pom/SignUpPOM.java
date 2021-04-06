package pom;



import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SignUpPOM {

	public SignUpPOM(AndroidDriver<AndroidElement> driver) {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//*[@content-desc='First name']")
	public AndroidElement firstname;
	
	@AndroidFindBy(xpath = "//*[@content-desc='Last name']")
	public AndroidElement lastname;
	
	@AndroidFindBy(xpath = "//*[@content-desc='Email address']")
	public AndroidElement email;
	
	@AndroidFindBy(xpath = "//*[@content-desc='Password']")
	public AndroidElement password;
	
	@AndroidFindBy(xpath = "//*[@content-desc='Birthday']")
	public AndroidElement birthday;
	
//	@AndroidFindBy(xpath = "//android.widget.NumberPicker[1]/android.widget.EditText")
//	public AndroidElement month;
	//public String monthxpath = "//android.widget.NumberPicker[1]/android.widget.EditText[@resource-id='android:id/numberpicker_input']";
	public String month = "Jun";
	
//	@AndroidFindBy(xpath = "//android.widget.NumberPicker[2]/android.widget.EditText")
//	public AndroidElement date;
	public String date = "01";
	
//	@AndroidFindBy(xpath = "//android.widget.NumberPicker[3]/android.widget.EditText")
//	public AndroidElement year;
	public String year = "2000";
	
	@AndroidFindBy(xpath = "//*[@resource-id='android:id/button1']")
	public AndroidElement okButton;
	
	@AndroidFindBy(xpath = "//*[@text='CREATE']")
	public AndroidElement createButton;
	
	
}
