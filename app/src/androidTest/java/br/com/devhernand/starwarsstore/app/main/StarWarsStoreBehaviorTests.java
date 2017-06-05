package br.com.devhernand.starwarsstore.app.main;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.UiThreadTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.WindowManager;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import br.com.devhernand.starwarsstore.test.utils.MainActivityIdlingResource;
import br.com.devhernand.starwarsstore.test.utils.MyViewMatcher;
import br.com.devhernand.starwarsstore.test.utils.MyViewAction;
import br.com.devhernand.starwarsstore.R;
import br.com.devhernand.starwarsstore.test.utils.RecyclerViewItemCountAssertion;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static android.support.test.espresso.action.ViewActions.click;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by Nando on 01/06/2017.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StarWarsStoreBehaviorTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(
            MainActivity.class);


    @Rule
    public UiThreadTestRule uiThreadTestRule = new UiThreadTestRule();

    private MainActivityIdlingResource idlingResource;
    @Before
    public void registerIntentServiceIdlingResource() {

        try {
            //Unlock the screen; FIXME create some method to reuse this
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


        //Wait the activity to start completely
        MainActivity activity = mActivityRule.getActivity();
        idlingResource = new MainActivityIdlingResource(activity);
        Espresso.registerIdlingResources(idlingResource);
    }

    @After
    public void unregisterIntentServiceIdlingResource() {
        Espresso.unregisterIdlingResources(idlingResource);
    }

    /**
     * This test adds on item to the chart
     */
    @Test
    public void test1() {


        onView(withId(R.id.list)).perform(
                RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.fabItemLayout)));
        onView(withText(R.string.add_to_char_ok)).inRoot(MyViewMatcher.findToastInRule(mActivityRule)).check(matches(isDisplayed()));


    }


    /**
     * This test fill a payment order filling all the fields
     */
    @Test
    public void test2() {


        onView(withId(R.id.menu_buy)).perform(click());

        onView(withId(R.id.btnGoToPayment)).check(matches(isDisplayed()));
        onView(withId(R.id.btnGoToPayment)).perform(click());

        onView(withId(R.id.etCardNumber)).check(matches(isDisplayed()));
        onView(withId(R.id.etCardNumber)).perform(click(), replaceText("1234123412341234"));
        onView(withId(R.id.etName)).check(matches(isDisplayed()));
        onView(withId(R.id.etName)).perform(click(), replaceText("HERNAND"));
        onView(withId(R.id.etMonth)).check(matches(isDisplayed()));
        onView(withId(R.id.etMonth)).perform(click(), replaceText("08"));
        onView(withId(R.id.etYear)).check(matches(isDisplayed()));
        onView(withId(R.id.etYear)).perform(click(), replaceText("2019"));
        onView(withId(R.id.etCvv)).check(matches(isDisplayed()));
        onView(withId(R.id.etCvv)).perform(click(), replaceText("123"));
        onView(withId(R.id.btnPay)).check(matches(isDisplayed()));
        onView(withId(R.id.btnPay)).perform(click());

    }


    /**
     * This test check the activity transactions
     */
    @Test
    public void test3(){
        onView(withId(R.id.menu_transactions)).perform(click());

        //Check if we have the transaction done before
        onView(withId(R.id.listTransactions)).check(new RecyclerViewItemCountAssertion(greaterThan(0)));
    }

}
