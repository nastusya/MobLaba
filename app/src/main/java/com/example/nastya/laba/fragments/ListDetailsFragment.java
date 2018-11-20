package com.example.nastya.laba.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nastya.laba.R;
import com.example.nastya.laba.adapters.DetailsAdapter;
import com.example.nastya.laba.model.children.Children;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListDetailsFragment extends Fragment {
    private DetailsAdapter detailsAdapter;
    private boolean isImageFitToScreen;
    private SharedPreferences sharedPreferences;
    public final static String ARG_TITLE = "Children";
    public final static String FAVOURITE = "Favourite";
    private Children children;

    @BindView(R.id.detail_char_name)
    protected TextView characterName;
    @BindView(R.id.detail_char_image)
    protected ImageView characterImage;
    @BindView(R.id.detail_char_role)
    protected TextView characterRole;
    @BindView(R.id.detail_char_id)
    protected TextView characterID;
    @BindView(R.id.detail_recycler_view)
    protected RecyclerView recyclerView;
    @BindView(R.id.favorite)
    protected ImageView favorite;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_details, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            children = (Children) getArguments().getSerializable(ARG_TITLE);
            displayCharacter();
        }

        if (getActivity() != null) {
            ButterKnife.bind(this, view);
            sharedPreferences = getActivity().getSharedPreferences(FAVOURITE,
                    Context.MODE_PRIVATE);
            initRecyclerView();
        }
        checkFavorite();
        return view;
    }

    @OnClick(R.id.detail_char_image)
    void fullScreenImage() {
        if (isImageFitToScreen) {
            isImageFitToScreen = false;
            characterImage.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.WRAP_CONTENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT));
            characterImage.setAdjustViewBounds(true);
        } else {
            isImageFitToScreen = true;
            characterImage.setLayoutParams(new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.MATCH_PARENT));
            characterImage.setScaleType(ImageView.ScaleType.FIT_XY);
        }
    }

    @OnClick(R.id.favorite)
    void setFavorite() {
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        if (checkFavorite()) {
            prefEditor.remove(children.getData().getAuthorFullname());
            prefEditor.apply();
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(children);
            prefEditor.putString(children.getData().getAuthorFullname(), json);
            prefEditor.apply();
        }
        checkFavorite();
    }

    boolean checkFavorite() {
        if (!sharedPreferences.contains(children.getData().getAuthorFullname())) {
            favorite.setImageResource(R.drawable.ic_favorite);
            return false;
        } else {
            favorite.setImageResource(R.drawable.ic_fav_black);
            return true;
        }
    }

    void displayCharacter() {
        characterName.setText(children.getData().getAuthorFullname());
        Picasso.get().load(children.getData().getThumbnail()).into(characterImage);
        characterRole.setText(String.format("%s: %s", getString(R.string.char_role),
                children.getData().getSubreddit()));
        characterID.setText(String.format("%s: %s", getString(R.string.mal_id),
                children.getData().getTitle()));
    }


    private void initRecyclerView() {
        detailsAdapter = new DetailsAdapter();
        recyclerView.setAdapter(detailsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}