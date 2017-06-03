package br.com.devhernand.starwarsstore.main;

import java.util.List;

import br.com.devhernand.starwarsstore.model.Product;

/**
 * Created by Nando on 02/06/2017.
 */

public interface MainView {

        void showWait();

        void removeWait();

        void onFailure(String appErrorMessage);

        void getProductSuccess(List<Product> productList);

        void onAddToChartSucess();


}
