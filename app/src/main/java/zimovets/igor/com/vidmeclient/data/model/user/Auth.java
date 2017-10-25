package zimovets.igor.com.vidmeclient.data.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Auth {

    @SerializedName("token")
    @Expose
    private String token;


    public String getToken() {
        return token;
    }

}