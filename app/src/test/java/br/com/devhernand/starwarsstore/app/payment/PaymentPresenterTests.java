package br.com.devhernand.starwarsstore.app.payment;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.model.json.Transact;
import br.com.devhernand.starwarsstore.modules.networking.NetworkError;
import br.com.devhernand.starwarsstore.services.PaymentService;

import static org.junit.Assert.*;


import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


/**
 * Created by Nando on 05/06/2017.
 */
public class PaymentPresenterTests {

    public static final long PRICE = 200L;
    private PaymentPresenterImpl presenter;

    @Mock
    private PaymentInteractor interactor;

    @Mock
    private PaymentView view;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new PaymentPresenterImpl(interactor,view);
    }

    @Test
    public void doPayment() throws Exception {
        String cardNumber = "8918-8988-8498-1498";
        String name = "HERNAND S AZEVEDO";
        String month = "08";
        String year = "2019";
        String cvv = "123";
        presenter.doPayment(cardNumber,name,month,year,cvv);

        verify(view).showWait();
        verify(interactor).doPayment(any(Transact.class),any(PaymentService.DoPaymentCallback.class));
    }

    @Test
    public void onCreate() throws Exception {

        List<Product> productList = new ArrayList<>();
        productList.add(generateProduct(PRICE));
        when(interactor.getProductsInChart()).thenReturn(productList);
        presenter.onCreate();
        verify(view).onCreateSuccess(anyString());

    }

    @Test
    public void onCreateSumZero() throws Exception {

        List<Product> productList = new ArrayList<>();
        productList.add(generateProduct(0L));
        when(interactor.getProductsInChart()).thenReturn(productList);
        presenter.onCreate();
        verify(view).onCreateError();

    }

    @Test
    public void onStop() throws Exception {
        presenter.onStop();
    }

    @Test
    public void onSendPaymentSuccess() throws Exception {
        Transact transact = getTransact();
        presenter.onSendPaymentSuccess(transact);

        verify(interactor).saveTransaction(transact);
        verify(view,times(1)).removeWait();
        verify(interactor).clearCart();
        verify(view).onPaymentSucess();

    }

    @Test
    public void onSendPaymentSuccessException() throws Exception {
        Transact transact = getTransact();

        Exception exception = new RuntimeException();
        doThrow(exception).when(interactor).saveTransaction(any(Transact.class));


        presenter.onSendPaymentSuccess(transact);

        verify(interactor).saveTransaction(transact);
        verify(view,times(1)).removeWait();
        verify(interactor,times(0)).clearCart();
        verify(view,times(0)).onPaymentSucess();

    }

    @Test
    public void onSendPaymentError() throws Exception {
        NetworkError networkError = new NetworkError(new RuntimeException());
        presenter.onSendPaymentError(networkError);
        verify(view).removeWait();
        verify(view).showMessage(anyString());
    }

    @NonNull
    private Product generateProduct(Long price) {
        Product product = new Product();
        product.setPrice(price != null ? price : PRICE);
        product.setDate("12/01/2019");
        product.setSeller("Some Selller");
        product.setThumbnailHd("http://sometumbnail.com/1234");
        product.setTitle("Product title");
        product.setZipcode("20230260");
        return product;
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

}