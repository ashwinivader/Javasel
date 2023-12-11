package com.amazon.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.amazon.qa.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//a[@aria-label='Amazon.in']")
	WebElement AmazonindiaLogo;
	
	@FindBy(xpath="//span[contains(text(),'Delivering')]")
	WebElement loc1;
	
	@FindBy(xpath="//span[contains(text(),'Update location')]")
	WebElement loc2;
	
	@FindBy(xpath="//*[@class='nav-icon']")
	WebElement searchDropdownBox;
	
	
	@FindBy(id="twotabsearchtextbox")
	WebElement Searchamazon;
		
	
	@FindBy(xpath="//span[contains(@class,'icp-nav-flag icp-nav-flag-in icp-nav-flag-lop')]")
	WebElement Iflag;
	
	@FindBy(xpath="//span[contains(@class,'nav-icon nav-arrow')][1]")
	WebElement langselect;
	
	//Initializing the Page Objects in constructor:
	public HomePage()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyAmazonindiaLogo()
	{   

		return(AmazonindiaLogo.isDisplayed());		
	}
	public boolean verifyloc1() throws InterruptedException
	{
     Thread.sleep(1000);
     return loc1.isDisplayed();	
	}
	
	public boolean verifyloc2()
	{
     return loc2.isDisplayed();	
	}
	
	public boolean verifysearchDropdownBox()
	{
     return searchDropdownBox.isDisplayed();	
	}
	
	public boolean verifyflag()
	{
     return Iflag.isDisplayed();	
	}
	
	public boolean verifylangselect()
	{
     return langselect.isDisplayed();	
	}
	
	
	//Actions:
	public String validateamazonurl(){
		return driver.getCurrentUrl();
				
	}
	
}
