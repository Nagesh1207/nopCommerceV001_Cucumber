package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;

public class SearchCustomerPage {
	
	public WebDriver ldriver;
	WaitHelper waithelper;
	
	public SearchCustomerPage(WebDriver rdriver) {
		ldriver=rdriver;
		PageFactory.initElements(ldriver, this);
		waithelper=new WaitHelper(ldriver);
	}
	
	@FindBy(how=How.ID, using="SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(how=How.ID, using="SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(how=How.ID, using="SearchLastName")
	@CacheLookup
	WebElement txtLastName;
	
	@FindBy(how=How.ID, using="SearchMonthOfBirth")
	@CacheLookup
	WebElement drpdobMonth;
	
	@FindBy(how=How.ID, using="SearchDayOfBirth")
	@CacheLookup
	WebElement drpdobDay;
	
	@FindBy(how=How.ID, using="SearchCompany")
	@CacheLookup
	WebElement txtCompany;
	
	@FindBy(how=How.XPATH,using="//div[@class='k-multiselect-wrap k-floatwrap']")
	@CacheLookup
	WebElement txtCustomerRole;
	
	@FindBy(how=How.XPATH, using="//li[contains(text(),'Administrators')]")
	@CacheLookup
	WebElement lstitemAdministrators;
	
	@FindBy(how=How.XPATH,using="//li[contains(text(),'Forum Moderators')]")
	@CacheLookup
	WebElement lstitemForumModerators;
	
	@FindBy(how=How.XPATH, using="//li[contains(text(),'Guests')]")
	@CacheLookup
	WebElement lstitemGuests;
	
	@FindBy(how=How.ID, using="search-customers")
	@CacheLookup
	WebElement btnSearch;
	
	@FindBy(how=How.XPATH, using="//div[@id='customers-grid_wrapper']")
	@CacheLookup
	WebElement tableSearchResult;
	
	@FindBy(how=How.XPATH, using="//table[@id='customers-grid']")			////table[@id='customers-grid']
	@CacheLookup
	WebElement table;
	
	@FindBy(how=How.XPATH, using="//table[@id='customers-grid']//tbody/tr")
	@CacheLookup
	List<WebElement> tableRows;
	
	@FindBy(how=How.XPATH, using="//table[@id='customers-grid']//tbody/tr/td")
	@CacheLookup
	List<WebElement> tableColumns;
	
	public void setEmail(String email) {
		waithelper.waitForElement(txtEmail, Duration.ofSeconds(10));
		txtEmail.clear();
		txtEmail.sendKeys(email);
			}
	
	
	public void setFirstName(String fname) {
		waithelper.waitForElement(txtFirstName, Duration.ofSeconds(10));
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		waithelper.waitForElement(txtLastName, Duration.ofSeconds(10));
		txtLastName.clear();
		txtLastName.sendKeys(lname);
	}
	
	public void clicksearch() {
		btnSearch.click();
		waithelper.waitForElement(btnSearch, Duration.ofSeconds(10));
	}
	
	public int getNoOfRows() {
		return(tableRows.size());
	}
	
	public int getNoOfColumns() {
		return(tableColumns.size());
	}	
	
	public boolean searchCustomerByEmail(String email) {
		
		boolean flag=false;
		
		for(int i=1;i<=getNoOfRows();i++) {
			String emailid=table.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[2]")).getText();
			System.out.print(emailid);
			
			if(emailid.equals("victoria_victoria@nopCommerce.com")) {
				flag=true;
			}
		}
		return flag;
	}
	
	public boolean searchCustomerByName(String email) {
		
		boolean flag=false;
		
		for(int i=1;i<=getNoOfRows();i++) {
			String name=table.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+i+"]/td[3]")).getText();
			System.out.print(name);
			String names[]=name.split (" ");		//seperating fname and lname
			if(names[0].equals("Victoria") && names[1].equals("Terces")) {
				flag=true;
			}
		}
		return flag;
	}
	
	
	
	
	
	
	
}
