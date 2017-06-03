package br.com.devhernand.starwarsstore.modules;

import javax.inject.Singleton;

import br.com.devhernand.starwarsstore.main.MainInteractorImpl;
import br.com.devhernand.starwarsstore.modules.networking.NetworkModule;
import br.com.devhernand.starwarsstore.services.ProductEndpoints;
import br.com.devhernand.starwarsstore.services.ProductService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Nando on 31/05/2017.
 */

@Module(includes = {NetworkModule.class,})
public class ProductModule {

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public ProductService providesProductService(
            ProductEndpoints productEndpoints) {
        return new ProductService(productEndpoints);
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public ProductEndpoints providesProductEndepoints(
            Retrofit retrofit) {
        return retrofit.create(ProductEndpoints.class);
    }



}
