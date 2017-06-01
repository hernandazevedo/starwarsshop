package br.com.devhernand.starwarsstore.services;

import java.util.List;

import br.com.devhernand.starwarsstore.model.Product;
import br.com.devhernand.starwarsstore.modules.networking.NetworkError;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Nando on 31/05/2017.
 */

public class ProductService {


    private final ProductEndpoints productService;

    public ProductService(ProductEndpoints productService) {
        this.productService = productService;
    }

    public Subscription getProductList(final GetProductListCallback callback) {

        return productService.listProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<Product>>>() {
                    @Override
                    public Observable<? extends List<Product>> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<List<Product>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(List<Product> cityListResponse) {
                        callback.onSuccess(cityListResponse);

                    }
                });
    }

    public interface GetProductListCallback{
        void onSuccess(List<Product> cityListResponse);

        void onError(NetworkError networkError);
    }
}
