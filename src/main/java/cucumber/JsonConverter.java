package cucumber;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.node.Element;
import cucumber.node.Feature;

import java.io.File;
import java.io.IOException;

public class JsonConverter {

    private String path;
    private Feature[] cucumber;

    public JsonConverter(String path){
        this.path = path;
    }

    public JsonConverter(Feature[] cucumber){
        this.cucumber = cucumber;
    }

    public JsonConverter jsonToCucumberObject() throws IOException {
        File file = new File(path);
        ObjectMapper objectMapper = new ObjectMapper();
        this.cucumber = objectMapper.readValue(file, Feature[].class);

        return this;

    }

    public void cucumberObjectToJson(String pathDestination){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        try {
            objectMapper.writeValue(new File(pathDestination), cucumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JsonConverter assignBackgroundId(){
        for (Feature feature : cucumber){
            for (int i = 0 ; i < feature.getElements().size(); i++){
                Element element = feature.getElements().get(i);

                if (element.getType().equals("background")){
                    try {
                        int y = i + 1;
                        while (!feature.getElements().get(y).getType().equals("scenario")&&y<=feature.getElements().size()){
                            y++;
                        }
                        String backgroundId = feature.getElements().get(y).getId();
                        element.setBackgroundId(backgroundId);
                    }catch (Exception e){
                        element.setBackgroundId(null);
                    }
                }
            }
        }

        return this;
    }

    public String getPath() {
        return path;
    }

    public Feature[] getCucumber() {
        return cucumber;
    }
}
