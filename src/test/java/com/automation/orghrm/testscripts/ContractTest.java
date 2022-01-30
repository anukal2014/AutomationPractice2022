package com.automation.orghrm.testscripts;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ContractTest {

	public static WebDriver driver;
	
	@BeforeClass
	@Parameters({"browser"})
	public void launchChrome(@Optional String browser)
	//public static void main(String[] args) 
	{
		System.out.println("Launch Browser- "+ browser);
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe") ; 
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
						
	}
	
	@BeforeMethod
	@Parameters({"url"})
	public void LaunchApp(String url)
	//public static void main(String[] args) 
	{
		System.out.println("Launch Application ");
		driver.get(url);
		driver.manage().window().maximize();
					
	}
	@Test(priority=0)
	@Parameters({"username","password"})
	public void enterCredentials(String username, String password)
	{
		System.out.println("Enter Credentials");
		
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(password);
		System.out.println("click on button");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		String dashboardPage = driver.getTitle();
		System.out.println("page title - "+dashboardPage);
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals("Dashboard",dashboardPage);
		softassert.assertAll();
		
	}
		
	@AfterMethod
	public void TakeScreenShotOnFailure(ITestResult result) throws IOException
	{
		String name = dateOpration();
		if(result.getStatus() == result.FAILURE)
		{

			System.out.println("Failed Test Case- ");
			TakesScreenshot ts = (TakesScreenshot) driver;
			File image = ts.getScreenshotAs(OutputType.FILE);
			File destFile = new File("./screenshots/"+result.getName()+name+".png");
			FileUtils.copyFile(image, destFile);
		}
		
	}
	
	@AfterClass
	public void closeBrowser()
	{
		System.out.println("After class close browser");
		driver.close();
	}
	
	public static String dateOpration()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMYYYYmmss");
		Date date = new Date();
		String timestamp = dateFormat.format(date);
		return timestamp;
	}
	
	public void automationpractice(String url, String text)
	//public static void main(String[] args) 
	{
		  System.out.println("The reversed sentence is: ");
		System.setProperty("webdriver.chrome.driver", "./chromedriver.exe") ; 
		WebDriver driver = new ChromeDriver();
		  driver.get(url);
		  driver.manage().window().maximize();
		 // driver.findElement(By.linkText("Sign in"));
		  
		  
		  /*String sentence = "Go work";
	    String reversed = reverse(sentence);
	    System.out.println("The reversed sentence is: " + reversed);
	    
		Integer a[]={1,2,5,6,3,2};  
		Arrays.sort(a);
		System.out.println(a[a.length-1]);
		
		Integer b[]={1,2,5,6,3,2}; 
		List<Integer> list=Arrays.asList(b);  
		Collections.sort(list);
		int largestNum = list.get(list.size()-1);
		
		System.out.println("lasrgest num: "+largestNum);
		*/
		/*Calendar calendar = Calendar.getInstance(); 
		System.out.println("The current date is : " + calendar.getTime());  
		System.out.println("At present Calendar's Year: " + calendar.get(Calendar.YEAR));  
		System.out.println("At present Calendar's Day: " + calendar.get(Calendar.DATE));
		calendar.add(Calendar.DATE, -15);  
		System.out.println("15 days ago: " + calendar.getTime()); 
		*/
	 }
	
	public static void clickOnButton(WebDriver driver, WebElement element)
	{
		new WebDriverWait(driver,20).
		until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		
	}
	/*public static WebElement getElement(WebDriver driver, String locator,String locatorType)
	{
		WebDriverWait wait = new WebDriverWait(driver,20);
		WebElement element = null;
		switch(locatorType) {
		case "XPATH":
			element = (WebElement) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));
		break;
		case "LinkText":
			element = (WebElement) wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(locator)));
		break;
		}
		return element;
	}*/
	
}
