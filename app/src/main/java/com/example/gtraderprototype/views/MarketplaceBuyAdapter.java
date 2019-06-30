package com.example.gtraderprototype.views;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class MarketplaceBuyAdapter extends RecyclerView.Adapter<MarketplaceBuyAdapter.MarketplaceViewHolder> {
    private List<Item> itemList;

    public MarketplaceBuyAdapter(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    class MarketplaceViewHolder extends RecyclerView.ViewHolder {
        private TextView itemName;
        private TextView itemPrice;
        private TextView numberOwned;

        public MarketplaceViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            itemPrice = itemView.findViewById(R.id.price_value);
        }
    }

    public MarketplaceViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.marketplace_buy_item, parent, false);
        return new MarketplaceViewHolder(itemView);
    }

    public void onBindViewHolder(MarketplaceViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemName.setText(item.getName());
        holder.itemPrice.setText("" + item.getRegionPrice());
    }

    public int getItemCount() {
        if (itemList == null) return 0;
        return itemList.size();
    }

    public Item getItemAt(int position) {
        return itemList.get(position);
    }
}
