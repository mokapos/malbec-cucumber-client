package cucumber;

import cucumber.node.Feature;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class JsonConverterTest {
    private String path = System.getProperty("user.dir")+"/src/test/resources/cucumber.json";

    @Test
    public void toCucumberObject() throws IOException {
        JsonConverter jsonConverter = new JsonConverter(path);
        Feature[] cucumber = jsonConverter.jsonToCucumberObject().getCucumber();

        Assert.assertNotNull(cucumber);
    }
}