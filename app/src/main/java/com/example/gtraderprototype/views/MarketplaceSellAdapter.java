package com.example.gtraderprototype.views;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class MarketplaceSellAdapter extends RecyclerView.Adapter<MarketplaceSellAdapter.MarketplaceViewHolder> {
    public List<Item> itemList;
    private RecyclerViewClickListener itemListener;
    public MarketplaceSellAdapter (ArrayList<Item> itemList,RecyclerViewClickListener itemListener){
        this.itemList = itemList;
        this.itemListener = itemListener;
    }
    class MarketplaceViewHolder extends RecyclerView.ViewHolder{
        private TextView itemName;
        private TextView itemPrice;
        private Button sell;
        private TextView numberOwned;

        public MarketplaceViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.price_value);
            sell = itemView.findViewById(R.id.sell);
            sell.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemListener.recyclerViewListClicked(v, getLayoutPosition());
                }
            });
        }
    }
    public MarketplaceViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.marketplace_sell_item, parent, false);
        return new MarketplaceViewHolder(itemView);
    }

    public void onBindViewHolder(MarketplaceViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemName.setText(item.getName());
        holder.itemPrice.setText(new StringBuilder().append(item.getRegionPrice()).toString());
    }

    public int getItemCount() {
        if (itemList == null) return 0;
        return itemList.size();
    }

    public Item getItemAt(int position) {
        return itemList.get(position);
    }
}

