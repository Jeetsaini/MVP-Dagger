package myapp.jeet.com.myapp.di;

import javax.inject.Singleton;

import dagger.Component;
import myapp.jeet.com.myapp.view.MainActivity;

/**
 * Created by Admin on 3/8/2017.
 */

//adding modules to the network
@Singleton
@Component(modules = {NetworkModule.class})
public interface MainComponent {
    //You can inject this module below Activities
    void inject(MainActivity mainActivity);

}
