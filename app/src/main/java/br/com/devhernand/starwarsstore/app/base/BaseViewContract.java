package br.com.devhernand.starwarsstore.app.base;

/**
 * Created by Nando on 03/06/2017.
 */

public interface BaseViewContract {

    void showWait();

    void removeWait();

    void showMessage(String message);
}
