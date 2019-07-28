package ee.ut.esi.group4.buildit.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",snippets = SnippetType.CAMELCASE,tags = {"@single"})
public class CucumberRunnerTest {
}
