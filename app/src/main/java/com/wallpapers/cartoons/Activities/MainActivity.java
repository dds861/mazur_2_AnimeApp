package com.wallpapers.cartoons.Activities;

import android.app.Application;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.appevents.AppEventsLogger;
import com.wallpapers.cartoons.Adapters.SectionsPagerAdapter;
import com.wallpapers.cartoons.Fragments.FavoriteFragment;
import com.wallpapers.cartoons.Fragments.HotFragment;
import com.wallpapers.cartoons.Fragments.NewFragment;
import com.wallpapers.cartoons.Fragments.RandomFragment;
import com.wallpapers.cartoons.Models.ConstantsKt;
import com.wallpapers.cartoons.R;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        NewFragment.OnFragmentInteractionListener,
        HotFragment.OnFragmentInteractionListener,
        RandomFragment.OnFragmentInteractionListener,
        FavoriteFragment.OnFragmentInteractionListener {

    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout mTabsSliding;
    private ViewPager mPagerView;
    int openPageViewTab = 2;
//    private ImageView mBtnGoLeftIv;
//    private ImageView mBtnGoRightIv;
private AppEventsLogger logger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();



        setAdapterForPageView();

        mTabsSliding.setupWithViewPager(mPagerView);
        mTabsSliding.getTabAt(3).setIcon(R.drawable.ic_star_black_24dp);
        openCurrentTabinPagerView();


        onLoadYandexMetrics();
        onLogFacebookEvent();
    }

    public void onLogFacebookEvent() {
        logger = AppEventsLogger.newLogger(getApplicationContext());
        logger.logEvent(AppEventsConstants.EVENT_NAME_VIEWED_CONTENT);
    }

    private void onLoadYandexMetrics() {

//        String API_key = "71541371-3720-4a88-b4e8-8362f9ec5a4a";
        String API_key = getResources().getString(R.string.yandex_api_key);
        // Создание расширенной конфигурации библиотеки.
        YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder(API_key).build();
        // Инициализация AppMetrica SDK.
        YandexMetrica.activate(getApplicationContext(), config);
        // Отслеживание активности пользователей.
        YandexMetrica.enableActivityAutoTracking((Application) getApplicationContext());
    }

    private void openCurrentTabinPagerView() {
        //check if current activity is from SecondsActivity which is by itself was opened from FavoriteTab
        //we need to return to Favorite Tab to refresh it
        //if not from secondsactivity then normally open first tab
        openPageViewTab = getIntent().getIntExtra(ConstantsKt.OPEN_TAB_FROM_FAVORITE, 0);
        mPagerView.setCurrentItem(openPageViewTab);

    }

    private void setAdapterForPageView() {
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mPagerView.setAdapter(mSectionsPagerAdapter);
    }

    private void initView() {
        mTabsSliding = (TabLayout) findViewById(R.id.sliding_tabs);
        mPagerView = (ViewPager) findViewById(R.id.view_pager);
//        mBtnGoLeftIv = (ImageView) findViewById(R.id.iv_btn_go_left);
//        mBtnGoLeftIv.setOnClickListener(this);
//        mBtnGoRightIv = (ImageView) findViewById(R.id.iv_btn_go_right);
//        mBtnGoRightIv.setOnClickListener(this);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.iv_btn_go_left:
//                break;
//            case R.id.iv_btn_go_right:
//                setAdapterForPageView();
//                Toast.makeText(this, "right clicked", Toast.LENGTH_SHORT).show();
//                break;
            default:
                break;
        }
    }


    //Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show();

}
