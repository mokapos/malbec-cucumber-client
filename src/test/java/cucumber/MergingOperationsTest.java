package cucumber;

import cucumber.node.Element;
import cucumber.node.Feature;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MergingOperationsTest {

    @Test
    public void replace() throws IOException {
        MergingOperations mergingOperations = new MergingOperations();

        String pathCucumber1 = System.getProperty("user.dir") + "/src/test/resources/parent.json";
        String pathCucumber2 = System.getProperty("user.dir") + "/src/test/resources/rerun.json";

        Feature[] cucumber1 =  (new JsonConverter(pathCucumber1)).jsonToCucumberObject().getCucumber();
        Feature[] cucumber2 = (new JsonConverter(pathCucumber2)).jsonToCucumberObject().getCucumber();

        Feature[] cucumber = mergingOperations.replace(cucumber1,cucumber2);

        Element background = cucumber[0].getElements().get(2);
        Element scenario = cucumber[0].getElements().get(3);

        Element background2 = cucumber2[0].getElements().get(0);
        Element scenario2 = cucumber2[0].getElements().get(1);

        Assert.assertEquals(cucumber.length, cucumber1.length);
        Assert.assertEquals(background.getElementStatus(), background2.getElementStatus());
        Assert.assertEquals(background.getBackgroundId(), background2.getBackgroundId());
        Assert.assertEquals(scenario.getAfters().get(0).getEmbeddings(), scenario2.getAfters().get(0).getEmbeddings());
        Assert.assertEquals(scenario.getName(), scenario2.getName());
        Assert.assertEquals(scenario.getElementStatus(), scenario2.getElementStatus());
    }
}