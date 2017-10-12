package zimovets.igor.com.vidmeclient;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zimovets.igor.com.vidmeclient.adapters.SectionsPagerAdapter;


public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    private SectionsPagerAdapter mSectionsPagerAdapter;
    private Context mContext = this;

    private ImageView mButton;

    private SharedPreferences mSharedPreferences;

    @BindView(R.id.progressBarStartLoad) ProgressBar mProgressBar;
    @BindView(R.id.textViewNoInternet) TextView mTextViewNoInternet;


    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //change

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mProgressBar.setVisibility(View.VISIBLE);
        mTextViewNoInternet.setVisibility(View.GONE);

        mButton = (ImageView) findViewById(R.id.button);
        registerForContextMenu(mButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mButton.showContextMenu();
            }
        });

        mSharedPreferences = getSharedPreferences("My_Pref", MODE_PRIVATE);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
        if (mSharedPreferences.contains("key")){
            mButton.setVisibility(View.VISIBLE);
        } else {
            mButton.setVisibility(View.GONE);
        }

        mViewPager = (ViewPager) findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (activeNetwork != null && activeNetwork.isConnected()){

            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), mContext);
            // Set up the ViewPager with the sections adapter.
            mViewPager.setAdapter(mSectionsPagerAdapter);

            mProgressBar.setVisibility(View.GONE);

            tabLayout.setupWithViewPager(mViewPager);
        }else {


            mProgressBar.setVisibility(View.GONE);
            mTextViewNoInternet.setVisibility(View.VISIBLE);

        }

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
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {


        if (sharedPreferences.contains("key")){
            mButton.setVisibility(View.VISIBLE);

        }else {
            mButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mSharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_main, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.log_out:
                Log.d("Test" , "Work");
                SharedPreferences sharedPreferences = getSharedPreferences("My_Pref", MODE_PRIVATE);
                sharedPreferences.edit().remove("key").apply();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

}
