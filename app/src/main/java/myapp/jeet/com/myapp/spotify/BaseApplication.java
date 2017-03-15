package myapp.jeet.com.myapp.spotify;

import android.app.Application;


import myapp.jeet.com.myapp.di.DaggerMainComponent;
import myapp.jeet.com.myapp.di.MainComponent;
import myapp.jeet.com.myapp.di.NetworkModule;

/**
 * Created by Admin on 3/8/2017.
 */

public class BaseApplication extends Application{
    private MainComponent mMainComponent;
    private NetworkModule mNetworkModule;

    @Override
    public void onCreate() {
        super.onCreate();
        mMainComponent= DaggerMainComponent.builder().networkModule(new NetworkModule()).build();
    }

    public MainComponent getMainComponent()
    {
        return mMainComponent;
    }

    public void setMainModule(NetworkModule networkModule)
    {
        this.mNetworkModule=networkModule;
        mMainComponent= DaggerMainComponent.builder().networkModule(new NetworkModule()).build();


    }



}
