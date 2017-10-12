
package zimovets.igor.com.vidmeclient.data.model.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Page {

    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("currentMarker")
    @Expose
    private Object currentMarker;
    @SerializedName("currentMarkerDay")
    @Expose
    private Object currentMarkerDay;
    @SerializedName("currentMarkerDate")
    @Expose
    private Object currentMarkerDate;
    @SerializedName("nextMarker")
    @Expose
    private String nextMarker;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getCurrentMarker() {
        return currentMarker;
    }

    public void setCurrentMarker(Object currentMarker) {
        this.currentMarker = currentMarker;
    }

    public Object getCurrentMarkerDay() {
        return currentMarkerDay;
    }

    public void setCurrentMarkerDay(Object currentMarkerDay) {
        this.currentMarkerDay = currentMarkerDay;
    }

    public Object getCurrentMarkerDate() {
        return currentMarkerDate;
    }

    public void setCurrentMarkerDate(Object currentMarkerDate) {
        this.currentMarkerDate = currentMarkerDate;
    }

    public String getNextMarker() {
        return nextMarker;
    }

    public void setNextMarker(String nextMarker) {
        this.nextMarker = nextMarker;
    }

}
