package br.com.devhernand.starwarsstore.transaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import br.com.devhernand.starwarsstore.R;
import br.com.devhernand.starwarsstore.adapter.TransactRecyclerAdapter;
import br.com.devhernand.starwarsstore.base.BaseActivity;
import br.com.devhernand.starwarsstore.model.json.Transact;


public class TransactionActivity extends BaseActivity implements TransactionView {

    @Inject
    public TransactionInteractor transactionInteractor;

    private TransactionPresenter presenter;

    private RecyclerView listTransactions;

    public static void navigate(Activity activity){
        Intent intent = new Intent(activity, TransactionActivity.class);
        ActivityCompat.startActivity(activity,intent, ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDeps().inject(this);
        renderView();
        init();

        presenter = new TransactionPresenterImpl(transactionInteractor, this);
        presenter.showTransactions();
    }

    public void init(){
        listTransactions.setLayoutManager(new LinearLayoutManager(this));
        initToolbar(getString(R.string.transactions_history));

    }

    public void renderView() {
        setContentView(R.layout.activity_transaction);
        super.initCommonComponents();
        listTransactions = (RecyclerView) findViewById(R.id.listTransactions);
    }


    @Override
    public void onShowTransactionsSuccess(List<Transact> transactList) {
        TransactRecyclerAdapter adapter = new TransactRecyclerAdapter(getApplicationContext(), transactList);
        listTransactions.setAdapter(adapter);
    }

    @Override
    public void onTransactionsListEmpty() {
        showToast(getString(R.string.transactions_empty_message));
    }
}
