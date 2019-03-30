package com.wallpapers.cartoons.Models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.wallpapers.cartoons.BuildConfig;
import com.wallpapers.cartoons.R;

public class FirebaseRemoteConfigApp extends AppCompatActivity {
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private Context context;

    public FirebaseRemoteConfigApp(Context context) {
        this.context = context;

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        mFirebaseRemoteConfig.setDefaults(R.xml.default_parameters);
        fetchPeriod();
    }

    public int getNumberOfNewPics() {
        return Integer.valueOf(mFirebaseRemoteConfig.getString(ConstantsKt.NUMBER_OF_NEW_PICS));
    }

    public int getNumberOfHotPics() {
        return Integer.valueOf(mFirebaseRemoteConfig.getString(ConstantsKt.NUMBER_OF_HOT_PICS));
    }

    private void fetchPeriod() {


        long cacheExpiration = 3600; // 1 hour in seconds.
        // If your app is using developer mode, cacheExpiration is set to 0, so each fetch will
        // retrieve values from the service.
        if (mFirebaseRemoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()) {
            cacheExpiration = 0;
        }

        // [START fetch_config_with_callback]
        // cacheExpirationSeconds is set to cacheExpiration here, indicating the next fetch request
        // will use fetch data from the Remote Config service, rather than cached parameter values,
        // if cached parameter values are more than cacheExpiration seconds old.
        // See Best Practices in the README for more information.
        mFirebaseRemoteConfig.fetch(cacheExpiration)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {


                            // After config data is successfully fetched, it must be activated before newly fetched
                            // values are returned.
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                        }

                    }
                });
        // [END fetch_config_with_callback]
    }


}
