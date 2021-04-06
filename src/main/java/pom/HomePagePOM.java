package pom;



import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePagePOM {

	public HomePagePOM(AndroidDriver<AndroidElement> driver)  {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//*[@text='Dismiss']")
	public AndroidElement covidMessageDismiss;
	//public AndroidElement covidMessageDismiss() {return covidMessageDismiss;}
	
	@AndroidFindBy(xpath = "//*[@text='Sign in']")
	public AndroidElement signInButton;
	//public AndroidElement signInButton() {return signInButton;}
	
	@AndroidFindBy(xpath = "//*[@text='Khan Academy']")
	public AndroidElement khanAcadamy;
	
	@AndroidFindBy(xpath = "//*[@content-desc='Settings']")
	public AndroidElement settingbutton;
	
	@AndroidFindBy(xpath = "//*[@text='Join class']")
	public AndroidElement joinClassButton;
	
	@AndroidFindBy(xpath = "//*[@text='Edit']")
	public AndroidElement editCourcesButton;
	public String editCourcesButtonText= "Edit";
	
	@AndroidFindBy(xpath = "//*[@text='Get started']")
	public AndroidElement getStartedButton;
	public String getStartedButtonText= "Get started";

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
	
	

}
