package br.com.devhernand.starwarsstore.payment;

import java.util.List;

import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.model.json.Transact;
import br.com.devhernand.starwarsstore.repository.TransactRepository;
import br.com.devhernand.starwarsstore.services.PaymentService;
import br.com.devhernand.starwarsstore.services.ProductService;
import rx.Subscription;

/**
 * Created by Nando on 04/06/2017.
 */

public class PaymentInteractorImpl implements PaymentInteractor{

    private ProductService productService;
    private TransactRepository transactRepository;
    private PaymentService paymentService;
    public PaymentInteractorImpl(ProductService productService,TransactRepository transactRepository,PaymentService paymentService) {
        this.productService = productService;
        this.transactRepository = transactRepository;
        this.paymentService = paymentService;
    }

    @Override
    public Subscription doPayment(Transact transact, PaymentService.DoPaymentCallback callback) {

        return paymentService.doPayment(transact,callback);
    }

    @Override
    public List<Product> getProductsInChart() {
        return productService.getProductsInChart();
    }
}
