package br.com.devhernand.starwarsstore.app.chart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import br.com.devhernand.starwarsstore.app.base.BaseActivity;
import br.com.devhernand.starwarsstore.R;
import br.com.devhernand.starwarsstore.adapter.ProductRecyclerAdapter;
import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.app.payment.PaymentActivity;

public class ChartActivity extends BaseActivity implements ChartView {

    @Inject
    public ChartInteractor chartInteractor;

    private RecyclerView listChart;
    private ChartPresenter presenter;
    private AppCompatButton btnGoToPayment;

    public static void navigate(Activity activity){
        Intent intent = new Intent(activity, ChartActivity.class);
        ActivityCompat.startActivity(activity,intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        renderView();
        init();

        presenter = new ChartPresenterImpl(chartInteractor, this);
        presenter.showProducts();

    }


    public void init(){
        listChart.setLayoutManager(new LinearLayoutManager(this));
        initToolbar(getString(R.string.your_chart));
        btnGoToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onGoToPaymentPressed();
            }
        });
    }

    public void renderView() {
        setContentView(R.layout.activity_chart);
        super.initCommonComponents();
        listChart = (RecyclerView) findViewById(R.id.listChart);
        btnGoToPayment = (AppCompatButton) findViewById(R.id.btnGoToPayment);
    }

    @Override
    public void onChartEmpty() {
        showToast(getString(R.string.chart_empty_message));
    }

    @Override
    public void onChartSuccess(List<Product> productList) {
        ProductRecyclerAdapter adapter = new ProductRecyclerAdapter(getApplicationContext(), productList);
        adapter.setItemLayout(R.layout.item_product_chart);
        listChart.setAdapter(adapter);
    }

    @Override
    public void navigateToPayment() {
        PaymentActivity.navigate(this);
    }
}
