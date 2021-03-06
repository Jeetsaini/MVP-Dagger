package myapp.jeet.com.myapp;

import android.app.Instrumentation;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import myapp.jeet.com.myapp.api.RetroFitService;
import myapp.jeet.com.myapp.di.NetworkModule;
import myapp.jeet.com.myapp.model.RetrofitNetworkClient;
import myapp.jeet.com.myapp.presentar.MainPresentar;
import myapp.jeet.com.myapp.spotify.BaseApplication;
import myapp.jeet.com.myapp.ui.activity.MainActivity;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Admin on 3/10/2017.
 */
@RunWith(AndroidJUnit4.class)
public class MainViewAndroidTest {
    private MainPresentar mainPresentar;
    private RetroFitService mRetroFitService;
    private RetrofitNetworkClient mRetrofitNetworkClient;
    private MainPresentar.MainView mMainView;



    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule=new ActivityTestRule<MainActivity>(MainActivity.class);

    private IdlingResource mIdlingResource;
    @Before
    public void setUP()
    {

        NetworkModule networkModule=new NetworkModule();
        mainPresentar=mock(MainPresentar.class);
        mMainView=mock(MainPresentar.MainView.class);

        mRetroFitService=networkModule.getRetrofitService();

        mRetrofitNetworkClient=networkModule.getRetrofitClient(mRetroFitService);
        mainPresentar=new MainPresentar(mMainView,mRetrofitNetworkClient);
        // mIdlingResource = activityActivityTestRule.getActivity().getIdlingResource();
        // To prove that the test fails, omit this call:
        // Espresso.registerIdlingResources(mIdlingResource);
        /*when(networkModule.getRetrofitService()).thenReturn(mock(RetroFitService.class)); // this is needed to fool dagger
        when(networkModule.getRetrofitClient(mRetroFitService))
                .thenReturn(mRetrofitNetworkClient);*/

        Instrumentation instrumentation = getInstrumentation();
        BaseApplication app
                = (BaseApplication) instrumentation.getTargetContext().getApplicationContext();

        // forced to the application object
        app.setMainModule(networkModule);


    }
    @Test
    public void querySubmited()
    {
        onView(withId(R.id.menu_search)).perform(click());
        onView(withId(android.support.v7.appcompat.R.id.search_src_text)).perform(typeText("company"), pressKey(KeyEvent.KEYCODE_ENTER));

    }

    @Test
    public void recyclerViewClickTest()
    {
        onView(withId(R.id.menu_search)).perform(click());
        onView(withId(android.support.v7.appcompat.R.id.search_src_text)).perform(typeText("company"), pressKey(KeyEvent.KEYCODE_ENTER));
        onView(withId(R.id.artist_recylerview))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }
}
