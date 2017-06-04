package br.com.devhernand.starwarsstore.adapter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.devhernand.starwarsstore.R;
import br.com.devhernand.starwarsstore.model.json.Product;

/**
 * Created by Nando on 31/05/2017.
 */

public class ProductRecyclerAdapter extends RecyclerView.Adapter<ProductRecyclerAdapter.ViewHolder> implements View.OnClickListener {

    private int itemLayout = R.layout.item_product;

    private List<Product> productList;
    private OnItemClickListener onItemClickListener;
    private OnButtonClickListener onButtonClickListener;
    private Context ctx;
    public ProductRecyclerAdapter(Context ctx, List<Product> productList,OnItemClickListener onItemClickListener) {
        this.ctx = ctx;
        this.productList = productList;
        this.onItemClickListener = onItemClickListener;
    }

    public ProductRecyclerAdapter(Context ctx, List<Product> productList) {
        this.productList = productList;
        this.ctx = ctx;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        v.setOnClickListener(this);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        final Product item = productList.get(position);

        holder.productname.setText(item.getTitle());
        String price = ctx.getString(R.string.currency) + item.getPrice();

        holder.fabItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onButtonClickListener != null) {
                    onButtonClickListener.onButtonClick(view,item);
                }
            }
        });

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
        if(onItemClickListener != null)
            onItemClickListener.onItemClick(v, (Product) v.getTag());
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView productname;
        public TextView productprice;
        public TextView productseller;
        public FloatingActionButton fabItemLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            productname = (TextView) itemView.findViewById(R.id.productname);
            productprice = (TextView) itemView.findViewById(R.id.productprice);
            productseller = (TextView) itemView.findViewById(R.id.productseller);
            fabItemLayout = (FloatingActionButton) itemView.findViewById(R.id.fabItemLayout);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, Product viewModel);

    }

    public interface OnButtonClickListener {

        void onButtonClick(View view, Product viewModel);

    }

    public void setItemLayout(int itemLayout) {
        this.itemLayout = itemLayout;
    }
}
