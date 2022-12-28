package appium;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;
import java.io.IOException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import capabilitis.framwork;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;

public class appiumclass extends framwork {

	@BeforeTest
	public void KillAllProcesses() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
		
		System.out.println("Hi Appium");
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"//emulator.bat");
		Thread.sleep(80000);
		System.out.println("Hi dipak");
		
	}
	@Test
	public void tc1() throws IOException, InterruptedException {
		
		System.out.println("Hi General Store");
		service = startServer();
		
		AndroidDriver<AndroidElement> driver= hybrid_capabilities(appactivity, apppackage, deviceName, chromeexcutable);
		
		Thread.sleep(6000);
		
		driver.findElement(MobileBy.id("android:id/text1")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Antarctica\"))").click();
		driver.findElement(MobileBy.id("com.androidsample.generalstore:id/nameField")).sendKeys("dipak");
		
		driver.hideKeyboard();
		
		driver.findElement(MobileBy.id("com.androidsample.generalstore:id/radioFemale")).click();
		
		Thread.sleep(5000);
		
		driver.findElement(MobileBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
				
		
		Thread.sleep(5000);
		
		String productname=driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Converse All Star\"))").getText();
		
		System.out.println(productname);
		
		driver.findElements(MobileBy.xpath("//*[@text='ADD TO CART']")).get(1).click();
		
		driver.findElement(MobileBy.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(5000);
		
		
String price1=driver.findElement(MobileBy.id("com.androidsample.generalstore:id/productPrice")).getText();
		
		price1=price1.substring(1);
		
		Double cost1=Double.parseDouble(price1);
		System.out.println(cost1);
		
		Thread.sleep(5000);
		driver.findElement(MobileBy.className("android.widget.CheckBox")).click();
		
		
		AndroidElement terms=driver.findElement(MobileBy.id("com.androidsample.generalstore:id/termsButton"));
		
		TouchAction ta=new TouchAction(driver);
		
		ta.longPress(longPressOptions().withElement(element(terms))).release().perform();
		Thread.sleep(3000);
		
		String message=driver.findElement(MobileBy.id("android:id/message")).getText();
		
		System.out.println(message);
		
		driver.findElement(MobileBy.id("android:id/button1")).click();
		Thread.sleep(3000);
		
		driver.findElement(MobileBy.className("android.widget.Button")).click();
				
	}

}
