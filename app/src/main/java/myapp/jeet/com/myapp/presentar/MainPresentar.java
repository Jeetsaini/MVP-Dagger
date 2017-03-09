package myapp.jeet.com.myapp.presentar;

import javax.inject.Inject;

import myapp.jeet.com.myapp.api.RetrofitAPICallBack;
import myapp.jeet.com.myapp.api.RetrofitNetworkClient;
import myapp.jeet.com.myapp.api.model.model.ArtistsSearch;

/**
 * Created by Admin on 3/9/2017.
 */

public class MainPresentar {
    @Inject
    RetrofitNetworkClient retrofitNetworkClient;
    private MainView mMainView;
    public MainPresentar(MainView mainView) {
        this.mMainView=mainView;
    }

    public void searchMusic(String query)
    {
        mMainView.showLoading();
        retrofitNetworkClient.callNetworkAPI(query, new RetrofitAPICallBack<ArtistsSearch>() {
            @Override
            public void onSuccess(ArtistsSearch artistsSearch) {
                mMainView.dismissLoading();
                mMainView.setAdapter(artistsSearch);
            }

            @Override
            public void onFailure(String message) {
              mMainView.onError();
            }
        });
    }

    public interface MainView
    {
        void showLoading();
        void dismissLoading();
        void setAdapter(ArtistsSearch artistsSearch);
        void onError();
    }
}
