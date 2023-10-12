package commonFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.PropertyFileUtil;

public class FunctionLibrary {
	public static WebDriver driver;
//method for launching browser
	public static WebDriver startBrowser() throws Throwable
	{
		if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else
		{
			System.out.println("Browser value is not matching");
		}
		return driver;
	}
//method for launching Url
public static void openUrl(WebDriver driver) throws Throwable
{
	driver.get(PropertyFileUtil.getValueForKey("url"));
	
}
// method to wait for element
public static void waitForElement(WebDriver driver, String Locator_Type,String Locator_Value,String wait)
{
	WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
	if(Locator_Type.equalsIgnoreCase("name"))
	{
		mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locator_Value)));
	}
	else if (Locator_Type.equalsIgnoreCase("xpath"))
	{
		mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locator_Value)));
	}
	else if (Locator_Type.equalsIgnoreCase("id"))
	{
		mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locator_Value)));
	}
}
	// method textboxes
	public static void typeAction(WebDriver driver, String Locator_Type,String Locator_Value,String TestData)
	{
		if(Locator_Type.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(Locator_Value)).clear();
			driver.findElement(By.name(Locator_Value)).sendKeys(TestData);
		}
		else if (Locator_Type.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(Locator_Value)).clear();
			driver.findElement(By.xpath(Locator_Value)).sendKeys(TestData);
		}
		else if (Locator_Type.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(Locator_Value)).clear();
			driver.findElement(By.id(Locator_Value)).sendKeys(TestData);
		}
	}
	// methods for buttons,radio buttons,links and images
	public static void clickAction(WebDriver driver, String Locator_Type,String Locator_Value)
	{
		if(Locator_Type.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(Locator_Value)).click();
		}
		else if(Locator_Type.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(Locator_Value)).click();;
		}
		else if(Locator_Type.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(Locator_Value)).sendKeys(Keys.ENTER);
		}
	}
	// method for title validation
	public static void validateTitle(WebDriver driver, String Expected_Title)
	{
		String Actual_Title = driver.getTitle();
		try {
			Assert.assertEquals(Actual_Title, Expected_Title, "Title not matched");
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage());
		}
		
	}
	// method for close browser
	public static void closeBrowser(WebDriver driver)
	{
		driver.quit();
	}
	public static void mouseClick(WebDriver driver)
	{
		Actions ac = new Actions(driver);
		ac.moveToElement(driver.findElement(By.xpath("//a[starts-with(text(),'Stock Items ')]"))).perform();
		ac.moveToElement(driver.findElement(By.xpath("(//a[.='Stock Categories'])[2]"))).click().perform();
	}
	public static void categoryTable(WebDriver driver,String ExpData)throws Throwable
	{	
		//if search box already displayed dont click on search panel
		if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search-Textbox"))).isDisplayed())
			// if search panel box not displayed, click on search panel
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search-panel"))).click();
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search-Textbox"))).sendKeys(ExpData);
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search-button"))).click();
	// method to capture table
		String ActData = driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[4]/div/span/span")).getText();
		System.out.println(ActData+"      "+ExpData);
		try {
		Assert.assertEquals(ExpData, ActData,"Category name not found in table");
		}
		catch(Throwable t)
		{
			System.out.println(t.getMessage());
		}
	}
	// METHOD TO DROPDOWNACTION
		public static void dropDownAction(WebDriver driver,String Locator_Type, String Locator_Value, String TestData)
		{
			if(Locator_Type.equalsIgnoreCase("xpath")) {
				int data = Integer.parseInt(TestData);
				WebElement we = driver.findElement(By.name(Locator_Value));
				Select sel = new Select(we);
				sel.selectByIndex(data);
				
			}
			else if (Locator_Type.equalsIgnoreCase("id")) {
				int data = Integer.parseInt(TestData);
				WebElement we = driver.findElement(By.id(Locator_Value));
				Select  sel = new Select(we);
				sel.selectByIndex(data);
			}
			else if (Locator_Type.equalsIgnoreCase("name")) {
				int data = Integer.parseInt(TestData);
				WebElement we = driver.findElement(By.name(Locator_Value));
				Select sel = new Select(we);
				sel.selectByIndex(data);
				}
			}
		// method for capture data
		public static void captureData(WebDriver driver, String Locator_Type,String Locator_Value) throws Throwable
		{
		String datawrite = "";
		if(Locator_Type.equalsIgnoreCase("xpath")) 
		{
			 datawrite = driver.findElement(By.xpath(Locator_Value)).getAttribute("value");
		}
		else if(Locator_Type.equalsIgnoreCase("name")) 
		{
			datawrite = driver.findElement(By.name(Locator_Value)).getAttribute("value");
		}
		else if(Locator_Type.equalsIgnoreCase("id")) 
		{
			datawrite = driver.findElement(By.id(Locator_Value)).getAttribute("value");
		}
		// write into notepad
		FileWriter fw = new FileWriter("./CaptureData/captureFile.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(datawrite);
		bw.flush();
		bw.close();
		}
		//read from notepad
		public static void stockTable(WebDriver driver) throws Throwable
		{
			FileReader fr = new FileReader("./CaptureData/captureFile.txt");
			BufferedReader br = new BufferedReader(fr);
			String Exp_Data = br.readLine();
			if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search-Textbox"))).isDisplayed())
				// if search panel box not displayed, click on search panel
				driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search-panel"))).click();
				driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search-Textbox"))).sendKeys(Exp_Data);
				driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search-button"))).click();
			
				String Act_Data = driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[8]/div/span/span")).getText();
				System.out.println(Exp_Data+"       "+Act_Data);
				try {
					Assert.assertEquals(Exp_Data, Act_Data, "Stock number not matching");
				}catch(Throwable t)
				{
					System.out.println(t.getMessage());
				}
				
		}
		public static void supplierTable(WebDriver driver) throws Throwable
		{
			FileReader fr = new FileReader("./CaptureData/captureFile.txt");
			BufferedReader br = new BufferedReader(fr);
			String Exp_Data = br.readLine();
			if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search-Textbox"))).isDisplayed())
				// if search panel box not displayed, click on search panel
				driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search-panel"))).click();
				driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search-Textbox"))).sendKeys(Exp_Data);
				driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("Search-button"))).click();
			
				String Act_Data = driver.findElement(By.xpath("//table[@class='table ewTable']/tbody/tr[1]/td[6]/div/span/span")).getText();
				System.out.println(Exp_Data+"       "+Act_Data);
				try {
					Assert.assertEquals(Exp_Data, Act_Data, "Supplier number not matching");
				}catch(Throwable t)
				{
					System.out.println(t.getMessage());
				}
		}
		
		
}

		
		
		
		
		
		
		
		
		
		
		
		
