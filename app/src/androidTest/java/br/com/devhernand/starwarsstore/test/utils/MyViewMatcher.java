package br.com.devhernand.starwarsstore.test.utils;

import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.CoreMatchers;

import static org.hamcrest.CoreMatchers.is;

/**
 * Created by Nando on 02/06/2017.
 */

public class MyViewMatcher {

    public static org.hamcrest.Matcher findToastInRule(ActivityTestRule mActivityRule){
        return  RootMatchers.withDecorView(CoreMatchers.not(is(mActivityRule.getActivity().getWindow().getDecorView())));
    }
}
