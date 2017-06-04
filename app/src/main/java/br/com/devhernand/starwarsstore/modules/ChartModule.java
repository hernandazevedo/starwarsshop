package br.com.devhernand.starwarsstore.modules;

import javax.inject.Singleton;

import br.com.devhernand.starwarsstore.chart.ChartInteractor;
import br.com.devhernand.starwarsstore.chart.ChartInteractorImpl;
import br.com.devhernand.starwarsstore.modules.services.ProductServiceModule;
import br.com.devhernand.starwarsstore.services.ProductService;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Nando on 02/06/2017.
 */

@Module(includes = {ProductServiceModule.class,})
public class ChartModule {

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public ChartInteractor providesChartInteractor(
            ProductService productService) {
        return new ChartInteractorImpl(productService);
    }
}
