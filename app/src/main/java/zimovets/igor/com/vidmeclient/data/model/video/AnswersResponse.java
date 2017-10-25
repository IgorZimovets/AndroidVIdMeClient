
package zimovets.igor.com.vidmeclient.data.model.video;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswersResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("videos")
    @Expose
    private List<Video> videos = null;


    public Boolean getStatus() {
        return status;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }


}
