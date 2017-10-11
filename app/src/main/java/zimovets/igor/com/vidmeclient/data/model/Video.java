
package zimovets.igor.com.vidmeclient.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("video_id")
    @Expose
    private String videoId;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("original_size")
    @Expose
    private String originalSize;
    @SerializedName("full_url")
    @Expose
    private String fullUrl;
    @SerializedName("embed_url")
    @Expose
    private String embedUrl;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("duration")
    @Expose
    private Double duration;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("date_stored")
    @Expose
    private String dateStored;
    @SerializedName("date_completed")
    @Expose
    private String dateCompleted;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("view_count")
    @Expose
    private Integer viewCount;
    @SerializedName("share_count")
    @Expose
    private Integer shareCount;
    @SerializedName("version")
    @Expose
    private Integer version;
    @SerializedName("nsfw")
    @Expose
    private Boolean nsfw;
    @SerializedName("moderated")
    @Expose
    private Boolean moderated;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("thumbnail_url")
    @Expose
    private String thumbnailUrl;
    @SerializedName("thumbnail_gif")
    @Expose
    private String thumbnailGif;
    @SerializedName("thumbnail_gif_url")
    @Expose
    private String thumbnailGifUrl;
    @SerializedName("thumbnail_ai")
    @Expose
    private String thumbnailAi;
    @SerializedName("storyboard")
    @Expose
    private String storyboard;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("likes_count")
    @Expose
    private Integer likesCount;
    @SerializedName("channel_id")
    @Expose
    private Object channelId;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("private")
    @Expose
    private Boolean _private;
    @SerializedName("scheduled")
    @Expose
    private Boolean scheduled;
    @SerializedName("subscribed_only")
    @Expose
    private Boolean subscribedOnly;
    @SerializedName("date_published")
    @Expose
    private String datePublished;
    @SerializedName("latitude")
    @Expose
    private Integer latitude;
    @SerializedName("longitude")
    @Expose
    private Integer longitude;
    @SerializedName("place_id")
    @Expose
    private Object placeId;
    @SerializedName("place_name")
    @Expose
    private Object placeName;
    @SerializedName("colors")
    @Expose
    private String colors;
    @SerializedName("reddit_link")
    @Expose
    private Object redditLink;
    @SerializedName("youtube_override_source")
    @Expose
    private Object youtubeOverrideSource;
    @SerializedName("complete")
    @Expose
    private Object complete;
    @SerializedName("complete_url")
    @Expose
    private String completeUrl;
    @SerializedName("watching_count")
    @Expose
    private Integer watchingCount;
    @SerializedName("hot_score")
    @Expose
    private Double hotScore;
    @SerializedName("is_featured")
    @Expose
    private Boolean isFeatured;
    @SerializedName("date_featured")
    @Expose
    private String dateFeatured;
    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("channel")
    @Expose
    private Channel channel;
    @SerializedName("formats")
    @Expose
    private List<Format> formats = null;
    @SerializedName("score_modifier")
    @Expose
    private Integer scoreModifier;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOriginalSize() {
        return originalSize;
    }

    public void setOriginalSize(String originalSize) {
        this.originalSize = originalSize;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    public String getEmbedUrl() {
        return embedUrl;
    }

    public void setEmbedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateStored() {
        return dateStored;
    }

    public void setDateStored(String dateStored) {
        this.dateStored = dateStored;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getNsfw() {
        return nsfw;
    }

    public void setNsfw(Boolean nsfw) {
        this.nsfw = nsfw;
    }

    public Boolean getModerated() {
        return moderated;
    }

    public void setModerated(Boolean moderated) {
        this.moderated = moderated;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailGif() {
        return thumbnailGif;
    }

    public void setThumbnailGif(String thumbnailGif) {
        this.thumbnailGif = thumbnailGif;
    }

    public String getThumbnailGifUrl() {
        return thumbnailGifUrl;
    }

    public void setThumbnailGifUrl(String thumbnailGifUrl) {
        this.thumbnailGifUrl = thumbnailGifUrl;
    }

    public String getThumbnailAi() {
        return thumbnailAi;
    }

    public void setThumbnailAi(String thumbnailAi) {
        this.thumbnailAi = thumbnailAi;
    }

    public String getStoryboard() {
        return storyboard;
    }

    public void setStoryboard(String storyboard) {
        this.storyboard = storyboard;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(Integer likesCount) {
        this.likesCount = likesCount;
    }

    public Object getChannelId() {
        return channelId;
    }

    public void setChannelId(Object channelId) {
        this.channelId = channelId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getPrivate() {
        return _private;
    }

    public void setPrivate(Boolean _private) {
        this._private = _private;
    }

    public Boolean getScheduled() {
        return scheduled;
    }

    public void setScheduled(Boolean scheduled) {
        this.scheduled = scheduled;
    }

    public Boolean getSubscribedOnly() {
        return subscribedOnly;
    }

    public void setSubscribedOnly(Boolean subscribedOnly) {
        this.subscribedOnly = subscribedOnly;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Object getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Object placeId) {
        this.placeId = placeId;
    }

    public Object getPlaceName() {
        return placeName;
    }

    public void setPlaceName(Object placeName) {
        this.placeName = placeName;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public Object getRedditLink() {
        return redditLink;
    }

    public void setRedditLink(Object redditLink) {
        this.redditLink = redditLink;
    }

    public Object getYoutubeOverrideSource() {
        return youtubeOverrideSource;
    }

    public void setYoutubeOverrideSource(Object youtubeOverrideSource) {
        this.youtubeOverrideSource = youtubeOverrideSource;
    }

    public Object getComplete() {
        return complete;
    }

    public void setComplete(Object complete) {
        this.complete = complete;
    }

    public String getCompleteUrl() {
        return completeUrl;
    }

    public void setCompleteUrl(String completeUrl) {
        this.completeUrl = completeUrl;
    }

    public Integer getWatchingCount() {
        return watchingCount;
    }

    public void setWatchingCount(Integer watchingCount) {
        this.watchingCount = watchingCount;
    }

    public Double getHotScore() {
        return hotScore;
    }

    public void setHotScore(Double hotScore) {
        this.hotScore = hotScore;
    }

    public Boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(Boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getDateFeatured() {
        return dateFeatured;
    }

    public void setDateFeatured(String dateFeatured) {
        this.dateFeatured = dateFeatured;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public List<Format> getFormats() {
        return formats;
    }

    public void setFormats(List<Format> formats) {
        this.formats = formats;
    }

    public Integer getScoreModifier() {
        return scoreModifier;
    }

    public void setScoreModifier(Integer scoreModifier) {
        this.scoreModifier = scoreModifier;
    }

}
