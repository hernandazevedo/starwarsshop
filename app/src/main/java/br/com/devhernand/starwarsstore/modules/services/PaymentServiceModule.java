package br.com.devhernand.starwarsstore.modules.services;

import javax.inject.Singleton;

import br.com.devhernand.starwarsstore.modules.networking.NetworkModule;
import br.com.devhernand.starwarsstore.services.PaymentEndpoints;
import br.com.devhernand.starwarsstore.services.PaymentService;
import br.com.devhernand.starwarsstore.services.ProductEndpoints;
import br.com.devhernand.starwarsstore.services.ProductService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Nando on 31/05/2017.
 */

@Module(includes = {NetworkModule.class,})
public class PaymentServiceModule {

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public PaymentService proPaymentService(
            PaymentEndpoints endpoints) {
        return new PaymentService(endpoints);
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public PaymentEndpoints providesEndpoints(
            Retrofit retrofit) {
        return retrofit.create(PaymentEndpoints.class);
    }



}
