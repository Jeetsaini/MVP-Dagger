package myapp.jeet.com.myapp.presentar;

import myapp.jeet.com.myapp.IdlingResource.SimpleIdlingResource;
import myapp.jeet.com.myapp.api.RetrofitAPICallBack;
import myapp.jeet.com.myapp.model.RetrofitNetworkClient;
import myapp.jeet.com.myapp.api.model.model.ArtistsSearch;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Admin on 3/9/2017.
 */

public class MainPresentar {

    private RetrofitNetworkClient retrofitNetworkClient;
    private MainView mMainView;
    private CompositeSubscription mCompositeSubscription;
    private SimpleIdlingResource mSimpleIdlingResource;
    public MainPresentar(MainView mainView,RetrofitNetworkClient retrofitNetworkClient) {
        this.mMainView=mainView;
        mCompositeSubscription=new CompositeSubscription();
        this.retrofitNetworkClient=retrofitNetworkClient;
    }

    public void searchMusic(String query)
    {

        /*if(mSimpleIdlingResource==null) {
           // mSimpleIdlingResource=new SimpleIdlingResource();
            mSimpleIdlingResource.setIdleState(true);
        }*/
        mMainView.showLoading();
        Subscription subscription= retrofitNetworkClient.callNetworkAPI(query, new RetrofitAPICallBack<ArtistsSearch>() {
            @Override
            public void onSuccess(ArtistsSearch artistsSearch) {
                mMainView.dismissLoading();
                mMainView.setAdapter(artistsSearch);


               /* if(mSimpleIdlingResource!=null) {
                    mSimpleIdlingResource.setIdleState(false);
                }*/

            }

            @Override
            public void onFailure(String message) {
              mMainView.onError();
            }
        });
        mCompositeSubscription.add(subscription);

    }
    public void setIdlingResource(SimpleIdlingResource simpleIdlingResource)
    {
        this.mSimpleIdlingResource=simpleIdlingResource;
    }

    public interface MainView
    {
        void showLoading();
        void dismissLoading();
        void setAdapter(ArtistsSearch artistsSearch);
        void onError();
    }
}
