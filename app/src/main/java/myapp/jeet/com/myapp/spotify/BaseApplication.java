package myapp.jeet.com.myapp.spotify;

import android.app.Application;
import android.content.Context;


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
    }

    public MainComponent getMainComponent(Context context)
    {
        mMainComponent= DaggerMainComponent.builder().networkModule(new NetworkModule(context)).build();

        return mMainComponent;
    }

    public void setMainModule(NetworkModule networkModule, Context context)
    {
        this.mNetworkModule=networkModule;
        mMainComponent= DaggerMainComponent.builder().networkModule(new NetworkModule(context)).build();
    }

    public NetworkModule getNetworkModule()
    {
        return mNetworkModule;
    }



}
