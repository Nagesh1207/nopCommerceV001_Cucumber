package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddcustomerPage {
	
	public WebDriver ldriver;

    public AddcustomerPage(WebDriver rdriver) {
        
        ldriver = rdriver;
        PageFactory.initElements(ldriver, this);
    }

        By InkCustomers_menu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
        By InkCustomers_menuitem = By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
        By btnAddnew = By.xpath("//a[normalize-space()='Add new']"); //Add new
        By txtEmail = By.xpath("//input[@id='Email']");
        By txtPassword = By.xpath("//input[@id='Password']");
        By txtcustomerRoles = By.xpath("(//div[@class='k-multiselect-wrap k-floatwrap'])[2]");
        //By txtcustomerRoles = By.xpat("//By.xpath("//span[@title='delete']");
        By lstitemAdministrators = By.xpath("//li[contains(text(), 'Administrators')]");
        By lstitemForumModerators = By.xpath("//li[contains(text(),'Forum Moderators')]");
        By lstitemGuests = By.xpath("//li[contains(text(),'Guests')]");
        By lstitemRegistered = By.xpath("//li[contains (text(), 'Registered')]");
        By lstitemVendors =By.xpath("//li[contains(text(),'Vendors')]");
        
        By drpmgrofVendor =By.xpath("//*[@id='VendorId']");
        By rdMaleGender = By.id("Gender_Male"); 
        By rdFemaleGender = By.id("Gender_Female");
        
        By txtFirstName =By.xpath("//input[@id='FirstName']");
        By txtLastName = By.xpath("//input[@id='LastName']");
        By txtDob =By.xpath("//input[@id='DateOfBirth']");
        By txtCompanyName = By.xpath("//input[@id='Company']");
        By txtAdminContent = By.xpath("//textarea[@id='AdminComment']");
        By btnSave = By.xpath("//button[@name='save']");
        
        
        //Action Methods
        
        public String getPageTitle() {
        	return ldriver.getTitle();
        }
        
        public void clickOnCustomerMenu() {
        	ldriver.findElement(InkCustomers_menu).click();
        }
        
        public void clickOnCustomerMenuItem() {
        	ldriver.findElement(InkCustomers_menuitem).click();
        }

        public void clickOnAddnew() {
        	ldriver.findElement(btnAddnew).click();
        }
        
        public void setEmail(String email) {
        	ldriver.findElement(txtEmail).sendKeys(email);
        	
        }
        
        public void setPassword(String password) {
        	ldriver.findElement(txtPassword).sendKeys(password);
        }
        
        public void setCustomerRole(String role) throws InterruptedException {
        	if(!role.equals("Vendors")) 	// if role is vendors should not delete Register as per default
        	{
        		Thread.sleep(10000);
        		ldriver.findElement(By.xpath("//span[@title='delete']")).click();
        	}
        	
        	ldriver.findElement(txtcustomerRoles).click();
        	WebElement listitem;
        	Thread.sleep(2000);
        	if(role.equals("Administrators")) {
        		listitem = ldriver.findElement(lstitemAdministrators);
        	}
        	else if (role.equals("Forum Moderators")) {
        		listitem = ldriver.findElement(lstitemForumModerators);
        		
        	}
        	else if (role.equals("Guests")) {
        		listitem = ldriver.findElement(lstitemGuests);
        	}
        	else  {
        		listitem = ldriver.findElement(lstitemVendors);
        	}
        	//listitem.click();
        	//Thread.sleep(2000);
        	
        	JavascriptExecutor js = (JavascriptExecutor)ldriver;
        	js.executeScript("arguments[0].click();", listitem);
        }
        public void setManagerOfVendor(String value) {
        		Select drp=new Select(ldriver.findElement(drpmgrofVendor));
        		drp.selectByVisibleText(value);       	
        }
        public void setGender(String gender) {
        	if(gender.equals("Male")) {
        		ldriver.findElement(rdMaleGender).click();
        	}
        	else if(gender.equals("Female")) {
        		ldriver.findElement(rdFemaleGender).click();
        	}
        	else {
        		ldriver.findElement(rdFemaleGender).click(); //Default
        	}	
        }
       public void setFirstName(String fname) {
    	   ldriver.findElement(txtFirstName).sendKeys(fname);
       }
       public void setLastName(String lname) {
    	   ldriver.findElement(txtLastName).sendKeys(lname);
       }
       public void setDob(String dob) {
    	   ldriver.findElement(txtDob).sendKeys(dob);
       }
       public void setCompanyName(String comname) {
    	   ldriver.findElement(txtCompanyName).sendKeys(comname);
       }
       public void setSdminContent(String content) {
    	   ldriver.findElement(txtAdminContent).sendKeys(content);
       }
       public void clickOnSave() {
    	   ldriver.findElement(btnSave).click();
       }



        

















}
