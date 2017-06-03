package br.com.devhernand.starwarsstore;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;


import java.io.File;
import br.com.devhernand.starwarsstore.deps.DaggerDeps;
import br.com.devhernand.starwarsstore.deps.Deps;
import br.com.devhernand.starwarsstore.modules.networking.NetworkModule;

/**
 * Created by Nando on 31/05/2017.
 */

public class BaseActivity extends AppCompatActivity{
    Deps deps;

    public ProgressBar progressBar;

    @Override
    @SuppressWarnings("deprecation")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        deps = DaggerDeps.builder().networkModule(new NetworkModule(cacheFile)).build();

    }

    public Deps getDeps() {
        return deps;
    }

    public void initCommonComponents(){
        progressBar = (ProgressBar) findViewById(R.id.progress);
    }

    protected void showToast(String text,Integer length){
        Toast.makeText(getApplicationContext(), text,
                length != null ? length : Toast.LENGTH_LONG).show();
    }

    protected void showToast(String text){
        showToast(text,null);
    }

    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }


    public void showMessage(String appErrorMessage) {
        showToast(appErrorMessage);
    }


    public void initToolbar(String title){
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);

        if(title != null)
            toolbar.setTitle(title);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void initMainToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

}
