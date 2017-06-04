package br.com.devhernand.starwarsstore.main;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.WindowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.devhernand.starwarsstore.app.main.MainActivity;
import br.com.devhernand.starwarsstore.test.utils.MainActivityIdlingResource;
import br.com.devhernand.starwarsstore.test.utils.MyViewMatcher;
import br.com.devhernand.starwarsstore.test.utils.MyViewAction;
import br.com.devhernand.starwarsstore.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Nando on 01/06/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityBehaviorTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(
            MainActivity.class);


    @Rule
    public UiThreadTestRule uiThreadTestRule = new UiThreadTestRule();

    private MainActivityIdlingResource idlingResource;
    @Before
    public void registerIntentServiceIdlingResource() {

        try {
            uiThreadTestRule.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    MainActivity activity = mActivityRule.getActivity();
                    activity.getWindow()
                            .addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
                                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

                }
            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        MainActivity activity = mActivityRule.getActivity();
        idlingResource = new MainActivityIdlingResource(activity);
        Espresso.registerIdlingResources(idlingResource);
    }

    @After
    public void unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource);
    }

    @Test
    public void showAddToChartOnClick() {

        onView(withId(R.layout.activity_main));

        onView(withId(R.id.list)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.fabItemLayout)));
        onView(withText(R.string.add_to_char_ok)).inRoot(MyViewMatcher.findToastInRule(mActivityRule)).check(matches(isDisplayed()));


    }

}
