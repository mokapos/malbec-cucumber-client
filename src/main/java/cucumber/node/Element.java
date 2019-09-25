package cucumber.node;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Element {

    @JsonProperty("before")
    private List<Before> befores = new ArrayList<>();
    private int line;
    private String name;
    private String description;
    private String id;
    @JsonProperty("after")
    private List<After> afters = new ArrayList<>();
    private String type;
    private String keyword;
    private List<Step> steps = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();
    @JsonIgnore
    private String backgroundId = "";

    public List<Before> getBefores() {
        return befores;
    }

    public void setBefores(List<Before> befores){
        this.befores = befores;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<After> getAfters() {
        return afters;
    }

    public void setAfters(List<After> afters) {
        this.afters = afters;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @JsonIgnore
    public boolean isElementFailed(){
        List<Step> steps = getSteps();

        if (steps.size()==0){
            return true;
        }

        for (Step step : steps){
            if (!step.getResult().getStatus().equalsIgnoreCase("passed")){
                return true;
            }
        }

        return false;
    }

    @JsonIgnore
    public String getElementStatus(){
        List<Step> steps = getSteps();

        if (steps.size()==0){
            return "failed";
        }

        for (Step step : steps){
            if (!step.getResult().getStatus().equalsIgnoreCase("passed")){
                return "failed";
            }
        }

        return "passed";
    }

    @JsonIgnore
    public List<String> getTagWithPrefix(String prefix){
        List<Tag> tags = getTags();
        List<String> tagWithPrefix = new ArrayList<>();

        for (Tag tag : tags){
            if (tag.getName().startsWith(prefix)){
                tagWithPrefix.add(tag.getName());
            }
        }

        return tagWithPrefix;
    }

    @JsonIgnore
    public String getTagsAsString(){
        List<Tag> tags = getTags();
        String tagsAsString = "";

        for (Tag tag : tags){
            tagsAsString = String.format("%s;%s", tagsAsString, tag.getName());
        }

        return tagsAsString.substring(1);
    }

    @JsonIgnore
    /**
     *
     * @return as nanoseconds
     */
    public long getDuration(){
        long beforeDuration = 0;

        for (Before before : getBefores()){
            beforeDuration += before.getDuration();
        }

        long stepsDuration = 0;
        for (Step step : getSteps()){
            stepsDuration += step.getDuration();
        }

        long afterDuration = 0;
        for (After after : getAfters()){
            afterDuration += after.getDuration();
        }

        return beforeDuration + stepsDuration + afterDuration;

    }

    public String getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(String backgroundId) {
        if (this.type.equals("background")){
            this.backgroundId = backgroundId;
        }

    }
}
