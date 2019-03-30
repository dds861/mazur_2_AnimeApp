package com.wallpapers.cartoons.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wallpapers.cartoons.Activities.SecondActivity;
import com.wallpapers.cartoons.Models.ConstantsKt;
import com.wallpapers.cartoons.Models.PreferencesApp;
import com.wallpapers.cartoons.Presenter.ImageUrlPresenter;
import com.wallpapers.cartoons.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FavoriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoriteFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final Object FavoriteFragment = "favorite_fragment";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ImageView mOneIv;
    private ImageView mTwoIv;
    private ImageView mThreeIv;
    private ImageView mFourIv;
    private ImageView mFiveIv;
    private ImageView mSixIv;
    private ImageView mSevenIv;
    private ImageView mEightIv;
    private ImageUrlPresenter imageUrlPresenter;
    private ArrayList<String> imageUrlsArrayList;
    private int iterator;
    private ImageView mBtnGoLeftIv;
    private ImageView mBtnGoRightIv;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        mOneIv.setImageResource(0);
        mTwoIv.setImageResource(0);
        mThreeIv.setImageResource(0);
        mFourIv.setImageResource(0);
        mFiveIv.setImageResource(0);
        mSixIv.setImageResource(0);
        mSevenIv.setImageResource(0);
        mEightIv.setImageResource(0);


        getImageUrls();
        loadImages(iterator);


    }

    private void getImageUrls() {
        PreferencesApp preferencesApp = new PreferencesApp(getContext());
        if (preferencesApp.onGetList(ConstantsKt.FAVORITE_IMAGE) != null) {
            imageUrlsArrayList = preferencesApp.onGetList(ConstantsKt.FAVORITE_IMAGE);
        }
    }

    private void loadImages(int iterator) {
        try {

            if (imageUrlsArrayList != null) {
                Glide.with(this).load(imageUrlsArrayList.get(iterator)).into(mOneIv);
                Glide.with(this).load(imageUrlsArrayList.get(iterator + 1)).into(mTwoIv);
                Glide.with(this).load(imageUrlsArrayList.get(iterator + 2)).into(mThreeIv);
                Glide.with(this).load(imageUrlsArrayList.get(iterator + 3)).into(mFourIv);
                Glide.with(this).load(imageUrlsArrayList.get(iterator + 4)).into(mFiveIv);
                Glide.with(this).load(imageUrlsArrayList.get(iterator + 5)).into(mSixIv);
                Glide.with(this).load(imageUrlsArrayList.get(iterator + 6)).into(mSevenIv);
                Glide.with(this).load(imageUrlsArrayList.get(iterator + 7)).into(mEightIv);
            }
        } catch (IndexOutOfBoundsException e) {
            Log.i("autolog", "IndexOutOfBoundsException: " + e);

        }
    }

    private void initView(@NonNull final View itemView) {
        mOneIv = (ImageView) itemView.findViewById(R.id.iv_one);
        mOneIv.setOnClickListener(this);
        mTwoIv = (ImageView) itemView.findViewById(R.id.iv_two);
        mTwoIv.setOnClickListener(this);
        mThreeIv = (ImageView) itemView.findViewById(R.id.iv_three);
        mThreeIv.setOnClickListener(this);
        mFourIv = (ImageView) itemView.findViewById(R.id.iv_four);
        mFourIv.setOnClickListener(this);
        mFiveIv = (ImageView) itemView.findViewById(R.id.iv_five);
        mFiveIv.setOnClickListener(this);
        mSixIv = (ImageView) itemView.findViewById(R.id.iv_six);
        mSixIv.setOnClickListener(this);
        mSevenIv = (ImageView) itemView.findViewById(R.id.iv_seven);
        mSevenIv.setOnClickListener(this);
        mEightIv = (ImageView) itemView.findViewById(R.id.iv_eight);
        mEightIv.setOnClickListener(this);
        mBtnGoLeftIv = (ImageView) itemView.findViewById(R.id.iv_btn_go_left);
        mBtnGoLeftIv.setOnClickListener(this);
        mBtnGoRightIv = (ImageView) itemView.findViewById(R.id.iv_btn_go_right);
        mBtnGoRightIv.setOnClickListener(this);
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), SecondActivity.class);
        switch (v.getId()) {
            case R.id.iv_one:
                intent.putExtra(ConstantsKt.IMAGE_URL, imageUrlsArrayList.get(iterator));
                intent.putExtra(ConstantsKt.FAVORITE_FRAGMENT,true);
                startActivity(intent);

                break;
            case R.id.iv_two:

                intent.putExtra(ConstantsKt.IMAGE_URL, imageUrlsArrayList.get(iterator + 1));
                intent.putExtra(ConstantsKt.FAVORITE_FRAGMENT,true);
                startActivity(intent);
                break;
            case R.id.iv_three:

                intent.putExtra(ConstantsKt.IMAGE_URL, imageUrlsArrayList.get(iterator + 2));
                intent.putExtra(ConstantsKt.FAVORITE_FRAGMENT,true);
                startActivity(intent);
                break;
            case R.id.iv_four:

                intent.putExtra(ConstantsKt.IMAGE_URL, imageUrlsArrayList.get(iterator + 3));
                intent.putExtra(ConstantsKt.FAVORITE_FRAGMENT,true);
                startActivity(intent);
                break;
            case R.id.iv_five:

                intent.putExtra(ConstantsKt.IMAGE_URL, imageUrlsArrayList.get(iterator + 4));
                intent.putExtra(ConstantsKt.FAVORITE_FRAGMENT,true);
                startActivity(intent);
                break;
            case R.id.iv_six:

                intent.putExtra(ConstantsKt.IMAGE_URL, imageUrlsArrayList.get(iterator + 5));
                intent.putExtra(ConstantsKt.FAVORITE_FRAGMENT,true);
                startActivity(intent);
                break;
            case R.id.iv_seven:

                intent.putExtra(ConstantsKt.IMAGE_URL, imageUrlsArrayList.get(iterator + 6));
                intent.putExtra(ConstantsKt.FAVORITE_FRAGMENT,true);
                startActivity(intent);
                break;
            case R.id.iv_eight:

                intent.putExtra(ConstantsKt.IMAGE_URL, imageUrlsArrayList.get(iterator + 7));
                intent.putExtra(ConstantsKt.FAVORITE_FRAGMENT,true);
                startActivity(intent);

                break;

            case R.id.iv_btn_go_left:
                if ((iterator -= 8) >= 0) {
                    loadImages(iterator);
                } else iterator += 8;
                break;
            case R.id.iv_btn_go_right:// TODO 19/03/28
                iterator += 8;
                loadImages(iterator);
                break;
            default:
                break;
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
