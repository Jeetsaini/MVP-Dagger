package myapp.jeet.com.myapp.ui.activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import myapp.jeet.com.myapp.IdlingResource.SimpleIdlingResource;
import myapp.jeet.com.myapp.R;
import myapp.jeet.com.myapp.helpers.RecyclerTouchListener;
import myapp.jeet.com.myapp.model.RetrofitNetworkClient;
import myapp.jeet.com.myapp.api.model.model.Artist;
import myapp.jeet.com.myapp.api.model.model.ArtistsSearch;
import myapp.jeet.com.myapp.ui.adapters.ArtistRecyclerViewAdapter;
import myapp.jeet.com.myapp.helpers.DividerItemDecoration;
import myapp.jeet.com.myapp.presentar.MainPresentar;
import myapp.jeet.com.myapp.spotify.BaseApplication;
import myapp.jeet.com.myapp.ui.player.SpotifyPlayerActivity;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener,MainPresentar.MainView{
    @Inject
    RetrofitNetworkClient mRetrofitNetworkClient;
    private Toolbar toolbar;
    private MainPresentar mMainPresentar;
    private RecyclerView  mRecyclerView;
    private ProgressBar mProgressBar;
    private TextView mNotFound;
    private List<Artist> mArtistList;
    private TextView notFound;

    @Nullable private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=(RecyclerView)findViewById(R.id.artist_recylerview);
        mProgressBar=(ProgressBar)findViewById(R.id.progressbar);
        mNotFound=(TextView)findViewById(R.id.not_found);

        setUpRecylerView(mRecyclerView,getApplicationContext());
        initializeDagger();
        setUpToolbar();
        initPresentar();
        mNotFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              mMainPresentar.notFoundClicked();
            }
        });

    }
    public void setUpRecylerView(RecyclerView recylerView, Context context)
    {
        recylerView.setHasFixedSize(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(context);
        recylerView.setLayoutManager(mLayoutManager);
        recylerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recylerView.setItemAnimator(new DefaultItemAnimator());
        recylerView.addOnItemTouchListener(new RecyclerTouchListener(MainActivity.this, recylerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String artist=mArtistList.get(position).name;
                Intent intent=new Intent(getApplicationContext(), SpotifyPlayerActivity.class);
                intent.putExtra("artist",mArtistList.get(position));
                startActivity(intent);
                Toast.makeText(getApplicationContext(),artist,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


    private void initializeDagger()
    {
        BaseApplication baseApplication=(BaseApplication)getApplication();
        baseApplication.getMainComponent(MainActivity.this).inject(this);
    }
    private void initPresentar()
    {
        mMainPresentar=new MainPresentar(this,mRetrofitNetworkClient);
    }

    private void setUpToolbar()
    {
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        ActionBar actionBar =getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
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
        mMainPresentar.searchMusic(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void dismissLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setAdapter(ArtistsSearch artistsSearch) {
        mArtistList =artistsSearch.getArtists();
        ArtistRecyclerViewAdapter artistRecyclerViewAdapter=new ArtistRecyclerViewAdapter(MainActivity.this, mArtistList);
        mRecyclerView.setAdapter(artistRecyclerViewAdapter);
    }


    @Override
    public void onError() {
        mNotFound.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
            mMainPresentar.setIdlingResource(mIdlingResource);
        }
        return mIdlingResource;
    }

    @Override
    public void notFoundResponse(String toast) {

    }
}
