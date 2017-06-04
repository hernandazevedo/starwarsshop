package br.com.devhernand.starwarsstore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.devhernand.starwarsstore.R;
import br.com.devhernand.starwarsstore.model.json.Transact;

/**
 * Created by Nando on 31/05/2017.
 */

public class TransactRecyclerAdapter extends RecyclerView.Adapter<TransactRecyclerAdapter.ViewHolder> {

    private List<Transact> list;

    private Context ctx;
    public TransactRecyclerAdapter(Context ctx, List<Transact> list) {
        this.ctx = ctx;
        this.list = list;
    }


    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        final Transact item = list.get(position);

        holder.tvDateTime.setText(item.getDateTime());
        String price = ctx.getString(R.string.total_message,item.getValue().toString()) ;

        holder.tvValue.setText(price);
        holder.tvCardNumber.setText(item.getCardNumber());
        holder.tvName.setText(item.getCardHolderName());

        holder.itemView.setTag(item);

    }

    @Override public int getItemCount() {
        return list.size();
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvDateTime,tvCardNumber,tvName,tvValue;



        public ViewHolder(View itemView) {
            super(itemView);

            tvDateTime = (TextView) itemView.findViewById(R.id.tvDateTime);
            tvCardNumber = (TextView) itemView.findViewById(R.id.tvCardNumber);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvValue = (TextView) itemView.findViewById(R.id.tvValue);

        }


    }

}
