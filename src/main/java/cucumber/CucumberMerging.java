package cucumber;

import cucumber.node.Feature;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

public class CucumberMerging {

    public void replaceMethod(String pathCucumber1, String pathCucumber2, String pathMergedCucumber) throws IOException {
        JsonConverter jsonConverter_parent = new JsonConverter(pathCucumber1);
        JsonConverter jsonConverter_rerun = new JsonConverter(pathCucumber2);

            Feature[] parent = jsonConverter_parent.jsonToCucumberObject().assignBackgroundId().getCucumber();
            Feature[] rerun = jsonConverter_rerun.jsonToCucumberObject().assignBackgroundId().getCucumber();

            MergingOperations merging = new MergingOperations();
            Feature[] mergedCucumber = merging.replace(parent, rerun);

            JsonConverter jsonConverter_merged = new JsonConverter(mergedCucumber);
            jsonConverter_merged.cucumberObjectToJson(pathMergedCucumber);
    }

    public void joinMethod(String pathDir, String pathJoinedCucumber) throws IOException {

        File directory = new File(pathDir);
        if (!directory.isDirectory()){
            System.err.println(pathDir + " is not a directory");
        }else {
            Feature[] features = new Feature[]{};

            for (File cucumber : Objects.requireNonNull(directory.listFiles())){
                if (getExtensionFile(cucumber.getName()).equals("json")){
                    JsonConverter jsonConverter = new JsonConverter(cucumber.getAbsolutePath());
                    features = ArrayUtils.addAll(features, jsonConverter.jsonToCucumberObject().getCucumber());

                }
            }
            JsonConverter joinedCucumber = new JsonConverter(features);
            joinedCucumber.cucumberObjectToJson(pathJoinedCucumber);
        }

    }

    private String getExtensionFile(String fileName){
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i >= 0) {
            extension = fileName.substring(i+1);
        }

        return extension;
    }




}
