package zimovets.igor.com.vidmeclient.data.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OAuthTokenBasicAuth {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("auth")
    @Expose
    private Auth auth;

    public Boolean getStatus() {
        return status;
    }

    public Auth getAuth() {
        return auth;
    }



}