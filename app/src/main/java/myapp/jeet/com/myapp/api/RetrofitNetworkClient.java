package myapp.jeet.com.myapp.api;

import myapp.jeet.com.myapp.api.model.model.ArtistsSearch;
import myapp.jeet.com.myapp.model.CityListResponse;
import myapp.jeet.com.myapp.model.Response;
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

                  }

                  @Override
                  public void onNext(ArtistsSearch myModelClass) {
                      mRetrofitAPICallBack.onSuccess((T)myModelClass);
                  }
              });
        return subscription;
    }

}
