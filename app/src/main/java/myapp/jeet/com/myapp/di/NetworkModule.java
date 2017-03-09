package myapp.jeet.com.myapp.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import myapp.jeet.com.myapp.api.RetroFitService;
import myapp.jeet.com.myapp.api.RetrofitFactory;
import myapp.jeet.com.myapp.api.RetrofitNetworkClient;

/**
 * Created by Admin on 3/8/2017.
 */
@Module
public class NetworkModule {
    private RetroFitService mRetroFitService;

    @Provides
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




}
