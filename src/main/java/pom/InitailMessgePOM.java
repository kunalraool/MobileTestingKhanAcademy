package pom;



import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class InitailMessgePOM {

	public InitailMessgePOM(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		
		
	}
	
	@AndroidFindBy(className = "android.widget.Button")
	public AndroidElement dismissButton;
	
	
	

}
