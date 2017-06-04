package br.com.devhernand.starwarsstore.deps;

import javax.inject.Singleton;

import br.com.devhernand.starwarsstore.app.chart.ChartActivity;
import br.com.devhernand.starwarsstore.modules.ChartModule;
import br.com.devhernand.starwarsstore.modules.MainModule;
import br.com.devhernand.starwarsstore.modules.PaymentModule;
import br.com.devhernand.starwarsstore.app.main.MainActivity;
import br.com.devhernand.starwarsstore.modules.TransactionModule;
import br.com.devhernand.starwarsstore.app.payment.PaymentActivity;
import br.com.devhernand.starwarsstore.app.transaction.TransactionActivity;
import dagger.Component;
import dagger.Module;

/**
 * Created by Nando on 31/05/2017.
 */

@Singleton
@Component(modules = {MainModule.class, ChartModule.class, PaymentModule.class, TransactionModule.class})
@Module
public interface Deps {
    void inject(MainActivity mainActivity);
    void inject(ChartActivity chartActivity);
    void inject(PaymentActivity paymentActivity);
    void inject(TransactionActivity transactionActivity);

}
