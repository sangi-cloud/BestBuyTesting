package bestbus.testngtestrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
@RunWith (Cucumber.class)
@CucumberOptions(features="src/test/resources", glue="bestbuy.createaccount")

public class TestngRunner extends AbstractTestNGCucumberTests{

}
