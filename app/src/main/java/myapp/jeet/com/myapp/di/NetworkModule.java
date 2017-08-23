package myapp.jeet.com.myapp.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import myapp.jeet.com.myapp.api.RetroFitService;
import myapp.jeet.com.myapp.api.RetrofitFactory;
import myapp.jeet.com.myapp.model.PlayerSpotify;
import myapp.jeet.com.myapp.model.RetrofitNetworkClient;

/**
 * Created by Admin on 3/8/2017.
 */
@Module
public class NetworkModule {
    private RetroFitService mRetroFitService;
    private Context mContext;

    public NetworkModule(Context context)
    {
        this.mContext=context;
    }

   /* @Provides
    @Singleton
    public RetroFitService getRetrofitService()
    {
        mRetroFitService= RetrofitFactory.create();

        return mRetroFitService;
    }

    @Provides
    @Singleton
    public RetrofitNetworkClient getRetrofitClient(RetroFitService retroFitService)
    {
        RetrofitNetworkClient retrofitNetworkClient=new RetrofitNetworkClient(retroFitService);
        return retrofitNetworkClient;
    }

    @Provides
    @Singleton
    public PlayerSpotify getPlayerSpotify()
    {
        return new PlayerSpotify(mContext);
    }*/




}
