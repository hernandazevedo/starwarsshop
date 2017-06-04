package br.com.devhernand.starwarsstore.modules;

import javax.inject.Singleton;

import br.com.devhernand.starwarsstore.modules.networking.NetworkModule;
import br.com.devhernand.starwarsstore.modules.services.PaymentServiceModule;
import br.com.devhernand.starwarsstore.modules.services.ProductServiceModule;
import br.com.devhernand.starwarsstore.payment.PaymentInteractor;
import br.com.devhernand.starwarsstore.payment.PaymentInteractorImpl;
import br.com.devhernand.starwarsstore.repository.TransactRepository;
import br.com.devhernand.starwarsstore.repository.TransactRepositoryImpl;
import br.com.devhernand.starwarsstore.services.PaymentService;
import br.com.devhernand.starwarsstore.services.ProductService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Nando on 04/06/2017.
 */

@Module(includes = {NetworkModule.class,ProductServiceModule.class, PaymentServiceModule.class,RepositoryModule.class,})
public class PaymentModule {



    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public PaymentInteractor providesPaymentInteractor(
            ProductService productService, TransactRepository transactRepository, PaymentService paymentService) {
        return new PaymentInteractorImpl(productService,transactRepository,paymentService);
    }
}
