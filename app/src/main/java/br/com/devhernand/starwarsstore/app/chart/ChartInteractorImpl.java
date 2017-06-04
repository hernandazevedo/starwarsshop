package br.com.devhernand.starwarsstore.app.chart;

import java.util.List;

import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.services.ProductService;

/**
 * Created by Nando on 03/06/2017.
 */

public class ChartInteractorImpl implements ChartInteractor {

    private ProductService productService;

    public ChartInteractorImpl(ProductService productService) {
        this.productService = productService;
    }


    @Override
    public List<Product> getProductsInChart() {
        return productService.getProductsInChart();
    }
}
