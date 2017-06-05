package br.com.devhernand.starwarsstore.app.chart;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.services.ProductService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nando on 05/06/2017.
 */
public class ChartPresenterTests {


    @Mock
    private ChartInteractor interactor;

    @Mock
    private ChartView view;

    private ChartPresenterImpl presenter;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        presenter = new ChartPresenterImpl(interactor,view);
    }


    @Test
    public void showProducts() throws Exception {

        List<Product> productList = new ArrayList<>();
        productList.add(generateProduct());

        when(interactor.getProductsInChart()).thenReturn(productList);
        presenter.showProducts();
        verify(view).showWait();
        verify(interactor).getProductsInChart();
        verify(view).removeWait();
        verify(view).onChartSuccess(productList);

    }

    @Test
    public void showProductsEmpty() throws Exception {

        List<Product> productList = new ArrayList<>();

        when(interactor.getProductsInChart()).thenReturn(productList);
        presenter.showProducts();
        verify(view).showWait();
        verify(interactor).getProductsInChart();
        verify(view).removeWait();
        verify(view).onChartEmpty();
    }

    @Test
    public void showProductsNullList() throws Exception {

        List<Product> productList = null;

        when(interactor.getProductsInChart()).thenReturn(productList);
        presenter.showProducts();
        verify(view).showWait();
        verify(interactor).getProductsInChart();
        verify(view).removeWait();
        verify(view).onChartEmpty();

    }

    @Test
    public void onGoToPaymentPressed() throws Exception {
        presenter.onGoToPaymentPressed();
        verify(view).navigateToPayment();
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