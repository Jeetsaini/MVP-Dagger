package myapp.jeet.com.myapp.di;

import javax.inject.Singleton;

import dagger.Component;
import myapp.jeet.com.myapp.presentar.PlayerPresenter;
import myapp.jeet.com.myapp.ui.activity.MainActivity;
import myapp.jeet.com.myapp.ui.player.SpotifyPlayerActivity;

/**
 * Created by Admin on 3/8/2017.
 */

//adding modules to the network
@Singleton
@Component(modules = {NetworkModule.class})
public interface MainComponent {
    //You can inject this module below Activities
    void inject(MainActivity mainActivity);
    void inject(PlayerPresenter playerPresenter);
    void inject(SpotifyPlayerActivity spotifyPlayerActivity);


}
