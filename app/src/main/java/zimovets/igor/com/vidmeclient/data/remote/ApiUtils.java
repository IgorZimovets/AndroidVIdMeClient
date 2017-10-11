package zimovets.igor.com.vidmeclient.data.remote;

/**
 * Created by PC on 08.10.2017.
 */

public class ApiUtils {
    public static final String BASE_URL = "https://api.vid.me/videos/";

    public static FeaturedAPI getFeaturedAPI() {
        return RetrofitClient.getClient(BASE_URL).create(FeaturedAPI.class);
    }
}
