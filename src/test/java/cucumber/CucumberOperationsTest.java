package cucumber;

import cucumber.node.Feature;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;


public class CucumberOperationsTest {

    private static Feature[] cucumber;
    private static String path = System.getProperty("user.dir")+"/src/test/resources/cucumber.json";

    @BeforeClass
    public static void getCucumber() throws IOException {
        cucumber = (new JsonConverter(path)).jsonToCucumberObject().getCucumber();
    }

    @Test
    public void getTotalScenario() {
        CucumberOperations cucumberOperations = new CucumberOperations();
        int totalScenario = cucumberOperations.getTotalScenario(cucumber);

        Assert.assertEquals(3,totalScenario);
    }

    @Test
    public void getFailedScenario() {
        CucumberOperations cucumberOperations = new CucumberOperations();
        int failedScenario = cucumberOperations.getFailedScenario(cucumber);

        Assert.assertEquals(2,failedScenario);
    }

    @Test
    public void getSuccessScenario() {
        CucumberOperations cucumberOperations = new CucumberOperations();
        int successScenario = cucumberOperations.getSuccessScenario(cucumber);

        Assert.assertEquals(1,successScenario);
    }

    @Test
    public void getTestDuration() {
        CucumberOperations cucumberOperations = new CucumberOperations();
        long durationTest = cucumberOperations.getTestDuration(cucumber);

        Assert.assertEquals(25611160039L, durationTest);
    }

    @Test
    public void getTesDuration_ContainsNull() throws IOException {
        String path = System.getProperty("user.dir")+"/src/test/resources/cucumber-empty-steps.json";
        Feature[] cucumber = (new JsonConverter(path)).jsonToCucumberObject().getCucumber();

        CucumberOperations cucumberOperations = new CucumberOperations();
        long durationTest = cucumberOperations.getTestDuration(cucumber);

        Assert.assertEquals(0, durationTest);
    }

    @Test
    public void getFailedScenario_emptySteps() throws IOException {
        String path = System.getProperty("user.dir")+"/src/test/resources/cucumber-empty-steps.json";
        Feature[] cucumber = (new JsonConverter(path)).jsonToCucumberObject().getCucumber();

        CucumberOperations cucumberOperations = new CucumberOperations();
        int failedScenario = cucumberOperations.getFailedScenario(cucumber);

        Assert.assertEquals(2,failedScenario);
    }

    @Test
    public void getDuration_AsFloat(){

        CucumberOperations cucumberOperations = new CucumberOperations();
        float durationTest = cucumberOperations.getTestDurationAsSeconds(cucumber);

        System.out.println("duration : " + durationTest);

    }


}