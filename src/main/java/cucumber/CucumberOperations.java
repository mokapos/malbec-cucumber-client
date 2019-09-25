package cucumber;

import cucumber.node.Element;
import cucumber.node.Feature;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CucumberOperations {

    public int getTotalScenario(Feature[] cucumber){
        int totalScenario = 0;

        for (int i = 0 ; i < cucumber.length ; i++){
            List<Element> elements = cucumber[i].getElements();

            for (Element e : elements){
                if (e.getType().equals("scenario")){
                    totalScenario += 1;
                }
            }
        }

        return totalScenario;
    }

    public int getFailedScenario(Feature[] cucumber){
        int failedScenario = 0;

        for (int i = 0 ; i < cucumber.length ; i++){
            List<Element> elements = cucumber[i].getElements();

            for (Element e : elements){
                if (e.getType().equals("scenario") && e.isElementFailed()){
                    failedScenario +=1;
                }
            }
        }

        return failedScenario;

    }

    public int getSuccessScenario(Feature[] cucumber){
        int successScenario = 0;

        for (int i = 0 ; i < cucumber.length ; i++){
            List<Element> elements = cucumber[i].getElements();

            for (Element e : elements){
                if (e.getType().equals("scenario") && !e.isElementFailed()){
                    successScenario +=1;
                }
            }
        }

        return successScenario;
    }

    public List<Element> getListFailedScenario(Feature[] cucumber){
        List<Element> failedScenarios = new ArrayList<>();

        for (Feature f : cucumber){
            for (Element e : f.getElements()){
                if (e.getType().equals("scenario") && e.getElementStatus().equals("failed")){
                    failedScenarios.add(e);
                }
            }
        }

        return failedScenarios;
    }

    public long getTestDuration(Feature[] cucumber){
        long duration = 0;
        for (Feature feature : cucumber){
            for (Element e : feature.getElements()){
                duration = duration + e.getDuration();
            }
        }

        return duration;
    }

    public float getTestDurationAsSeconds(Feature[] cucumber){
        return getTestDuration(cucumber)/1000000000f;

    }

    public int getSuccessScenario(Feature cucumber){
        int successScenario = 0;

        List<Element> elements = cucumber.getElements();

        for (Element e : elements){
            if (e.getType().equals("scenario") && !e.isElementFailed()){
                successScenario +=1;
            }
        }

        return successScenario;
    }

    public int getFailedScenario(Feature cucumber){
        int failedScenario = 0;

        List<Element> elements = cucumber.getElements();

        for (Element e : elements){
            if (e.getType().equals("scenario") && e.isElementFailed()){
                failedScenario +=1;
            }
        }

        return failedScenario;
    }

}
