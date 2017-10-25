package zimovets.igor.com.vidmeclient.data.remote;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import zimovets.igor.com.vidmeclient.data.model.user.OAuthTokenBasicAuth;
import zimovets.igor.com.vidmeclient.data.model.video.AnswersResponse;


public interface WidMeRetrofitApi {

    @GET("videos/featured/")
    Call<AnswersResponse> loadFeaturedVideo(@Query("limit") Integer limit,
                                            @Query("offset") Integer offset);

    @GET("videos/new/")
    Call<AnswersResponse> loadNewVideo(@Query("limit") Integer limit,
                                       @Query("offset") Integer offset);

    @FormUrlEncoded
    @POST("auth/create")
    Call<OAuthTokenBasicAuth> postCredentials(@Field("username") String name,
                                              @Field("password") String pass);
    @GET("videos/feed/")
    Call<AnswersResponse> getFeedVideo(@Header("AccessToken") String token,
                                       @Query("limit") Integer limit,
                                       @Query("offset") Integer offset);

}
