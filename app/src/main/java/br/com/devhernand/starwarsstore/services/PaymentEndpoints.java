package br.com.devhernand.starwarsstore.services;

import java.util.List;

import br.com.devhernand.starwarsstore.model.json.Product;
import br.com.devhernand.starwarsstore.model.json.Transact;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Nando on 28/05/2017.
 */

public interface PaymentEndpoints {


    @Headers("Content-Type: application/json")
    @POST("products")
    Observable<Transact> doPayment(@Body Transact body);


}
