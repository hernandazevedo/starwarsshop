package br.com.devhernand.starwarsstore.services;

import java.util.ArrayList;
import java.util.List;

import br.com.devhernand.starwarsstore.model.json.Product;
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


    private final ProductEndpoints productEndpoints;

    private static List<Product> productList = new ArrayList<>();


    public ProductService(ProductEndpoints productEndpoints) {
        this.productEndpoints = productEndpoints;
    }

    public Subscription getProductList(final GetProductListCallback callback) {

        return productEndpoints.listProducts()
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

    public void addToChart(Product product,final AddToChartCallback callback) {

        productList.add(product);
        callback.onSucess();

    }

    public void clearCart() {
        clearCart(null);
    }

    public Integer getChartSize() {
        return productList.size();
    }

    public void clearCart(final ClearChartCallback callback) {

        productList.clear();
        if(callback != null)
            callback.onSucess();

    }

    public List<Product> getProductsInChart() {
        return productList;
    }

    public interface GetProductListCallback{
        void onSuccess(List<Product> cityListResponse);

        void onError(NetworkError networkError);
    }

    public interface AddToChartCallback {

        void onSucess();
//        void onError(Exception e);
    }

    public interface ClearChartCallback extends  AddToChartCallback {


    }
}
