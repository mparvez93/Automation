package Posit_Automation.Take_Home;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PositCloudTest {
	WebDriver driver;

    @SuppressWarnings("deprecation")
	@BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Updated Chrome Webdriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://posit.cloud");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void createSpaceAndProject() throws InterruptedException {
    	
    	//login
    	WebElement login = driver.findElement(By.xpath("//*[text()='Log In']"));
    	login.click();
    	System.out.println("Clicked on Login Button");
    	
    	//Click in email text field 
    	WebElement emailTextfield = driver.findElement(By.name("email"));
    	emailTextfield.click();
    	System.out.println("Clicked on Email Text Field");
    	
    	//creating string to store email and password
    	String email = "mohammad.p.parvez@gmail.com";
    	emailTextfield.sendKeys(email);
    	System.out.println("String Created for email");
    	
    	
    	//click on continue button
    	WebElement continueButton = driver.findElement(By.xpath("//*[text()='Continue']"));
    	continueButton.click();
    	System.out.println("Clicked on Continue Button");
    	
    	//password field
    	WebElement passwordfield = driver.findElement(By.xpath("//input[@name='password']"));
    	passwordfield.click();
    	System.out.println("Clicked on Password Field");
    	
    	//Password
    	String password = "Brooklyn718";
    	passwordfield.sendKeys(password);
    	System.out.println("Entered password in password field");
    	
    	//click on login
    	WebElement loginbutton = driver.findElement(By.xpath("//button[@type='submit']"));
    	loginbutton.click();
    	System.out.println("Clicked on Lgoin Button");
    	
    	
    	
    	
        // Click on Spaces tab
        WebElement spacesTab = driver.findElement(By.xpath("//*[text()='New Space']"));
        spacesTab.click();
        System.out.println("Clicked on Space tab");
        
        //Enter name for space
        WebElement spacename = driver.findElement(By.id("name"));
        spacename.sendKeys("New Project");
        System.err.println("Name Entered for new Space");

        // Click on Create Space button
        WebElement createSpaceButton = driver.findElement(By.xpath("//button[@type ='submit']"));
        createSpaceButton.click();
        System.err.println("Create Space Button has been clicked");
        
       
        //We are using Try, Catch because the stale element- element not found. 
        //Stale = not moving
        //Compound element 
        //It is trying 1 more time
        
        try{
        	
        //Creating a Project within the space
        WebElement newProjectbutton=driver.findElement(By.xpath("//button[@class='action menuDropDown withActionTitle imageRight alwaysShowTitle']"));
        newProjectbutton.click();
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
        	//Creating a Project within the space
            WebElement newProjectbutton=driver.findElement(By.xpath("//button[@class='action menuDropDown withActionTitle imageRight alwaysShowTitle']"));
            newProjectbutton.click();
        }
        
        try{
        
        
        //click on New rStudioproject
        WebElement NewrStudioProject=driver.findElement(By.xpath("//button[@class='action newRStudioProject']"));
        NewrStudioProject.click();
        
        }
        catch(org.openqa.selenium.StaleElementReferenceException ex)
        {
            //click on New rStudioproject
            WebElement NewrStudioProject=driver.findElement(By.xpath("//button[@class='action newRStudioProject']"));
            NewrStudioProject.click();
          }
    
    
        
        // Verify that RStudio IDE loads
       
  
        //searched up iframe because there were multiple iframes on the page
        // I switched the iframe to contentIFrame to locate the Rstudio IDE element

        driver.switchTo().frame("contentIFrame");
        System.out.println("Frame has been switched to contentIFrame");
        
        //Creating rStuiod IDE element
        WebElement rStuiodconsole = driver.findElement(By.id("gwt-uid-52"));
       
      //Validating that it is existing in the page
       
        if( rStuiodconsole.isDisplayed() == true){
        	System.out.println("RStudio is showing");
        }
        
 
}
        
        
    @AfterTest
    public void tearDown() throws InterruptedException {
    
    	//process of deleting existing account because with the account we have, we are only allowed to have 1 project
    	
    	driver.switchTo().parentFrame();
    	System.out.println("Frame has been switched to parentFrame");
    	
        //Clicking on account of the user which is located on the top right corner
    	WebElement account = driver.findElement(By.className("userInitialsIconContainer"));
    	account.click();
    	
    	//From the drop down menu- Account has been selected
    	WebElement account1 = driver.findElement(By.xpath("//a[@class = 'menuItem accountPersonal primary']"));
    	account1.click();
    
    	//Once account has been selected- takes to another page- where we see a menu and select "space" page
    	WebElement spaces = driver.findElement(By.cssSelector("#rStudioHeader > div.band > div.innards.bandContent > nav > ul > li:nth-child(3) > a"));
    	spaces.click();
    	
    	//Once on the space pace- select the project
    	WebElement projectSelect = driver.findElement(By.xpath("//td[text()='New Project']"));
    	projectSelect.click();
    	
    	//Delete the project
    	WebElement deleteSpace = driver.findElement(By.id("spaceDetailsDeleteSpaceButton"));
    	deleteSpace.click();
    	
    	//Enter credentials for delete space test
    	WebElement deleteSpacetest = driver.findElement(By.id("deleteSpaceTest"));
    	deleteSpacetest.sendKeys(" Delete New Project");
    	
    	Thread.sleep(1000);
    
    	//Click on delete button
    	WebElement deleteButton = driver.findElement(By.id("deleteSpaceSubmit"));
    	deleteButton.click();
    	System.err.println("Clicked on delete button");
    	
    	
    	
    	Thread.sleep(5000);
    	
    	
        driver.quit();
  
    
    }
}
	


	
	

