package com.amazon.testcases;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.amazon.qa.base.TestBase;
import com.amazon.qa.pages.HomePage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class HomePageTest extends TestBase {

	HomePage homepage;
	public ExtentSparkReporter spark;
	public ExtentReports extent;
	public ExtentTest logger;
    public ExtentReporter htmlReporter;
    public ExtentTest test;

	HomePageTest()
	{
		super();
	}
	
	@BeforeSuite
	public void setup() throws InterruptedException
	{

		System.out.println("Hello");
	    extent = new ExtentReports();
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output-Extent/HomePage.html");
		extent.attachReporter(spark);
		extent.setSystemInfo("Host Name", "SoftwareTestingMaterial");
	    extent.setSystemInfo("Environment", "Production");
		extent.setSystemInfo("User Name", "AAK SM");
		spark.config().setDocumentTitle("Title of the Report Comes here ");
		// Name of the report
		spark.config().setReportName("Home page presence of all object ");
		// Dark Theme
		spark.config().setTheme(Theme.STANDARD);
    	initialization();
    	homepage=new HomePage();

	}

	
	@Test (priority =1) 
	public void AmazonindiaLogoTest()
	{
    	test = extent.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
    	try 
    	{
		boolean flag=homepage.verifyAmazonindiaLogo();
		test.info( Boolean. toString(flag));
		if(Boolean.toString(flag)== "true") 
		{
		test.info("PASS");
		Assert.assertTrue(flag);
		}
    	}
    	catch(Exception e)
    	{
		test.info("Fail    "+e.getMessage());
		test.fail("Condition not met");
		Assert.assertFalse(true);
    	}
		
	}
	@Test (priority =2) 
	public void flagTest()
	{
    	test = extent.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
	    boolean flag=homepage.verifyflag();
		Assert.assertTrue(flag);
		test.info("PASS");

	}
	
	
	@Test (priority =3) 
	public void location1Test() throws InterruptedException{
    	test = extent.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		boolean flag= homepage.verifyloc1();
		Assert.assertTrue(flag);
		test.info("PASS");

	}
	
	
	@Test (priority =4) 
	public void location2Test(){
    	test = extent.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		boolean flag= homepage.verifyloc2();
		Assert.assertTrue(flag);
		test.info("PASS");

	}
	
	
	@Test (priority =5) 
	public void languagedropdownTest(){
    	test = extent.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		boolean flag= homepage.verifylangselect();
		Assert.assertTrue(flag);
		
		
		
	}
	
	
	@Test (priority =6) 
	public void searchTest(){
    	test = extent.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		boolean flag= homepage.verifysearchDropdownBox();
		Assert.assertTrue(flag);
		test.info("PASS");

	}
	
	
	@Test  (priority =7) 
	public void checkamazonlink(){
    	test = extent.createTest(Thread.currentThread().getStackTrace()[1].getMethodName());
		String url = homepage.validateamazonurl();
		Assert.assertEquals(TestBase.prop.getProperty("url"), url);
		test.info("PASS");

	}
	
		
	@AfterSuite
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}
	

}
