package zimovets.igor.com.vidmeclient.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zimovets.igor.com.vidmeclient.R;
import zimovets.igor.com.vidmeclient.player.VideoPlayerActivity;
import zimovets.igor.com.vidmeclient.adapters.VideoRecyclerViewAdapter;
import zimovets.igor.com.vidmeclient.data.model.user.OAuthTokenBasicAuth;
import zimovets.igor.com.vidmeclient.data.model.video.AnswersResponse;
import zimovets.igor.com.vidmeclient.data.model.video.Video;
import zimovets.igor.com.vidmeclient.data.remote.ApiUtils;
import zimovets.igor.com.vidmeclient.data.remote.WidMeRetrofitApi;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private String credentials = Credentials.basic("igorzimovets", "passZimov30");

    private WidMeRetrofitApi mWidMeRetrofitApi;
    private RecyclerView mRecyclerView;
    private VideoRecyclerViewAdapter mAdapter;
    private  RecyclerView.LayoutManager layoutManager;
    private Context mContext;
    private LinearLayout mLinearLayout;

    private SwipeRefreshLayout swipeRefreshLayout;

    SharedPreferences prefs;

    @BindView(R.id.name)
    EditText mEditTextName;
    @BindView(R.id.password)
    EditText mEditTextPass;




    public LoginFragment() {
        // Required empty public constructor
    }

    private String[] getNameAndPass(){
        String name = mEditTextName.getText().toString().trim();
        String pass = mEditTextPass.getText().toString().trim();

        String[] data =  {name, pass};

        return data;
    }

    @OnClick(R.id.email_sign_in_button)
    public void click(){

        String[] passAndName = getNameAndPass(); // 0- name , 1- pass


        if (passAndName[0].length() == 0 || passAndName[1].length() == 0){
            Toast.makeText(mContext, "The password or the name is too short.", Toast.LENGTH_LONG).show();
            return;
        }

        createWidMeApi();

        mWidMeRetrofitApi.postCredentials(getNameAndPass()[0], //ts
                getNameAndPass()[1]).enqueue(tokenCallback);


    }

    public static LoginFragment newInstance(/*MainActivity.FirstPageFragmentListener listener*/) {


        LoginFragment fragment = new LoginFragment();
        /*Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View rootView = inflater.inflate(R.layout.fragment_login, container, false);
        //viewPager = (ViewPager) container;

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        ButterKnife.bind(this, rootView);


        mLinearLayout = (LinearLayout) rootView.findViewById(R.id.linear_layout);

        mContext = container.getContext();

        layoutManager = new LinearLayoutManager(mContext);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_login);

        if (mAdapter != null){
            Log.d("Answers"," " + mAdapter.getItemCount());
        }


        SharedPreferences sharedPreferences = mContext.getSharedPreferences("My_Pref", MODE_PRIVATE);
        boolean value = sharedPreferences.contains("key");
        if (value){
            mLinearLayout.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

            /*createWidMeApi();

            initAdapter();*/
            createWidMeApi();
            loadNewVideos();

        }

        prefs = getActivity().getSharedPreferences("My_Pref", MODE_PRIVATE) ;
        prefs.registerOnSharedPreferenceChangeListener(this);


        return rootView;
    }

    private void createWidMeApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                boolean has = prefs.contains("key");

                Request.Builder builder = originalRequest.newBuilder()
                        .header(has ? "AccessToken" : "Authorization",
                                has ? prefs.getString("key", "") : credentials);

                //Log.d("what", has ? prefs.getString("key", "") : credentials );


                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUtils.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mWidMeRetrofitApi = null;
        mWidMeRetrofitApi = retrofit.create(WidMeRetrofitApi.class);
        Log.d("testk", "End create vid me api");
    }

    Callback<OAuthTokenBasicAuth> tokenCallback = new Callback<OAuthTokenBasicAuth>() {
        @Override
        public void onResponse(Call<OAuthTokenBasicAuth> call, Response<OAuthTokenBasicAuth> response) {

            if (response.isSuccessful()) {

                   // response.errorBody().contentLength(); // pass 92
                                                            // name 85

                    SharedPreferences sharedPreferences = mContext.getSharedPreferences("My_Pref", Context.MODE_PRIVATE);
                    String temp = response.body().getAuth().getToken();
                    sharedPreferences.edit().putString("key", temp).apply();

                    Log.d("testk", temp);

                    Log.d("testk", "Before feedVideo");
                    //mWidMeRetrofitApi.getFeedVideo(10,0).enqueue(userDetailsCallback);
                    loadNewVideos();
                    Log.d("testk", "Before afterVideo");

            } else {

                if (response.errorBody().contentLength() == 85){

                    Toast.makeText(mContext, "Please enter a valid name.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, "The password you entered was not valid.", Toast.LENGTH_LONG).show();
                }

                Log.d("RequestTokenCallback", "Code: " + response.code() + "Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<OAuthTokenBasicAuth> call, Throwable t) {
            t.printStackTrace();
        }
    };

    Callback<AnswersResponse> userDetailsCallback = new Callback<AnswersResponse>() {
        @Override
        public void onResponse(Call<AnswersResponse> call, Response<AnswersResponse> response) {
            Log.d("testk", "1");
            if (response.isSuccessful()) {
                Log.d("testk", "TOP");
                mAdapter.updateAnswers(response.body().getVideos());
                /*AnswersResponse userDetails = response.body();
                List<Video> list = userDetails.getVideos();
                Log.d("Test 1 video", list.get(0).getFullUrl() + "\n" +
                        list.get(0).getTitle());
                Log.d("Test", token.getAuth().getToken());*/

            } else {

                Toast.makeText(mContext, "Failure while requesting user details", Toast.LENGTH_LONG).show();
                Log.d("UserDetailsCallback", "Code: " + response.code() + "Message: " + response.message());
            }
        }

        @Override
        public void onFailure(Call<AnswersResponse> call, Throwable t) {
            t.printStackTrace();
        }
    };

    private void initAdapter(){
        Log.d("testk", "Insiderep");
        mAdapter = new VideoRecyclerViewAdapter(mContext, new ArrayList<Video>(0),
                new VideoRecyclerViewAdapter.PostItemListener() {

                    @Override
                    public void onPostClick(String url) {
                        Log.d("AnswersPresenter", url);
                        Intent intent = new Intent(mContext, VideoPlayerActivity.class); // VideoPlayerActivity.class
                        intent.putExtra("KEY", url);
                        startActivity(intent);

                        /*Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(browserIntent);*/

                    }
                });

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void loadNewVideos(){

        /*mWidMeRetrofitApi = null;
        createWidMeApi();*/

        initAdapter();

        mWidMeRetrofitApi.getFeedVideo(10,0).enqueue(userDetailsCallback);
    }


    public void showErrorMessage() {
        Toast.makeText(mContext, "Error loading posts", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        refreshList();
    }
    void refreshList(){
        mAdapter.updateAnswers(new ArrayList<Video>(0));
        loadNewVideos();
        //do processing to get new data and set your listview's adapter, maybe  reinitialise the loaders you may be using or so
        //when your data has finished loading, cset the refresh state of the view to false
        swipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Log.d("testk", "keyOnChange ");
        Log.d("testk", String.valueOf(prefs.contains("key")));
        if (prefs.contains("key")){
            mLinearLayout.setVisibility(View.GONE);
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            mEditTextPass.setText(""); //clear pass field

            createWidMeApi();
            //initAdapter();


        }else {
            mAdapter = null;
            mLinearLayout.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setVisibility(View.GONE);

        }
    }
}
