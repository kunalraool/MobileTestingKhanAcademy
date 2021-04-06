package pom;



import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ChooseCoursesPOM {

	public ChooseCoursesPOM(AndroidDriver<AndroidElement> driver)  {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//*[@text='Macroeconomics']")
	public AndroidElement macroeconomicsButton;
	public String macroeconomicsText =  "Macroeconomics";

	@AndroidFindBy(xpath = "//*[@text='Algebra basics']")
	public AndroidElement algebraBasicsButton;
	public String algebraBasicsText =  "Algebra basics";
	
	@AndroidFindBy(xpath = "//*[@text='Electrical engineering']")
	public AndroidElement electricalEngineeringButton;
	public String electricalEngineeringText =  "Electrical engineering";
	
	@AndroidFindBy(xpath = "//*[@text='Art history']")
	public AndroidElement artHistoryButton;
	public String artHistoryText =  "Art history";	

	
	@AndroidFindBy(xpath = "//*[@text='Computer programming']")
	public AndroidElement computerProgramingButton;
	public String computerProgramingText =  "Computer programming";


	@AndroidFindBy(xpath = "//*[@text='MCAT']")
	public AndroidElement mcatButton;
	public String mcatText =  "MCAT";
	
	
	@AndroidFindBy(xpath = "//*[@text='Done']")
	public AndroidElement doneButton;
	public String doneText =  "Done";	

	
}
