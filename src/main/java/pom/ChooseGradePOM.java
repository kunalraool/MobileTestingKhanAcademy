package pom;



import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ChooseGradePOM {

	public ChooseGradePOM(AndroidDriver<AndroidElement> driver)  {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//*[@text='4th year university']")
	public AndroidElement fourththYearUniversity;
	public String fourththYearUniversityText= "4th year university";

	
	

}
