package br.com.devhernand.starwarsstore.payment;

import java.io.Serializable;

/**
 * Created by Nando on 04/06/2017.
 */

public interface PaymentPresenter {

    void doPayment(String cardNumber,String name,String month,String year,String cvv);
    void onStop();

    void onCreate();
}
