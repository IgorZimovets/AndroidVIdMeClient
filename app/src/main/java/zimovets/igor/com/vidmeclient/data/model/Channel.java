
package zimovets.igor.com.vidmeclient.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel {

    @SerializedName("channel_id")
    @Expose
    private String channelId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("is_default")
    @Expose
    private Boolean isDefault;
    @SerializedName("hide_suggest")
    @Expose
    private Boolean hideSuggest;
    @SerializedName("show_unmoderated")
    @Expose
    private Boolean showUnmoderated;
    @SerializedName("nsfw")
    @Expose
    private Boolean nsfw;
    @SerializedName("follower_count")
    @Expose
    private Integer followerCount;
    @SerializedName("video_count")
    @Expose
    private Integer videoCount;
    @SerializedName("full_url")
    @Expose
    private String fullUrl;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("avatar_ai")
    @Expose
    private String avatarAi;
    @SerializedName("cover_url")
    @Expose
    private String coverUrl;
    @SerializedName("cover_ai")
    @Expose
    private String coverAi;

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Boolean getHideSuggest() {
        return hideSuggest;
    }

    public void setHideSuggest(Boolean hideSuggest) {
        this.hideSuggest = hideSuggest;
    }

    public Boolean getShowUnmoderated() {
        return showUnmoderated;
    }

    public void setShowUnmoderated(Boolean showUnmoderated) {
        this.showUnmoderated = showUnmoderated;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(Integer videoCount) {
        this.videoCount = videoCount;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarAi() {
        return avatarAi;
    }

    public void setAvatarAi(String avatarAi) {
        this.avatarAi = avatarAi;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCoverAi() {
        return coverAi;
    }

    public void setCoverAi(String coverAi) {
        this.coverAi = coverAi;
    }

}
