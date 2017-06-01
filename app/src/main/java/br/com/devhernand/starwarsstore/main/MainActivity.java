package br.com.devhernand.starwarsstore.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import br.com.devhernand.starwarsstore.BaseActivity;
import br.com.devhernand.starwarsstore.R;
import br.com.devhernand.starwarsstore.adapter.ProductRecyclerAdapter;
import br.com.devhernand.starwarsstore.model.Product;
import br.com.devhernand.starwarsstore.services.ProductService;


/**
 * Created by Nando on 31/05/2017.
 */
public class MainActivity extends BaseActivity implements MainView{

    @Inject
    public ProductService productService;

    private RecyclerView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        renderView();
        init();

        MainPresenter presenter = new MainPresenter(productService, this);
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
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
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

        ProductRecyclerAdapter adapter = new ProductRecyclerAdapter(getApplicationContext(), productList, new ProductRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Product viewModel) {
                showToast(viewModel.getTitle());
            }
        });

        list.setAdapter(adapter);

    }
}
