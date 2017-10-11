package zimovets.igor.com.vidmeclient.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import zimovets.igor.com.vidmeclient.data.model.AnswersResponse;

/**
 * Created by PC on 08.10.2017.
 */

public interface FeaturedAPI {
    @GET("featured/")
    Call<AnswersResponse> loadFeaturedVideo(@Query("limit") Integer limit, @Query("offset") Integer offset);

    @GET("new/")
    Call<AnswersResponse> loadNewVideo(@Query("limit") Integer limit, @Query("offset") Integer offset);

}
