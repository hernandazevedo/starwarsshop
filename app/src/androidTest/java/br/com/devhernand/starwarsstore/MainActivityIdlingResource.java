package br.com.devhernand.starwarsstore;

import android.support.test.espresso.IdlingResource;
import android.widget.ProgressBar;

import br.com.devhernand.starwarsstore.main.MainActivity;

/**
 * Created by Nando on 01/06/2017.
 */

public class MainActivityIdlingResource implements IdlingResource {

    private MainActivity activity;
    private IdlingResource.ResourceCallback callback;

    public MainActivityIdlingResource(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    public String getName() {
        return "MainActivityIdleName";
    }

    @Override
    public boolean isIdleNow() {
        Boolean idle = isIdle();
        if (idle) callback.onTransitionToIdle();
        return idle;
    }

    public boolean isIdle() {
        return activity != null && callback != null && activity.progressBar.getVisibility() != ProgressBar.VISIBLE;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.callback = resourceCallback;
    }
}