package br.com.devhernand.starwarsstore.main;

import java.util.List;

import br.com.devhernand.starwarsstore.model.Product;
import br.com.devhernand.starwarsstore.modules.networking.NetworkError;
import br.com.devhernand.starwarsstore.services.ProductService;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Nando on 31/05/2017.
 */

public class MainPresenter {

    private final ProductService service;
    private final MainView view;
    private CompositeSubscription subscriptions;

    public MainPresenter(ProductService service, MainView view) {
        this.service = service;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    public void getProductList() {
        view.showWait();

        Subscription subscription = service.getProductList(new ProductService.GetProductListCallback() {
            @Override
            public void onSuccess(List<Product> productList) {
                view.removeWait();
                view.getProductSuccess(productList);
            }

            @Override
            public void onError(NetworkError networkError) {
                view.removeWait();
                view.onFailure(networkError.getMessage());
            }
        });

        subscriptions.add(subscription);
    }


    public void onStop() {
        subscriptions.unsubscribe();
    }
}
