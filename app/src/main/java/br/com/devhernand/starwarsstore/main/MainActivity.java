package br.com.devhernand.starwarsstore.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;

import javax.inject.Inject;

import br.com.devhernand.starwarsstore.base.BaseActivity;
import br.com.devhernand.starwarsstore.R;
import br.com.devhernand.starwarsstore.adapter.ProductRecyclerAdapter;
import br.com.devhernand.starwarsstore.chart.ChartActivity;
import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.transaction.TransactionActivity;


/**
 * Created by Nando on 31/05/2017.
 */
public class MainActivity extends BaseActivity implements MainView{

    @Inject
    public MainInteractor mainInteractor;

    private RecyclerView list;
    private BottomNavigationView bottomNavigation;

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
        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
    }

    public void init(){
        initMainToolbar();
        list.setLayoutManager(new LinearLayoutManager(this));
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_buy:
                        presenter.buyItemsClicked();
                        break;
                    case R.id.menu_transactions:
                        TransactionActivity.navigate(MainActivity.this);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onAddToChartSucess() {
        showToast(getString(R.string.add_to_char_ok));
    }

    @Override
    public void onChartEmpty() {
        showToast(getString(R.string.chart_empty_message));
    }



    @Override
    public void getProductSuccess(List<Product> productList) {

        ProductRecyclerAdapter adapter = new ProductRecyclerAdapter(getApplicationContext(), productList);
        adapter.setOnButtonClickListener(presenter);
        list.setAdapter(adapter);

    }

    @Override
    public void onChartSuccess() {
        ChartActivity.navigate(this);
    }

    public static void navigate(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        ActivityCompat.startActivity(activity,intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }
}
