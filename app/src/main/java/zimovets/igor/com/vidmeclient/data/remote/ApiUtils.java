package zimovets.igor.com.vidmeclient.data.remote;

/**
 * Created by PC on 08.10.2017.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://api.vid.me/";

    public static WidMeRetrofitApi getFeaturedAPI() {
        return RetrofitClient.getClient(BASE_URL).create(WidMeRetrofitApi.class);
    }
}
