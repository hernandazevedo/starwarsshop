package br.com.devhernand.starwarsstore.main;

import java.util.List;

import br.com.devhernand.starwarsstore.base.BaseViewContract;
import br.com.devhernand.starwarsstore.model.Product;

/**
 * Created by Nando on 02/06/2017.
 */

public interface MainView extends BaseViewContract {

        void getProductSuccess(List<Product> productList);
        void onAddToChartSucess();
        void onChartEmpty();
        void onChartSuccess();




}
