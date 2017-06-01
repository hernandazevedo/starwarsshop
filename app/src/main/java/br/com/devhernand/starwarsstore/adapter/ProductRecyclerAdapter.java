package br.com.devhernand.starwarsstore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.devhernand.starwarsstore.R;
import br.com.devhernand.starwarsstore.model.Product;

/**
 * Created by Nando on 31/05/2017.
 */

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> implements View.OnClickListener {

    private List<Product> productList;
    private OnItemClickListener onItemClickListener;
    private Context ctx;
    public ProductRecyclerAdapter(Context ctx, List<Product> productList,OnItemClickListener onItemClickListener) {
        this.ctx = ctx;
        this.productList = productList;
        this.onItemClickListener = onItemClickListener;
    }

    public ProductRecyclerAdapter(List<Product> productList, Context ctx) {
        this.productList = productList;
        this.ctx = ctx;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Product item = productList.get(position);

        holder.productname.setText(item.getTitle());
        String price = ctx.getString(R.string.currency) + item.getPrice();

        holder.productprice.setText(price);
        holder.productseller.setText(item.getSeller());
        holder.image.setImageBitmap(null);
        Picasso.with(holder.image.getContext()).load(item.getThumbnailHd()).into(holder.image);
        holder.itemView.setTag(item);

    }

    @Override public int getItemCount() {
        return productList.size();
    }

    @Override public void onClick(final View v) {
        onItemClickListener.onItemClick(v, (Product) v.getTag());
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView productname;
        public TextView productprice;
        public TextView productseller;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            productname = (TextView) itemView.findViewById(R.id.productname);
            productprice = (TextView) itemView.findViewById(R.id.productprice);
            productseller = (TextView) itemView.findViewById(R.id.productseller);

        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, Product viewModel);

    }
}
