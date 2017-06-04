package br.com.devhernand.starwarsstore.app.payment;

import java.util.List;

import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.model.json.Transact;
import br.com.devhernand.starwarsstore.services.PaymentService;
import rx.Subscription;

/**
 * Created by Nando on 04/06/2017.
 */

public interface PaymentInteractor {
    Subscription doPayment(Transact transact, PaymentService.DoPaymentCallback callback);
    List<Product> getProductsInChart();
    void saveTransaction(Transact transact);

    void clearCart();
}
