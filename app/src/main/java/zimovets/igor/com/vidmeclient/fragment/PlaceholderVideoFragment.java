package zimovets.igor.com.vidmeclient.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zimovets.igor.com.vidmeclient.R;
import zimovets.igor.com.vidmeclient.VideoPlayerActivity;
import zimovets.igor.com.vidmeclient.VideoRecyclerViewAdapter;
import zimovets.igor.com.vidmeclient.data.model.video.AnswersResponse;
import zimovets.igor.com.vidmeclient.data.model.video.Video;
import zimovets.igor.com.vidmeclient.data.remote.ApiUtils;
import zimovets.igor.com.vidmeclient.data.remote.WidMeRetrofitApi;



/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderVideoFragment  extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */

    private WidMeRetrofitApi mWidMeRetrofitApi;
    private RecyclerView mRecyclerView;
    private VideoRecyclerViewAdapter mAdapter;


    private Context mContext;// = getActivity().getApplicationContext();

    private SwipeRefreshLayout swipeRefreshLayout;


    public static PlaceholderVideoFragment newInstance(int sectionNumber) {

        //flag = sectionNumber;
        //this flag start

        PlaceholderVideoFragment fragment = new PlaceholderVideoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaceholderVideoFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Answers"," onCreate" );
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main_refresh, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        mContext = container.getContext();
       // TextView textView = (TextView) rootView.findViewById(R.id.section_label);
       // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        mWidMeRetrofitApi = ApiUtils.getFeaturedAPI();

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_video_view);

        mRecyclerView.setItemAnimator(new SlideInUpAnimator());

        if (mAdapter != null){
            Log.d("Answers"," " + mAdapter.getItemCount());
        }
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

        /*RecyclerView.ItemAnimator animator = mRecyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }*/

        //mAdapter.setHasStableIds(true); dont update image, but dont flashing

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.setHasFixedSize(true);

        //boolean a = savedInstanceState.isEmpty();
        //Log.d("AnswersPresenter", String.valueOf(a));

       // Log.d("Answers"," " + mAdapter.getItemCount());

        loadDate();


        return rootView;
    }

    private void loadDate(){
        if (!getArguments().isEmpty()) {
            int section = getArguments().getInt(ARG_SECTION_NUMBER);

            if (section == 0){
                Log.d("AnswersPresenter", "loadFeatured");
                loadFeaturedVideos();
            }else if (section == 1){
                Log.d("AnswersPresenter", "loadNew");
                loadNewVideos();
            }
        }
    }

    private void loadFeaturedVideos() {


        // RxJava Implementation

        /*mService.getAnswers().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SOAnswersResponse>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                    }
                    @Override
                    public void onNext(SOAnswersResponse soAnswersResponse) {
                        mAnswersView.showAnswers(soAnswersResponse.getItems());
                    }
                });*/

        mWidMeRetrofitApi.loadFeaturedVideo(10, 0).enqueue(new Callback<AnswersResponse>() {
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

    private void loadNewVideos(){

        mWidMeRetrofitApi.loadNewVideo(10, 0).enqueue(new Callback<AnswersResponse>() {
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
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        refreshList();
    }
    void refreshList(){
        mAdapter.updateAnswers(new ArrayList<Video>(0));
        loadDate();
        //do processing to get new data and set your listview's adapter, maybe  reinitialise the loaders you may be using or so
        //when your data has finished loading, cset the refresh state of the view to false
        swipeRefreshLayout.setRefreshing(false);

    }
}