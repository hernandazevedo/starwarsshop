package br.com.devhernand.starwarsstore.deps;

import javax.inject.Singleton;

import br.com.devhernand.starwarsstore.chart.ChartActivity;
import br.com.devhernand.starwarsstore.modules.ChartModule;
import br.com.devhernand.starwarsstore.modules.MainModule;
import br.com.devhernand.starwarsstore.modules.PaymentModule;
import br.com.devhernand.starwarsstore.main.MainActivity;
import br.com.devhernand.starwarsstore.payment.PaymentActivity;
import dagger.Component;
import dagger.Module;

/**
 * Created by Nando on 31/05/2017.
 */

@Singleton
@Component(modules = {MainModule.class, ChartModule.class, PaymentModule.class,})
@Module
public interface Deps {
    void inject(MainActivity mainActivity);
    void inject(ChartActivity chartActivity);
    void inject(PaymentActivity paymentActivity);
}
