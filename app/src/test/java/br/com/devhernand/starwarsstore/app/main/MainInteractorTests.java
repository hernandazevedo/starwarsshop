package br.com.devhernand.starwarsstore.app.main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.modules.networking.NetworkError;
import br.com.devhernand.starwarsstore.services.ProductService;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nando on 04/06/2017.
 */

public class MainInteractorTests {

    public static final Integer SIZE_EXPECTED = 10;

    @Mock
    ProductService productService;

    private MainInteractorImpl interactor;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        interactor = spy(new MainInteractorImpl(productService));
    }


    @Test
    public void testGetProductList(){
        ProductService.GetProductListCallback cb = new ProductService.GetProductListCallback() {
            @Override
            public void onSuccess(List<Product> cityListResponse) {

            }

            @Override
            public void onError(NetworkError networkError) {

            }
        };
        interactor.getProductList(cb);

        verify(productService).getProductList(cb);
    }

    @Test
    public void testAddToChart(){
        ProductService.AddToChartCallback cb = new ProductService.AddToChartCallback() {
            @Override
            public void onSucess() {

            }

            @Override
            public void onError(Exception e) {

            }
        };
        Product product = new Product();
        interactor.addToChart(product,cb);
        verify(productService).addToChart(product,cb);
    }

    @Test
    public void testClearChart(){
        doNothing().when(productService).clearCart();
        interactor.clearChart();
        verify(productService).clearCart();
    }


    @Test
    public void testGetChartSize(){
        when(productService.getChartSize()).thenReturn(SIZE_EXPECTED);
        Integer size = interactor.getChartSize();
        verify(productService).getChartSize();
        Assert.assertNotNull(size);
        Assert.assertEquals(size,SIZE_EXPECTED);
    }
}
