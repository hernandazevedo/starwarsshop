package br.com.devhernand.starwarsstore.app.chart;

import java.util.List;

import br.com.devhernand.starwarsstore.model.json.Product;

/**
 * Created by Nando on 03/06/2017.
 */

public interface ChartInteractor {

    List<Product> getProductsInChart();
}
