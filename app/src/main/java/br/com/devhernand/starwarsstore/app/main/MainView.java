package br.com.devhernand.starwarsstore.app.main;

import java.util.List;

import br.com.devhernand.starwarsstore.app.base.BaseViewContract;
import br.com.devhernand.starwarsstore.model.json.Product;

/**
 * Created by Nando on 02/06/2017.
 */

public interface MainView extends BaseViewContract {

        void getProductSuccess(List<Product> productList);
        void onAddToChartSucess();
        void onChartEmpty();
        void onChartSuccess();




}
