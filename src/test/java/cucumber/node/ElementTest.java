package cucumber.node;

import cucumber.JsonConverter;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class ElementTest {
    private static Feature[] cucumber;
    private static String path = System.getProperty("user.dir")+"/src/test/resources/cucumber.json";

    @BeforeClass
    public static void getCucumber() throws IOException {
        cucumber = (new JsonConverter(path)).jsonToCucumberObject().getCucumber();
    }

    @Test
    public void isElementFailed() {
        List<Element> elements = cucumber[0].getElements();
        boolean result1 = elements.get(0).isElementFailed();
        boolean result2 = elements.get(1).isElementFailed();

        Assert.assertTrue(result1);
        Assert.assertFalse(result2);
    }

    @Test
    public void getElementStatus() {
        List<Element> elements = cucumber[0].getElements();
        String status1 = elements.get(0).getElementStatus();
        String status2 = elements.get(1).getElementStatus();
        String status3 = elements.get(2).getElementStatus();

        Assert.assertEquals("failed", status1);
        Assert.assertEquals("passed", status2);
        Assert.assertEquals("failed", status3);
    }

    @Test
    public void getTagWithPrefix() {//@TEST_
        List<Element> elements = cucumber[0].getElements();
        Element element = elements.get(2);
        List<String> tags = element.getTagWithPrefix("@TEST_");

        Assert.assertTrue(tags.get(0).startsWith("@TEST_"));
    }

    @Test
    public void getTagsAsString() {
        List<Element> elements = cucumber[0].getElements();
        Element element = elements.get(2);
        String tags = element.getTagsAsString();

        Assert.assertEquals("@TEST_PG-1123;@TEST123", tags);
    }
}