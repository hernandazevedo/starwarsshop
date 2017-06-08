package br.com.devhernand.starwarsstore.app.main;

import android.support.annotation.NonNull;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.modules.networking.NetworkError;
import br.com.devhernand.starwarsstore.services.ProductService;
import rx.Subscription;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


/**
 * Created by Nando on 02/06/2017.
 */

public class MainPresenterTests {

    private MainPresenterImpl presenter;

    private static final String ERROR_MESSAGE = "Something went wrong.";

    @Mock
    private MainInteractorImpl mainInteractor;

    @Mock
    private MainActivity mainActivity;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        presenter = spy(new MainPresenterImpl(mainInteractor,mainActivity));
    }

    @Test
    public void testShouldGetProducts(){
        Mockito.when( mainInteractor.getProductList(any(ProductService.GetProductListCallback.class)) ).thenReturn(getFakeSubscription());
        presenter.getProductList();
        verify(mainActivity).showWait();
    }

    @Test
    public void testShouldNotifyViewOnProducts(){
        List<Product> productList = new ArrayList<>();
        presenter.onSuccess(productList);

        verify(mainActivity).removeWait();
        verify(mainActivity).getProductSuccess(productList);
    }

    @Test
    public void testShouldNotifyViewOnError(){

        NetworkError e = new NetworkError(new Exception(ERROR_MESSAGE));
        presenter.onError(e);
        verify(mainActivity).removeWait();
        verify(mainActivity).showMessage(ERROR_MESSAGE);
    }

    @Test
    public void testShouldAddToCharOnClicked(){
        Product product = generateProduct();
        presenter.addToChartClicked(product);
        verify(mainActivity).showWait();
        verify(mainInteractor).addToChart(product,presenter);
    }

    @Test
    public void testOnStop(){
        presenter.onStop();
    }

    @Test
    public void testOnSucess(){
        presenter.onSucess();
        verify(mainActivity).removeWait();
        verify(mainActivity).onAddToChartSucess();
    }


//    @Test
//    public void testOnError(){
//        Exception e = new Exception(ERROR_MESSAGE);
////        presenter.onError(e);
//        verify(mainActivity).showMessage(ERROR_MESSAGE);
//    }

    @Test
    public void testOnButtonClick(){
        View v = new View(mainActivity);
        Product product = generateProduct();
        presenter.onButtonClick(v,product);
        verify(presenter).addToChartClicked(product);
    }

    @Test
    public void testBuyItemsClicked(){
        when(mainInteractor.getChartSize()).thenReturn(1);
        presenter.buyItemsClicked();
        verify(mainActivity).onChartSuccess();
    }

    @Test
    public void testBuyItemsClickedEmpty(){
        when(mainInteractor.getChartSize()).thenReturn(0);
        presenter.buyItemsClicked();
        verify(mainActivity).onChartEmpty();
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

    @NonNull
    private Subscription getFakeSubscription() {
        return new Subscription() {
            @Override
            public void unsubscribe() {

            }

            @Override
            public boolean isUnsubscribed() {
                return false;
            }
        };
    }

}
