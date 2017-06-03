package br.com.devhernand.starwarsstore.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import br.com.devhernand.starwarsstore.BaseActivity;
import br.com.devhernand.starwarsstore.R;
import br.com.devhernand.starwarsstore.adapter.ProductRecyclerAdapter;
import br.com.devhernand.starwarsstore.model.Product;


/**
 * Created by Nando on 31/05/2017.
 */
public class MainActivity extends BaseActivity implements MainView{

    @Inject
    public MainInteractor mainInteractor;

    private RecyclerView list;

    public MainPresenterImpl presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        renderView();
        init();

        presenter = new MainPresenterImpl(mainInteractor, this);
        presenter.getProductList();
    }


    public void renderView(){
        setContentView(R.layout.activity_main);
        super.initCommonComponents();
        list = (RecyclerView) findViewById(R.id.list);
    }

    public void init(){
        list.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAddToChartSucess() {
        showToast(getString(R.string.add_to_char_ok));
    }

    @Override
    public void removeWait() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        showToast(appErrorMessage);
    }

    @Override
    public void getProductSuccess(List<Product> productList) {

        ProductRecyclerAdapter adapter = new ProductRecyclerAdapter(getApplicationContext(), productList);
        adapter.setOnButtonClickListener(presenter);
        list.setAdapter(adapter);

    }
}
