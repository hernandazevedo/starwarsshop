package br.com.devhernand.starwarsstore.test.utils;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.core.deps.guava.base.CharMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Assert;

/**
 * Created by Nando on 05/06/2017.
 */

public class RecyclerViewItemCountAssertion  implements ViewAssertion {

    private final Matcher<Integer> matcher;

    public RecyclerViewItemCountAssertion(int expectedCount) {
        this.matcher = CoreMatchers.is(expectedCount);
    }

    public RecyclerViewItemCountAssertion(Matcher<Integer> matcher) {
        this.matcher = matcher;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        Assert.assertThat(adapter.getItemCount(), matcher);
    }
}
