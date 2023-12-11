package com.amazon.qa.base;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static org.testng.Assert.assertEquals;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.JavascriptExecutor;
import com.amazon.qa.util.TestUtil;

public class TestBase 
{	
	public static WebDriver driver;
	public static Properties prop;
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public static String TESTDATA_SHEET_PATH = "/Users/naveenkhunteta/Documents/workspace"
			+ "/FreeCRMTest/src/main/java/com/crm/qa/testdata/FreeCrmTestData.xlsx";

	static Workbook book;
	static Sheet sheet;
	static JavascriptExecutor js;

	
	public TestBase()
	{
		try 
		{
        prop= new Properties();
		String path = System.getProperty("user.dir")+ "\\src\\main\\java\\com\\amazon\\qa\\config\\config.properties";
		System.out.print(path);
		FileInputStream ip = new FileInputStream(path);	
		prop.load(ip);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e) 
		{
			e.printStackTrace();
		
	    }

	}
	


public static void initialization() throws InterruptedException
{
	String BrowserName=prop.getProperty("browser");
	System.out.print(BrowserName);
	if(BrowserName.equals("chrome"))
	{       
		driver = new ChromeDriver(); 		
	}
	else if(BrowserName.equals("FF")){
		System.setProperty("webdriver.gecko.driver", "/Users/naveenkhunteta/Documents/SeleniumServer/geckodriver");	
		driver = new FirefoxDriver(); 
	}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts();		
		driver.get(prop.getProperty("url"));	
}


public static Object[][] getTestData(String sheetName) {
	FileInputStream file = null;
	try {
		file = new FileInputStream(TESTDATA_SHEET_PATH);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	try {
		book = WorkbookFactory.create(file);
	} catch (InvalidFormatException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	sheet = book.getSheet(sheetName);
	Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	// System.out.println(sheet.getLastRowNum() + "--------" +
	// sheet.getRow(0).getLastCellNum());
	for (int i = 0; i < sheet.getLastRowNum(); i++) {
		for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
			data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			// System.out.println(data[i][k]);
		}
	}
	return data;
}

public static void takeScreenshotAtEndOfTest() throws IOException {
	File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	String currentDir = System.getProperty("user.dir");
	FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
}

public static void runTimeInfo(String messageType, String message) throws InterruptedException {
	js = (JavascriptExecutor) driver;
	// Check for jQuery on the page, add it if need be
	js.executeScript("if (!window.jQuery) {"
			+ "var jquery = document.createElement('script'); jquery.type = 'text/javascript';"
			+ "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';"
			+ "document.getElementsByTagName('head')[0].appendChild(jquery);" + "}");
	Thread.sleep(5000);

	// Use jQuery to add jquery-growl to the page
	js.executeScript("$.getScript('https://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

	// Use jQuery to add jquery-growl styles to the page
	js.executeScript("$('head').append('<link rel=\"stylesheet\" "
			+ "href=\"https://the-internet.herokuapp.com/css/jquery.growl.css\" " + "type=\"text/css\" />');");
	Thread.sleep(5000);

	// jquery-growl w/ no frills
	js.executeScript("$.growl({ title: 'GET', message: '/' });");
//'"+color+"'"
	if (messageType.equals("error")) {
		js.executeScript("$.growl.error({ title: 'ERROR', message: '"+message+"' });");
	}else if(messageType.equals("info")){
		js.executeScript("$.growl.notice({ title: 'Notice', message: 'your notice message goes here' });");
	}else if(messageType.equals("warning")){
		js.executeScript("$.growl.warning({ title: 'Warning!', message: 'your warning message goes here' });");
	}else
		System.out.println("no error message");
	Thread.sleep(5000);
}


}
	
