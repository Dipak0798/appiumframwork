package capabilitis;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class framwork {
	
	protected static String appactivity;
	protected static String apppackage;
	protected static String deviceName;
	protected static String chromeexcutable;
	public AppiumDriverLocalService service;
	public AppiumDriverLocalService startServer()
	{
		boolean flag = checkifserverisRunning(4723);
		if(!flag)
		{
	service = AppiumDriverLocalService.buildDefaultService();
	service.start();
			}
		return service;
	}
	
	public static boolean checkifserverisRunning(int port)
	{
		
		System.out.println("Hi Appium Server");
		boolean isServerRunning=false;
		ServerSocket serversocket;
		try{
			serversocket = new ServerSocket(port);
			serversocket.close();
		}
		catch(IOException e)
		{
			isServerRunning = true;
		}
		finally {
			serversocket=null;
		}
		
		System.out.println("Bye Appium Server");
		return isServerRunning;
	}
	
	public static AndroidDriver<AndroidElement> hybrid_capabilities(String appactivity, String apppackage, String deviceName, String chromeexcutable) throws IOException{
		
		FileReader gp = new FileReader(System.getProperty("user.dir")+"//src//main//java//Global.properties");
		Properties pro= new Properties();
		pro.load(gp);
		appactivity=pro.getProperty("appactivity");
		apppackage=pro.getProperty("apppackage");
		deviceName=pro.getProperty("deviceName");
		chromeexcutable=pro.getProperty("chromeexcutable");
		
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.androidsample.generalstore");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.androidsample.generalstore.SplashActivity ");
		//dc.setCapability("appium:chromeOptions", ImmutableMap.of("w3c",false));
		//driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);
		
		dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,60*5);
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, apppackage);
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appactivity);
		dc.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_EXECUTABLE, chromeexcutable);
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://0.0.0.0:4723/wd/hub"),dc);
		return driver;
	}

}
