package br.com.devhernand.starwarsstore.app.payment;

import android.support.annotation.NonNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.devhernand.starwarsstore.model.TransactEntity;
import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.model.json.Transact;
import br.com.devhernand.starwarsstore.modules.networking.NetworkError;
import br.com.devhernand.starwarsstore.repository.TransactRepository;
import br.com.devhernand.starwarsstore.services.PaymentService;
import br.com.devhernand.starwarsstore.services.ProductService;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


/**
 * Created by Nando on 05/06/2017.
 */
public class PaymentInteractorTests {

    @Mock
    private ProductService productService;

    @Mock
    private TransactRepository transactRepository;

    @Mock
    private PaymentService paymentService;


    private PaymentInteractorImpl interactor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        interactor = new PaymentInteractorImpl(productService,transactRepository,paymentService);
    }

    @Test
    public void doPayment() throws Exception {
        Transact transact = getTransact();
        PaymentService.DoPaymentCallback callback = getCallbackMock();
        interactor.doPayment(getTransact(), callback);
        verify(paymentService).doPayment(any(Transact.class),any(PaymentService.DoPaymentCallback.class));
        Assert.assertNotNull(transact.getCvv());
        Assert.assertNotNull(transact.getExpDate());
    }

    @NonNull
    private PaymentService.DoPaymentCallback getCallbackMock() {
        return new PaymentService.DoPaymentCallback() {
            @Override
            public void onSendPaymentSuccess(Transact transactCompleted) {

            }

            @Override
            public void onSendPaymentError(NetworkError networkError) {

            }
        };
    }

    @Test
    public void getProductsInChart() throws Exception {
        List<Product> productList = new ArrayList<>();
        productList.add(generateProduct());
        when(productService.getProductsInChart()).thenReturn(productList);

        List<Product> returnList = interactor.getProductsInChart();

        verify(productService).getProductsInChart();

        Assert.assertNotNull(returnList);
        Assert.assertFalse(returnList.isEmpty());

    }

    @Test
    public void saveTransaction() throws Exception {
        Transact transact = getTransact();
        interactor.saveTransaction(transact);
        verify(transactRepository).saveTransaction(transact.getCardNumber(),transact.getCardHolderName(),transact.getValue().toString());
    }

    @Test
    public void clearCart() throws Exception {
        interactor.clearCart();
        verify(productService).clearCart();
    }

    @NonNull
    private Transact getTransact() {
        Transact transact = new Transact();
        transact.setCardHolderName("Name Mock");
        transact.setCardNumber("****-****-****-1452");
        transact.setValue(7000L);
        transact.setDateTime("05/06/2017 15:33:00");
        transact.setCvv(213L);
        transact.setExpDate("12/2018");
        return transact;
    }

    @NonNull
    private Product generateProduct() {
        Product product = new Product();
        product.setPrice(200L);
        product.setDate("12/01/2019");
        product.setSeller("Some Selller");
        product.setThumbnailHd("http://sometumbnail.com/1234");
        product.setTitle("Product title");
        product.setZipcode("20230260");
        return product;
    }

}