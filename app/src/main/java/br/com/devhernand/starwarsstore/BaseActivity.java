package br.com.devhernand.starwarsstore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.devhernand.starwarsstore.deps.DaggerDeps;

import java.io.File;

import br.com.devhernand.starwarsstore.deps.Deps;
import br.com.devhernand.starwarsstore.modules.networking.NetworkModule;

/**
 * Created by Nando on 31/05/2017.
 */

public class BaseActivity extends AppCompatActivity{
    Deps deps;

    protected ProgressBar progressBar;

    @Override
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
}
