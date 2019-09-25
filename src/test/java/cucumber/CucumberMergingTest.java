package cucumber;

import org.junit.Test;

import java.io.IOException;

public class CucumberMergingTest {

    //@Test
    public void replaceMethod() throws IOException {
        CucumberMerging cucumberMerging = new CucumberMerging();
        String pathCucumber1 = System.getProperty("user.dir") + "/src/test/resources/parent.json";
        String pathCucumber2 = System.getProperty("user.dir") + "/src/test/resources/rerun.json";

        cucumberMerging.replaceMethod(pathCucumber1, pathCucumber2,System.getProperty("user.dir") + "/mergedcucumber.json");
    }

    //@Test
    public void joinMethod() throws IOException {
        CucumberMerging cucumberMerging = new CucumberMerging();
        String fileDirPath = System.getProperty("user.dir")+"/test";
        String result = System.getProperty("user.dir") + "/joined-cucumber.json";

        cucumberMerging.joinMethod(fileDirPath, result);
    }
}