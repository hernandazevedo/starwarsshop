package br.com.devhernand.starwarsstore.app.chart;

import java.util.List;

import br.com.devhernand.starwarsstore.model.json.Product;

/**
 * Created by Nando on 03/06/2017.
 */

public class ChartPresenterImpl implements ChartPresenter{


    private final ChartInteractor chartInteractor;
    private final ChartView view;

    public ChartPresenterImpl(ChartInteractor chartInteractor, ChartView view) {
        this.chartInteractor = chartInteractor;
        this.view = view;

    }


    @Override
    public void showProducts() {
        view.showWait();

        List<Product> productList = chartInteractor.getProductsInChart();
        view.removeWait();

        if(productList != null && !productList.isEmpty()){
            view.onChartSuccess(productList);
        }
//        else {
//            view.onChartEmpty();
//        }
    }

    public void onGoToPaymentPressed(){
        view.navigateToPayment();
    }

}
