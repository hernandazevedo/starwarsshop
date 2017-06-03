package br.com.devhernand.starwarsstore.main;

import br.com.devhernand.starwarsstore.model.Product;

/**
 * Created by Nando on 02/06/2017.
 */

public interface MainPresenter {

    void buyItemsClicked();
    void getProductList();
    void addToChartClicked(Product product);
    void onStop();
}
