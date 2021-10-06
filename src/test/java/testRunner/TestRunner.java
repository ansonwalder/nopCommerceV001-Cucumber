package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./Features", glue = "stepDefinitions", monochrome = true, 
plugin = { "pretty","html:target/htmlReport.html"},
stepNotifications=true,
dryRun = false
//tags =  "@regression or @Sanity"
)
public class TestRunner {

}