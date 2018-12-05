package com.example.nastya.laba.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nastya.laba.R;
import com.example.nastya.laba.entity.children.Children;
import com.example.nastya.laba.presenter.DetailPresenter;
import com.example.nastya.laba.presenter.DetailPresenterImpl;
import com.example.nastya.laba.views.DetailsView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.nastya.laba.ApplicationEx.HEIGHT;

public class ListDetailsFragment extends Fragment implements DetailsView {

    private static final String DETAILS = "details";
    private DetailPresenter presenter;
    private boolean isImageFitToScreen;
    private Children children;

    @BindView(R.id.image_details)
    protected ImageView imageDetails;
    @BindView(R.id.fav)
    protected ImageView favourite;
    @BindView(R.id.title)
    protected TextView title;
    @BindView(R.id.subreddit)
    protected TextView subreddit;
    private Bundle bundle;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details, container, false);
        ButterKnife.bind(this, view);
        presenter = new DetailPresenterImpl(this);
        imageDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlImage();
            }
        });
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkFavourite(getActivity());
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getData(getActivity());
    }

    @Override
    public void setItems(Children children) {
        Picasso.get().load(children.getData().getThumbnail()).into(imageDetails);
        title.setText(children.getData().getTitle());
        subreddit.setText(children.getData().getSubreddit());
    }

    public Children getChildren() {
        return bundle == null ? null : new Gson()
                .fromJson(bundle.getString(DETAILS), Children.class);
    }

    @OnClick(R.id.image_details)
    void fullScreenImage() {
        if (isImageFitToScreen) {
            isImageFitToScreen = false;
            imageDetails.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT));
            imageDetails.setAdjustViewBounds(true);
        } else {
            isImageFitToScreen = true;
            imageDetails.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT));
            imageDetails.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

    private void controlImage() {
        if (isImageFitToScreen) {
            isImageFitToScreen = false;
            imageDetails.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, HEIGHT));
        } else {
            isImageFitToScreen = true;
            imageDetails.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams
                    .MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
            imageDetails.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    @Override
    public void addToFavourite() {
        favourite.setImageResource(R.drawable.ic_fav_black);
    }

    @Override
    public void removeFromFavourite() {
        favourite.setImageResource(R.drawable.ic_favorite);
    }

    @Override
    public void markFavourite(boolean fav) {
        if (fav) {
            favourite.setImageResource(R.drawable.ic_fav_black);
        }
    }

}