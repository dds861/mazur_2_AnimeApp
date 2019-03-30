package com.wallpapers.cartoons.Activities;

import android.Manifest;
import android.app.WallpaperManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wallpapers.cartoons.Models.ConstantsKt;
import com.wallpapers.cartoons.Models.OpenGalleryListener;
import com.wallpapers.cartoons.Models.PreferencesApp;
import com.wallpapers.cartoons.R;

import java.io.IOException;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView mImageSecondActivityIv;
    private ImageView mBtnGoBackIv;
    private ImageView mBtnSetWallpaperIv;
    private ImageView mBtnSavePicIv;
    private ImageView mBtnSharePicIv;
    private ImageView mBtnFavoriteIv;
    private int iterator;
    private String imageUrl;
    private PreferencesApp preferencesApp;
    boolean isFromFavorite = false;
    private RelativeLayout mLayoutSecondActivityRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        receiveIntents();


        Glide.with(this).load(imageUrl).into(mImageSecondActivityIv);

        isImageUrlFavorite();

    }

    private void isImageUrlFavorite() {
        if (preferencesApp.onGetList(ConstantsKt.FAVORITE_IMAGE) != null) {
            ArrayList<String> strings = preferencesApp.onGetList(ConstantsKt.FAVORITE_IMAGE);
            if (strings.contains(imageUrl)) {
                mBtnFavoriteIv.setImageResource(R.drawable.ic_star_black_24dp);
            }
        }
    }

    private void receiveIntents() {
        imageUrl = getIntent().getStringExtra(ConstantsKt.IMAGE_URL);
        isFromFavorite = getIntent().getBooleanExtra(ConstantsKt.FAVORITE_FRAGMENT, false);

    }

    private void initView() {

        mImageSecondActivityIv = (ImageView) findViewById(R.id.iv_image_second_activity);
        mBtnGoBackIv = (ImageView) findViewById(R.id.iv_btn_go_back);
        mBtnGoBackIv.setOnClickListener(this);
        mBtnSetWallpaperIv = (ImageView) findViewById(R.id.iv_btn_set_wallpaper);
        mBtnSetWallpaperIv.setOnClickListener(this);
        mBtnSavePicIv = (ImageView) findViewById(R.id.iv_btn_save_pic);
        mBtnSavePicIv.setOnClickListener(this);
        mBtnSharePicIv = (ImageView) findViewById(R.id.iv_btn_share_pic);
        mBtnSharePicIv.setOnClickListener(this);
        mBtnFavoriteIv = (ImageView) findViewById(R.id.iv_btn_favorite);
        mBtnFavoriteIv.setOnClickListener(this);

        preferencesApp = new PreferencesApp(getApplicationContext());
        mLayoutSecondActivityRoot = (RelativeLayout) findViewById(R.id.root_layout_second_activity);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_btn_go_back:
                backPressed();
                break;
            case R.id.iv_btn_set_wallpaper:
                setWallpaper();
                break;
            case R.id.iv_btn_save_pic:
                if (isWriteStoragePermissionGrantedToSave()) {
                    saveImageToGallery();
                }


                break;
            case R.id.iv_btn_share_pic:
                if (isWriteStoragePermissionGrantedToShare()) {
                    sharePic();
                }
                break;
            case R.id.iv_btn_favorite:
                onFavoritePressed();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        backPressed();
    }

    private void backPressed() {
        if (isFromFavorite) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra(ConstantsKt.OPEN_TAB_FROM_FAVORITE, 3);
            startActivity(intent);
        }
        finish();
    }

    private void onFavoritePressed() {

        if (preferencesApp.isListContainsImageUrl(imageUrl)) {
            preferencesApp.deleteFavoriteImage(imageUrl);
            mBtnFavoriteIv.setImageResource(R.drawable.ic_star_border_black_24dp);

        } else {
            preferencesApp.saveFavoriteImage(imageUrl);
            mBtnFavoriteIv.setImageResource(R.drawable.ic_star_black_24dp);
        }
    }

    private void setWallpaper() {
        mImageSecondActivityIv.buildDrawingCache();
        Bitmap bmap = mImageSecondActivityIv.getDrawingCache();
        WallpaperManager m = WallpaperManager.getInstance(this);

        try {
            m.setBitmap(bmap);
            Toast.makeText(this, R.string.wallpaper_was_set, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveImageToGallery() {
        String savedImageURL = MediaStore.Images.Media.insertImage(
                getContentResolver(),
                ((BitmapDrawable) mImageSecondActivityIv.getDrawable()).getBitmap(),
                "Bird",
                "Image of bird");
        Uri savedImageURI = Uri.parse(savedImageURL);

        Snackbar mySnackbar = Snackbar.make(mLayoutSecondActivityRoot,
                "Downloaded", Snackbar.LENGTH_SHORT);
        mySnackbar.setAction("Open", new OpenGalleryListener(getApplicationContext(), savedImageURI));
        mySnackbar.show();

        Toast.makeText(this, R.string.saved_to_gallery, Toast.LENGTH_SHORT).show();
    }


    private void sharePic() {

        String savedImageURL = MediaStore.Images.Media.insertImage(
                getContentResolver(),
                ((BitmapDrawable) mImageSecondActivityIv.getDrawable()).getBitmap(),
                "Bird",
                "Image of bird");
        Uri contentUri = Uri.parse(savedImageURL);

        if (contentUri != null) {

            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // temp permission for receiving app to read this file
            shareIntent.setDataAndType(contentUri, getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            startActivity(Intent.createChooser(shareIntent, "Choose an app"));

        }
    }

    public boolean isWriteStoragePermissionGrantedToSave() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("autolog", "Permission is granted2");
                return true;
            } else {
                Log.v("autolog", "Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        } else {
            //permission is automatically granted on sdk<23 upon installation
            Log.v("autolog", "Permission is granted2");
            return true;
        }
    }

    public boolean isWriteStoragePermissionGrantedToShare() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                Log.v("autolog", "Permission is granted2");
                return true;
            } else {
                Log.v("autolog", "Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
                return false;
            }
        } else {
            //permission is automatically granted on sdk<23 upon installation
            Log.v("autolog", "Permission is granted2");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2:
                Log.d("autolog", "External storage2");
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.v("autolog", "Permission: " + permissions[0] + "was " + grantResults[0]);
                    //resume tasks needing this permission
                    saveImageToGallery();
                } else {
                    Toast.makeText(this, "Permission to save is not granted", Toast.LENGTH_SHORT).show();
                }
                break;

            case 3:
                Log.d("autolog", "External storage1");
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.v("autolog", "Permission: " + permissions[0] + "was " + grantResults[0]);
                    //resume tasks needing this permission
                    sharePic();
                } else {
                    Toast.makeText(this, "Permission to save is not granted", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
