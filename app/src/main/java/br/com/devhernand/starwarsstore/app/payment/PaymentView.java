package br.com.devhernand.starwarsstore.app.payment;

import br.com.devhernand.starwarsstore.app.base.BaseViewContract;

/**
 * Created by Nando on 04/06/2017.
 */

public interface PaymentView extends BaseViewContract{

    void onPaymentSucess();
    void onCreateSuccess(String totalShoppingSum);

    void onCreateError();
}
