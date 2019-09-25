package cucumber.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.concurrent.TimeUnit;

@JsonIgnoreProperties(ignoreUnknown = true)
public class After {

    private Result result;
    private Match match;
    private List<Embedding> embeddings;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @JsonIgnore
    /**
     * @return as nano seconds
     */
    public float getDuration(){
        return this.result.getDuration();
    }

    public List<Embedding> getEmbeddings() {
        return embeddings;
    }

    public void setEmbeddings(List<Embedding> embeddings) {
        this.embeddings = embeddings;
    }
}
