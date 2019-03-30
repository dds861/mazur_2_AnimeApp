package com.wallpapers.cartoons.Presenter;

import android.content.Context;

import com.wallpapers.cartoons.Models.FirebaseRemoteConfigApp;

import java.util.ArrayList;
import java.util.Collections;

public class ImageUrlPresenter {

    private Context context;

    public ImageUrlPresenter(Context context) {
        this.context = context;
    }

    public ArrayList<String> getHotUrls() {

//        strings.add("https://firebasestorage.googleapis.com/v0/b/picsrandomproject2.appspot.com/o/images%2Fhot%2Fpic_001.jpg?alt=media&token=1e093bbf-a7bc-4eb3-8962-6fc55d4b3044")
        FirebaseRemoteConfigApp firebaseRemoteConfigApp = new FirebaseRemoteConfigApp(context);
        int numberOfPics = firebaseRemoteConfigApp.getNumberOfHotPics();

        String consctructedUrl[] = getConstructedUrl("hot");

        ArrayList<String> imageUrls = new ArrayList<>();
        String url = "";
        for (int j = 1; j <= numberOfPics; j++) {
            if (j < 10) {
                url = consctructedUrl[0] + "pic_00" + j + consctructedUrl[1];
            } else if (j > 9 && j < 100) {
                url = consctructedUrl[0] + "pic_0" + j + consctructedUrl[1];
            } else if (j > 99) {
                url = consctructedUrl[0] + "pic_" + j + consctructedUrl[1];
            }
            imageUrls.add(url);
        }

        return imageUrls;
    }

    public ArrayList<String> getNewUrls() {

//        strings.add("https://firebasestorage.googleapis.com/v0/b/picsrandomproject2.appspot.com/o/images%2Fhot%2Fpic_001.jpg?alt=media&token=1e093bbf-a7bc-4eb3-8962-6fc55d4b3044")
        FirebaseRemoteConfigApp firebaseRemoteConfigApp = new FirebaseRemoteConfigApp(context);
        int numberOfPics = firebaseRemoteConfigApp.getNumberOfHotPics();

        String consctructedUrl[] = getConstructedUrl("new");

        ArrayList<String> imageUrls = new ArrayList<>();
        String url = "";
        for (int j = 1; j <= numberOfPics; j++) {
            if (j < 10) {
                url = consctructedUrl[0] + "pic_00" + j + consctructedUrl[1];
            } else if (j > 9 && j < 100) {
                url = consctructedUrl[0] + "pic_0" + j + consctructedUrl[1];
            } else if (j > 99) {
                url = consctructedUrl[0] + "pic_" + j + consctructedUrl[1];
            }
            imageUrls.add(url);
        }

        Collections.shuffle(imageUrls);

        return imageUrls;
    }


    public ArrayList<String> getRandomUrls() {

        ArrayList<String> imageUrls = new ArrayList<>();

        imageUrls.addAll(getHotUrls());
        imageUrls.addAll(getNewUrls());

        Collections.shuffle(imageUrls);

        return imageUrls;
    }

    private String[] getConstructedUrl(String subFolderName) {
        String url[] = new String[2];

        String folderName = "images";
        String token = "1e093bbf-a7bc-4eb3-8962-6fc55d4b3044";

        String urlBefore = "https://firebasestorage.googleapis.com/v0/b/picsrandomproject2.appspot.com/o/" + folderName + "%2F" + subFolderName + "%2F";
        String urlAfter = ".jpg?alt=media&token=" + token;

        url[0] = urlBefore;
        url[1] = urlAfter;

        return url;
    }

}
