package zimovets.igor.com.vidmeclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import zimovets.igor.com.vidmeclient.data.model.AnswersResponse;
import zimovets.igor.com.vidmeclient.data.remote.ApiUtils;
import zimovets.igor.com.vidmeclient.data.remote.FeaturedAPI;
import zimovets.igor.com.vidmeclient.fragment.LoginFragment;
import zimovets.igor.com.vidmeclient.fragment.PlaceholderVideoFragment;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Context mContext = this;
    private int currentApiVersion = android.os.Build.VERSION.SDK_INT;

    private FeaturedAPI mFeaturedAPI;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
// Remember that you should never show the action bar if the
// status bar is hidden, so hide that too if necessary.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }


        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //mViewPager.setOffscreenPageLimit(2);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //mViewPager.getAdapter().notifyDataSetChanged();

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    if (mViewPager.getCurrentItem() != 2) {
                        // Hide the keyboard.
                        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                                .hideSoftInputFromWindow(mViewPager.getWindowToken(), 0);
                    }
                }
            }
        });

        mFeaturedAPI = ApiUtils.getFeaturedAPI();

       // loadAnswers();
    }

    @Override
    protected void onResume() {
        super.onResume();

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        View decorView = getWindow().getDecorView();
// Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.log_out) {
            SharedPreferences sharedPreferences = getSharedPreferences("My_Pref", MODE_PRIVATE);
            sharedPreferences.edit().remove("key").apply();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    
  

    /**
     * A {@link FragmentStatePagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        static final int NUM_ITEMS = 3;
        private final FragmentManager mFragmentManager;
        private Fragment mFragmentAtPos3;


        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragmentManager = fm;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if (position == 0){
                //return PlaceholderVideoFragment.newInstance(position + 1);
                Log.d("AnswersPresenter", "fragment 1");
                return PlaceholderVideoFragment.newInstance(position);

            }else if (position == 1){
                Log.d("AnswersPresenter", "fragment 2");
               // return new PlaceholderVideoFragment();
                return PlaceholderVideoFragment.newInstance(position);
            }
            else {
                /*if (mFragmentAtPos3 == null){
                    mFragmentAtPos3 = new LoginFragment(new FirstPageFragmentListener() {
                        @Override
                        public void onSwitchToNextFragment() {

                        }
                    });
                }*/
                SharedPreferences sharedPreferences = getSharedPreferences("My_Pref", MODE_PRIVATE);
                String value = sharedPreferences.getString("key", "");
                Log.d("Pref  ", value);

                Log.d("AnswersPresenter", "fragment 3");
                //Fragment fragment = new LoginFragment();
                return LoginFragment.newInstance();


            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "FEATURED";
                case 1:
                    return "NEW";
                case 2:
                    return "FEED";
            }
            return null;
        }

       /* @Override
        public int getItemPosition(Object object) {
// POSITION_NONE makes it possible to reload the PagerAdapter
            return POSITION_NONE;
        }*/
    }




    private void loadAnswers() {

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

        mFeaturedAPI.loadFeaturedVideo(10, 0).enqueue(new Callback<AnswersResponse>() {
            @Override
            public void onResponse(Call<AnswersResponse> call, Response<AnswersResponse> response) {

                if(response.isSuccessful()) {
                    //mAdapter.updateAnswers(response.body().getItems());
                    String responseUrl = response.body().getVideos().get(0).getThumbnailUrl();
                    Log.d("AnswersPresenter", responseUrl);
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
        Toast.makeText(this, "Error loading posts", Toast.LENGTH_SHORT).show();
    }
}
