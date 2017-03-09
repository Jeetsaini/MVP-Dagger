package myapp.jeet.com.myapp.api;



import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Admin on 3/8/2017.
 */

public class RetrofitFactory {


    public static RetroFitService create()
    {


        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();
       return new Retrofit.Builder()
                .baseUrl("http://private-b8cf44-androidcleancode.apiary-mock.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

               .build().create(RetroFitService.class);



    }


}
