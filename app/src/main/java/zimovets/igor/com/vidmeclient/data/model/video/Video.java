
package zimovets.igor.com.vidmeclient.data.model.video;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("thumbnail_url")
    @Expose
    private String thumbnailUrl;
    @SerializedName("likes_count")
    @Expose
    private Integer likesCount;
    @SerializedName("formats")
    @Expose
    private List<Format> formats = null;

    public String getTitle() {
        return title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public List<Format> getFormats() {
        return formats;
    }

}
