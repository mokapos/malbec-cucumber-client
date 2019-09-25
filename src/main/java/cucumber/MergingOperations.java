package cucumber;

import cucumber.node.Element;
import cucumber.node.Feature;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MergingOperations {

    public Feature[] replace(Feature[] cucumber1, Feature[] cucumber2){

        for (Feature feature : cucumber1){

            List<Element> newElements = new ArrayList<>();
            for (Element element : feature.getElements()){

                Element elementFound = elementLookUp(element, cucumber2);

                if (elementFound!=null){//if found, put element found
                    newElements.add(elementFound);
                }else {
                    newElements.add(element);
                }

            }
            feature.setElements(newElements);
        }

        return cucumber1;
    }

    private Element elementLookUp(Element element, Feature[] cucumber) {
        for (Feature feature : cucumber){
            for (Element e : feature.getElements()){
                if (isElementEqual(element, e)){
                    return e;
                }
            }
        }

        return null;
    }

    private boolean isElementEqual(Element element1, Element element2) {
        if (element1.getType().equals(element2.getType())){
            if (element1.getType().equals("background")){
                return element1.getBackgroundId().equals(element2.getBackgroundId());
            }else {
                return element1.getId().equals(element2.getId());
            }
        }

        return false;
    }
}
