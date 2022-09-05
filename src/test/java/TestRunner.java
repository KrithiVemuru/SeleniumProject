import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features/AssignmentScenarios.feature" }, plugin = { "pretty",
		"json:target/cucumber-report/index.json", "html:target/cucumber-report/index.html" })
public class TestRunner {
}