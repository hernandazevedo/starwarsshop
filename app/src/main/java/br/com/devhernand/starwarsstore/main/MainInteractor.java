package br.com.devhernand.starwarsstore.main;

import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.services.ProductService;
import rx.Subscription;

/**
 * Created by Nando on 02/06/2017.
 */

public interface MainInteractor {

    Integer getChartSize();
    Subscription getProductList(final ProductService.GetProductListCallback callback);
    void addToChart(Product product, final ProductService.AddToChartCallback callback);
    void clearChart();
}
