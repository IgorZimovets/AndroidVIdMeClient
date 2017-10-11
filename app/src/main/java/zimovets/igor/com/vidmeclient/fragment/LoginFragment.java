package zimovets.igor.com.vidmeclient.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zimovets.igor.com.vidmeclient.MainActivity;
import zimovets.igor.com.vidmeclient.R;
import zimovets.igor.com.vidmeclient.VideoPlayerActivity;
import zimovets.igor.com.vidmeclient.VideoRecyclerViewAdapter;
import zimovets.igor.com.vidmeclient.data.model.AnswersResponse;
import zimovets.igor.com.vidmeclient.data.model.Video;
import zimovets.igor.com.vidmeclient.data.remote.ApiUtils;
import zimovets.igor.com.vidmeclient.data.remote.FeaturedAPI;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

   // private static final String ARG_SECTION_NUMBER = "section_number";

    //MainActivity.FirstPageFragmentListener mListener;

    private FeaturedAPI mFeaturedAPI;
    private RecyclerView mRecyclerView;
    private VideoRecyclerViewAdapter mAdapter;
    private  RecyclerView.LayoutManager layoutManager;
    private Context mContext;
    ViewPager viewPager;
    private LinearLayout mLinearLayout;

    SharedPreferences prefs;

    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
            // listener implementation

            Log.d("Sher", "Start");
            Log.d("Sher", prefs.toString() +" " + key);
            boolean string = prefs.contains("key");
            boolean string2 = prefs.contains(key);
            Log.d("Sher1", String.valueOf(string));
            Log.d("Sher2", String.valueOf(string2));
            Log.d("Sher", "end");

            if (string){
                mLinearLayout.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

                rep();
            }else {
                mAdapter = null;
                mLinearLayout.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);

            }

        }
    };


    public LoginFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.email_sign_in_button)
    public void clik(){

        SharedPreferences sharedPreferences = mContext.getSharedPreferences("My_Pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key", "key");
        editor.apply();

        //viewPager.getAdapter().notifyDataSetChanged();


       /* mLinearLayout.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);

        rep();*/
    }

    public static LoginFragment newInstance(/*MainActivity.FirstPageFragmentListener listener*/) {


        //flag = sectionNumber;
        //this flag start

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

        ButterKnife.bind(this, rootView);


        mLinearLayout = (LinearLayout) rootView.findViewById(R.id.linear_layout);

        mContext = container.getContext();
        // TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        mFeaturedAPI = ApiUtils.getFeaturedAPI();

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

            rep();
        }

        prefs = getActivity().getSharedPreferences("My_Pref", MODE_PRIVATE) ;
        prefs.registerOnSharedPreferenceChangeListener(listener);


        return rootView;
    }

    private void rep(){
        mAdapter = new VideoRecyclerViewAdapter(mContext, new ArrayList<Video>(0),
                new VideoRecyclerViewAdapter.PostItemListener() {

                    @Override
                    public void onPostClick(String url) {
                        Log.d("AnswersPresenter", url);
                        /*Intent intent = new Intent(mContext, FullscreenActivity.class);
                        intent.putExtra("KEY", url);
                        startActivity(intent);*/

                        /*Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(browserIntent);*/

                        Intent intent = new Intent(mContext, VideoPlayerActivity.class);
                        startActivity(intent);

                    }
                });

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        loadNewVideos();
    }

    private void loadNewVideos(){

        mFeaturedAPI.loadNewVideo(10, 0).enqueue(new Callback<AnswersResponse>() {
            @Override
            public void onResponse(Call<AnswersResponse> call, Response<AnswersResponse> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getVideos());
                    String responseUrl = response.body().getVideos().get(0).getThumbnailUrl();
                    Log.d("AnswersPresenter", response.raw().request().url().toString());

                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<AnswersResponse> call, Throwable t) {
                showErrorMessage();
                Log.d("AnswersPresenter", "error loading from API");

            }
        });
    }

    public void showErrorMessage() {
        Toast.makeText(mContext, "Error loading posts", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        prefs.unregisterOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void onResume() {
        super.onResume();
        prefs.registerOnSharedPreferenceChangeListener(listener);
    }
}
