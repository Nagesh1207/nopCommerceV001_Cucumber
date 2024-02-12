package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
	    features = {"Features/Login.feature", "Features/Customer.feature"},
	    glue = "stepDefinations",
	    dryRun = false,
	    plugin = {"pretty", "html:target/cucumber-reports.html"},
	    tags="@sanity,@regression"
	)
		
		
		

public class TestRun {

}
