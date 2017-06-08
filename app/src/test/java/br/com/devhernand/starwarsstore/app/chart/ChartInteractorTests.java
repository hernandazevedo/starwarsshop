package br.com.devhernand.starwarsstore.app.chart;

import android.support.annotation.NonNull;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import br.com.devhernand.starwarsstore.app.transaction.TransactionInteractorImpl;
import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.services.ProductService;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nando on 05/06/2017.
 */
public class ChartInteractorTests {


    @Mock
    private ProductService productService;

    private ChartInteractorImpl interactor;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        interactor = new ChartInteractorImpl(productService);
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
        Assert.assertEquals(productList.get(0).getZipcode(), returnList.get(0).getZipcode());

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