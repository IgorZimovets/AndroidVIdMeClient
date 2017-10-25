
package zimovets.igor.com.vidmeclient.data.model.video;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Format {

    @SerializedName("uri")
    @Expose
    private String uri;



    public String getUri() {
        return uri;
    }


}
