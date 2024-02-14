package stepDefinations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Step extends BaseClass {
	@Before
	public void setup() throws IOException {
		configProp=new Properties();
		FileInputStream configProfile=new FileInputStream("config.properties");
		configProp.load(configProfile);
		
		logger=Logger.getLogger("nopCommerceV001_Cucumber") ;		//Added logger
		PropertyConfigurator.configure("log4j.properties");			//Added logger
		// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
		String br = configProp.getProperty("browser");
		
		if(br.equals("chrome")) {
			logger.info("************launching chrome browser*************");
		System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
		driver=new ChromeDriver();

		}
		
		else if(br.equals("firefox")) {
			String driverLoc= configProp.getProperty("firefoxpath");
			String installDir = "/snap/firefox/current/usr/lib/firefox";
			String binaryLoc = new File(installDir, "firefox").getPath();			
			logger.info("************launching firefox browser*************");
			System.setProperty("webdriver.chrome.driver", driverLoc);
			GeckoDriverService service = new GeckoDriverService.Builder().usingDriverExecutable(new File(driverLoc)).build();

			FirefoxOptions options = new FirefoxOptions();
			options.setBinary(binaryLoc);			
			driver=new FirefoxDriver(service, options);

			}
		
		

		else if(br.equals("ie")) {
			logger.info("************launching ie browser*************");
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("iepathpath"));
			driver=new InternetExplorerDriver();

			}
		
			
	}
	

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {
//		logger=Logger.getLogger("nopCommerceV001_Cucumber") ;		//Added logger
//		PropertyConfigurator.configure("log4j.properties");			//Added logger
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver");
//		driver=new ChromeDriver();
//		logger.info("************launching browser*************");
		lp=new LoginPage(driver);
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("************launching url*************");
		driver.manage().window().maximize();
		driver.get(url);
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("************inserting login details*************");
		lp.setUserName(email);
		lp.setPassword(password);
	}

	@When("Click on Login")
	public void click_on_login() {
		logger.info("************click on login*************");
		lp.clickLogin();
	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String title) {
		
		if(driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			Assert.assertTrue(false);			
		}
		else {
			Assert.assertEquals(title, driver.getTitle());
		}
	}

	@When("User click on log out link")
	public void user_click_on_log_out_link() throws InterruptedException {
		lp.clickLogout();
		Thread.sleep(2000);
	}

	@Then("close browser")
	public void close_browser() {
		driver.close();
		
	}
	
	// customer feature step defination 
	
	@Then("user can view Dashbord")
	public void user_can_view_dashbord() {
		addCust= new AddcustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
	}
	@When("user click on customer menu")
	public void user_click_on_customer_menu() throws InterruptedException {
		Thread.sleep(2000);
		logger.info("************user click on customer menu*************");
		addCust.clickOnCustomerMenu();

	}
	@When("click on customer Menu Item")
	public void click_on_customer_menu_item() throws InterruptedException {
		Thread.sleep(2000);
		logger.info("************user click on customer menu item*************");
		addCust.clickOnCustomerMenuItem();

	}
	@When("click on add new button")
	public void click_on_add_new_button() {
		logger.info("************user click add new*************");
		addCust.clickOnAddnew();

	}
	@Then("user can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		logger.info("************checking validation************");
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());

	}
	@When("user enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		String email=randomeString()+"@gamil.com";
		logger.info("************creating new*************");
		addCust.setEmail(email);
		addCust.setPassword("test123");
		//Registered - default
		//The customer cannot be in both 'Guests' and 'Registered' customer role
		//Add the customer to 'Guests' or 'Registered' customer role
		addCust.setCustomerRole("Guests");
		Thread.sleep(2000);
		
		addCust.setManagerOfVendor("Vendor 2");
		addCust.setGender("Male");
		addCust.setFirstName("Rocky");
		addCust.setLastName("Bhai");
		addCust.setDob("9/02/1994");   //Format D/MM/YYYY
		addCust.setCompanyName("BusyQa");
		addCust.setSdminContent("This is cucumber.............");
		
	}
	@When("click on save button")
	public void click_on_save_button() {
		addCust.clickOnSave();
	}
	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		Assert.assertTrue(driver.findElement(By.tagName("body"))
				.getText().contains("The new customer has been added successfully"));
	}
	
	
	// Search customer by Email
	
	@When("Enter customer Email")
	public void enter_customer_email() {
		searchCust=new SearchCustomerPage(driver);
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
	}
	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clicksearch();
		Thread.sleep(3000);
	}
	@Then("User should found Email in the search table")
	public void user_should_found_email_in_the_search_table() {
		boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		logger.info(status);
		Assert.assertEquals(true, status);
	}
	
	
	// Search customer by Name and Last Name
	
	@When("Enter customer FirstName")
	public void enter_customer_first_name() {
		searchCust = new SearchCustomerPage(driver);
		searchCust.setFirstName("Victoria");
	}
	@When("Enter customer LastName")
	public void enter_customer_last_name() {
		searchCust.setLastName("Terces");
	}
	@Then("User should found Name in the search table")
	public void user_should_found_name_in_the_search_table() {
		boolean status=searchCust.searchCustomerByName("Victoria Terces");
		logger.info(status);
		Assert.assertEquals(true, status);
	}
	

	
}
