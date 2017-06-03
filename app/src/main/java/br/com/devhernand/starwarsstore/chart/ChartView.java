package br.com.devhernand.starwarsstore.chart;

import java.util.List;

import br.com.devhernand.starwarsstore.base.BaseViewContract;
import br.com.devhernand.starwarsstore.model.Product;

/**
 * Created by Nando on 03/06/2017.
 */

public interface ChartView extends BaseViewContract{

    void onChartEmpty();
    void onChartSuccess(List<Product> productList);
}
