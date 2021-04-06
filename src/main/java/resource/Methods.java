package resource;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import requiredCapabilities.capabilities;

public class Methods extends capabilities {

	public static AppiumDriverLocalService Service;
	@SuppressWarnings("rawtypes")
	TouchAction tapping;
	AndroidElement element123;
	public String ScreenShotPath;

	public static void StartEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\resources\\emulator.bat");
		Thread.sleep(5000);

	}

	public static boolean checkServerRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serversocket;
		try {
			serversocket = new ServerSocket(port);
			serversocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serversocket = null;
		}
		return isServerRunning;
	}

	public static AppiumDriverLocalService startServer() {
		boolean flag = checkServerRunning(4723);
		if (!flag) {

//		Service = AppiumDriverLocalService.buildDefaultService();
			Service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
					.usingDriverExecutable(new File("c:\\Program Files\\nodejs\\node.exe"))
					.withAppiumJS(new File(
							"C:\\Users\\KunalRaool\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
					.withIPAddress("127.0.0.1").usingPort(4723));
			Service.start();
		}
		return Service;
	}

	@SuppressWarnings("finally")
	public static AndroidDriver<AndroidElement> initiateDriver() {
		try {
			driver = capability(appactivity, apppackage, devicename, platform);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			return driver;
		}
	}

	public void clickOnElement(AndroidDriver<AndroidElement> driver, AndroidElement elementToClick) {
		if (isElementPresent(driver, elementToClick)) {
			clickUsingTap(driver, elementToClick);
		} else {
			Assert.fail("Element not Found");
		}
	}

	public boolean isElementPresent(AndroidDriver<AndroidElement> driver, AndroidElement elementToClick) {
		boolean isPresent = false;
		try {
			if (elementToClick.isDisplayed() || elementToClick.isEnabled()) {
				isPresent = true;
				takeScreenShot(driver);
			} else {
				Assert.fail("Warning Message: Element is Not Present" + elementToClick.toString());
				takeScreenShot(driver);
			}
		} catch (Exception exception) {
			Assert.fail("Warning Message: Element is Not Present" + elementToClick.toString());
			return isPresent;
		}
		return isPresent;
	}
	
	public boolean isElementPresentafterScrolling(AndroidDriver<AndroidElement> driver, String elementText) {
		boolean isPresent = false;
		AndroidElement elementToClick = null;
		try {
			String scrollToElement;
			scrollToElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(textContains(\"" + elementText + "\"));";

			elementToClick = driver.findElementByAndroidUIAutomator(scrollToElement);
			if (elementToClick.isDisplayed() || elementToClick.isEnabled()) {
				isPresent = true;
				takeScreenShot(driver);
			} else {
				Assert.fail("Warning Message: Element is Not Present" + elementToClick.toString());
				takeScreenShot(driver);
			}
		} catch (Exception exception) {
			Assert.fail("Warning Message: Element is Not Present" + elementToClick.toString());
			return isPresent;
		}
		return isPresent;
	}
	
	

	@SuppressWarnings("rawtypes")
	protected void clickUsingTap(AndroidDriver<AndroidElement> driver, AndroidElement element) {
		tapping = new TouchAction(driver);
		tapping.tap(tapOptions().withElement(element(element))).perform();
		takeScreenShot(driver);
	}

	public void wait(int timeToWaitInSec) {
		try {
			Thread.sleep(timeToWaitInSec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public AndroidElement getElementsFromList(List<AndroidElement> elementToClick, String text) {
		element123 = new AndroidElement();
		for (int i = 0; i < elementToClick.size(); i++) {
			System.out.println(elementToClick.get(i).getText());
			if (text == (elementToClick.get(i).getText())) {
				// element123 =
				elementToClick.get(i).click();
			} else {
				element123 = null;
			}
		}
		return element123;

	}

	public void clickOnElementfromList(AndroidDriver<AndroidElement> driver, List<AndroidElement> elementToClick,
			String text) {
		AndroidElement element = getElementsFromList(elementToClick, text);
		if (element != null) {
			// clickUsingTap(driver, element);
			takeScreenShot(driver);
		} else {
			Assert.fail("Element not Found");
		}

	}

	public void enterText(AndroidDriver<AndroidElement> driver1, AndroidElement element, String val) {
		try {
			clickOnElement(driver1, element);
			driver.hideKeyboard();
			element.sendKeys(val);
			takeScreenShot(driver);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void scrollIntoElementAndClick(AndroidDriver<AndroidElement> driver1, String xpath, String val) {
		String scrollToElement = "new UiScrollable(new UiSelector().text(\"" + xpath + "\""
				+ ")).flingBackward().scrollIntoView(new UiSelector().textMatches(\"" + val
				+ "\").instance(0))flingBackward()";
		driver.findElementByAndroidUIAutomator(scrollToElement);
		takeScreenShot(driver);
	}

	public void scrollTillElementAndClick(AndroidDriver<AndroidElement> driver1, String elementText) {
		String scrollToElement;
		AndroidElement element;
		scrollToElement = "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(textContains(\"" + elementText + "\"));";

		element = driver.findElementByAndroidUIAutomator(scrollToElement);
		clickOnElement(driver1, element);

	}

	
	
	public void scrollTillElementAndClick(AndroidDriver<AndroidElement> driver1, String elementText,
			String scrollDirection, String movement) {
		String scrollToElement;
		AndroidElement element;
		String direction;
		if (scrollDirection == "HORIZONTAL") {
			direction = "setAsHorizontalList()";
		} else {
			direction = "setAsVerticalList()";
		}
		switch (movement) {
		case "FORWARD":
			scrollToElement = "new UiScrollable(new UiSelector().scrollable(true))." + direction
					+ ".scrollIntoView(textContains(\"" + elementText + "\")).scrollForward();";
			break;
		case "BACKWARD":
			scrollToElement = "new UiScrollable(new UiSelector().scrollable(true))." + direction
					+ ".scrollIntoView(textContains(\"" + elementText + "\")).scrollBackward();";
			break;
		case "BIGINNING":
			scrollToElement = "new UiScrollable(new UiSelector().scrollable(true))." + direction
					+ ".scrollIntoView(textContains(\"" + elementText + "\")).flingToBeginning(10);";
			break;
		case "END":
			scrollToElement = "new UiScrollable(new UiSelector().scrollable(true))." + direction
					+ ".scrollIntoView(textContains(\"" + elementText + "\")).flingToEnd(10);";
			break;
		default:
			scrollToElement = "new UiScrollable(new UiSelector().scrollable(true))." + direction
					+ ".scrollIntoView(textContains(\"" + elementText + "\"));";
			break;
		}

		element = driver.findElementByAndroidUIAutomator(scrollToElement);
		clickOnElement(driver1, element);

	}
	
	
	
	public long generateRandomNumber(int noOfDigitsReq) {
		Random rnd = new Random();
		char[] digits = new char[noOfDigitsReq];
		digits[0] = (char) (rnd.nextInt(9) + '1');
		for (int i = 1; i < digits.length; i++) {
			digits[i] = (char) (rnd.nextInt(10) + '0');
		}
		return Long.parseLong(new String(digits));
	}

	public String randomEmailGenerator(String userName) {
		String email = randomStringGenerator(userName) + "@gmail.com";
		return email;
	}

	public String randomStringGenerator(String textToAppend) {
		String randString;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		String systemDate = dateFormat.format(date).replaceAll(" ", "").replaceAll(":", "").replaceAll("/", "");
		randString = textToAppend.concat(systemDate);
		return randString;
	}

	public void pressBackButton(AndroidDriver<AndroidElement> driver) {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		takeScreenShot(driver);
	}

	public void pressHomeButton(AndroidDriver<AndroidElement> driver) {
		driver.pressKey(new KeyEvent(AndroidKey.HOME));
		takeScreenShot(driver);
	}

	public void pressAppSwitchButton(AndroidDriver<AndroidElement> driver) {
		driver.pressKey(new KeyEvent(AndroidKey.APP_SWITCH));
		takeScreenShot(driver);

	}

	public void takeScreenShot(AndroidDriver<AndroidElement> driver1) {
		if (driver1 != null) {
			Date date = new Date();
			String todaysFolder = createFolder(System.getProperty("user.dir") + "//target", date);
			createFolder(todaysFolder);
			String screenshotFile = date.toString().replace(":", "_").replace(" ", "_") + ".png";
			String filePath = todaysFolder + File.separator + screenshotFile;
			String screenPath = "." + File.separator + "Screenshots" + File.separator + screenshotFile;
			try {

				File scrFile = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File(filePath));
				// test.addScreenCaptureFromPath(screenPath, screenPath);
				ScreenShotPath = screenPath;
			} catch (Exception e) {
				Assert.fail("Got an exception while capturing the screenshot");
			}
		} else
			throw new Error("Either driver or report object is 'Null'");

	}

	public static String createFolder(String folderPath, Date date1) {
		String folderName;
		SimpleDateFormat sdtf = new SimpleDateFormat("dd-MMM-YY");
		folderName = folderPath + File.separator + sdtf.format(date1);
		// create a directory in the path
		createFolder(folderName);
		return folderName;
	}

	public static void createFolder(String folderPath) {
		try {
			File file = new File(folderPath);
			if (!file.exists())
				file.mkdir();
		} catch (Exception e) {
		}
	}
}
