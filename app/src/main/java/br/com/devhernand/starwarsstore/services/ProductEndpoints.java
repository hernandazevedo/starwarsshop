package br.com.devhernand.starwarsstore.services;

import java.util.List;

import br.com.devhernand.starwarsstore.model.Product;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Nando on 31/05/2017.
 */

public interface ProductEndpoints {
    @GET("products")
    Observable<List<Product>> listProducts();
}
