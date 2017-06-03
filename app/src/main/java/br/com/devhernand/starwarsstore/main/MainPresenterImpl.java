package br.com.devhernand.starwarsstore.main;

import android.view.View;

import java.util.List;

import br.com.devhernand.starwarsstore.R;
import br.com.devhernand.starwarsstore.adapter.ProductRecyclerAdapter;
import br.com.devhernand.starwarsstore.model.Product;
import br.com.devhernand.starwarsstore.modules.networking.NetworkError;
import br.com.devhernand.starwarsstore.services.ProductService;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Nando on 31/05/2017.
 */

public class MainPresenterImpl implements MainPresenter,ProductService.GetProductListCallback,ProductService.AddToChartCallback, ProductRecyclerAdapter.OnButtonClickListener {

    private final MainInteractor mainInteractor;
    private final MainView view;
    private CompositeSubscription subscriptions;

    public MainPresenterImpl(MainInteractor mainInteractor, MainView view) {
        this.mainInteractor = mainInteractor;
        this.view = view;
        this.subscriptions = new CompositeSubscription();
    }

    @Override
    public void getProductList() {
        view.showWait();

        Subscription subscription = mainInteractor.getProductList(this);

        subscriptions.add(subscription);
    }

    @Override
    public void buyItemsClicked() {
        view.showWait();

        if(mainInteractor.getChartSize() > 0){
            view.onChartSuccess();
        }else{
            view.removeWait();
            view.onChartEmpty();
        }

    }

    @Override
    public void onSuccess(List<Product> productList) {
        view.removeWait();
        view.getProductSuccess(productList);
    }

    @Override
    public void onError(NetworkError networkError) {
        view.removeWait();
        view.showMessage(networkError.getMessage());
    }

    @Override
    public void addToChartClicked(Product product){
        view.showWait();
        mainInteractor.addToChart(product, this);
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

    @Override
    public void onSucess() {
        view.removeWait();
        view.onAddToChartSucess();
    }

    @Override
    public void onError(Exception e) {
        view.showMessage(e.getMessage());
    }

    @Override
    public void onButtonClick(View view, Product viewModel) {
        addToChartClicked(viewModel);
    }

}
