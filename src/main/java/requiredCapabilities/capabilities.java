package requiredCapabilities;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import resource.Methods;

public class capabilities {

	protected static String appactivity;
	protected static String apppackage;
	protected static String devicename;
	protected static String platform;
	public static  AndroidDriver<AndroidElement> driver;
	

	public static AndroidDriver<AndroidElement> capability(String appactivity, String apppackage, String devicename, String platform) throws IOException, InterruptedException {

		

		FileReader fis = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\global.properties");
		Properties property = new Properties();
		property.load(fis);
		appactivity = property.getProperty("APP_ACTIVITY");
		apppackage = property.getProperty("APP_PACKAGE");
		devicename = property.getProperty("DEVICE_NAME");
		platform = property.getProperty("PLATFORM_NAME");
		
		if (devicename.contains("Kunal")) {
			Methods.StartEmulator();
		}
		
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, devicename);
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
		cap.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, apppackage);
		cap.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appactivity);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		cap.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, "C:\\Users\\KunalRaool\\Downloads\\chromedriver_win32_89\\chromedriver.exe");
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);
		

		return driver;
	
	}

}
