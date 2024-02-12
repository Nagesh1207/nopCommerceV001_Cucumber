Feature: Customers

Background: Below are the commen steps for each scenerio
	 Given User Launch Chrome browser
	When User opens URL "https://admin-demo.nopcommerce.com/"
	And User enters Email as "admin@yourstore.com" and Password as "admin"
	And Click on Login
	Then user can view Dashbord
	  When user click on customer menu                                      
  And click on customer Menu Item 

@sanity
Scenario: Add new Customer
	And click on add new button
	Then user can view Add new customer page
	When user enter customer info
	And click on save button
	Then user can view confirmation message "The new customer has been added successfully"
	And close browser
	
@regression	
Scenario: Search Customer by EmailId                                                                                                                         
  And Enter customer Email                                               
  When Click on search button                                            
  Then User should found Email in the search table                       
  And close browser
  
@regression
Scenario: Search Customer by Name                                                                                   
  And Enter customer FirstName
  And Enter customer LastName                                               
  When Click on search button                                            
  Then User should found Name in the search table                       
  And close browser	
  
  
  