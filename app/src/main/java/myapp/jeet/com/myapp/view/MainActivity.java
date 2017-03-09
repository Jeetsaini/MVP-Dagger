package myapp.jeet.com.myapp.view;

import android.app.SearchManager;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import javax.inject.Inject;

import myapp.jeet.com.myapp.R;
import myapp.jeet.com.myapp.api.RetrofitAPICallBack;
import myapp.jeet.com.myapp.api.RetrofitNetworkClient;
import myapp.jeet.com.myapp.api.model.model.ArtistsSearch;
import myapp.jeet.com.myapp.model.CityListResponse;
import myapp.jeet.com.myapp.model.Response;
import myapp.jeet.com.myapp.presentar.MainPresentar;
import myapp.jeet.com.myapp.spotify.BaseApplication;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,MainPresentar.MainView{
    @Inject
    RetrofitNetworkClient mRetrofitNetworkClient;
    private CompositeSubscription subscriptions;
    private SearchView searchView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDagger();
        setUpToolbar();
      /*  subscriptions=new CompositeSubscription();

        Subscription subscription = mRetrofitNetworkClient.callNetworkAPI(new RetrofitAPICallBack<CityListResponse>() {
            @Override
            public void onSuccess(CityListResponse o) {

            }

            @Override
            public void onFailure(String message) {

            }
        });
        subscriptions.add(subscription);*/
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private void initializeDagger()
    {
        BaseApplication baseApplication=(BaseApplication)getApplication();
        baseApplication.getMainComponent().inject(this);
    }
    private void initPresentar()
    {

    }

    private void setUpToolbar()
    {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar =getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }
        toolbar.setTitle("Dagger");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_music, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        return true;


    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }

    @Override
    public void setAdapter(ArtistsSearch artistsSearch) {

    }

    @Override
    public void onError() {

    }
}
