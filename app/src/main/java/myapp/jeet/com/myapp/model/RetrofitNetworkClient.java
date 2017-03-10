package myapp.jeet.com.myapp.model;

import android.util.Log;

import myapp.jeet.com.myapp.api.RetroFitService;
import myapp.jeet.com.myapp.api.RetrofitAPICallBack;
import myapp.jeet.com.myapp.api.model.model.ArtistsSearch;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Admin on 3/8/2017.
 */

public class RetrofitNetworkClient<T> {
    private RetroFitService mRetroFitService;
    public RetrofitNetworkClient(RetroFitService retroFitService)
    {
        this.mRetroFitService=retroFitService;
    }

    public Subscription callNetworkAPI(String query,final RetrofitAPICallBack<T> mRetrofitAPICallBack)
    {
      Subscription subscription= mRetroFitService.searchArtist(query)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(new Observer<ArtistsSearch>() {
                  @Override
                  public void onCompleted() {

                  }

                  @Override
                  public void onError(Throwable e) {
                      Log.d("Error",e.toString());
                  }

                  @Override
                  public void onNext(ArtistsSearch myModelClass) {
                      mRetrofitAPICallBack.onSuccess((T)myModelClass);
                  }
              });
        return subscription;
    }

}
