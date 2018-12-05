package com.example.nastya.laba;

import android.app.Application;

import com.example.nastya.laba.http_client.RedditApi;
import com.example.nastya.laba.models.DetailInteractor;
import com.example.nastya.laba.models.DetailInterctorImpl;
import com.example.nastya.laba.models.FavInteractor;
import com.example.nastya.laba.models.FavInteractorImpl;
import com.example.nastya.laba.models.ListInteractor;
import com.example.nastya.laba.models.ListInteractorImpl;
import com.example.nastya.laba.repositories.SharedPreferencesRepository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {

    public static final String DETAILS = "details";
    public static final int HEIGHT = 1150;
    private static final String ROOT_URL = "https://reddit.com/";
    private DetailInteractor detailsInteractor;
    private FavInteractor favInteractor;
    private ListInteractor listInteractor;
    private SharedPreferencesRepository repository;
    private FragmentHandler fragmentHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        setupItems();
    }

    private void setupItems() {
        final RedditApi apiClient = createApiClient();
        listInteractor = new ListInteractorImpl(apiClient);
        repository = new SharedPreferencesRepository(getApplicationContext());
        favInteractor = new FavInteractorImpl(repository);
        detailsInteractor = new DetailInterctorImpl(repository);
    }

    private RedditApi createApiClient() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RedditApi.class);
    }

    public DetailInteractor getDetailsInteractor() {
        return detailsInteractor;
    }

    public FavInteractor getFavInteractor() {
        return favInteractor;
    }

    public ListInteractor getMainInteractor() {
        return listInteractor;
    }

    public SharedPreferencesRepository getPreferences() {
        return repository;
    }

    public FragmentHandler getFragmentHandler() {
        return fragmentHandler;
    }

    public void setFragmentHandler(FragmentHandler fragmentHandler) {
        this.fragmentHandler = fragmentHandler;
    }
}