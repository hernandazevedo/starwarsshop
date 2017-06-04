package br.com.devhernand.starwarsstore.app.main;

import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.services.ProductService;
import rx.Subscription;

/**
 * Created by Nando on 02/06/2017.
 */

public class MainInteractorImpl implements MainInteractor{

    public ProductService productService;

    public MainInteractorImpl(ProductService productService){
        this.productService = productService;
    }

    @Override
    public Subscription getProductList(ProductService.GetProductListCallback callback) {

        return productService.getProductList(callback);
    }

    @Override
    public void addToChart(Product product, ProductService.AddToChartCallback callback) {
        productService.addToChart(product,callback);
    }

    @Override
    public void clearChart() {
        productService.clearCart();
    }


    @Override
    public Integer getChartSize() {
        return productService.getChartSize();
    }

}
