package com.example.gtraderprototype.views;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.entity.Item;

import java.util.List;

/**
 * the adapter for sellable item from a player
 */
public class MarketplaceSellAdapter
        extends RecyclerView.Adapter<MarketplaceSellAdapter.MarketplaceViewHolder> {
    private final List<Item> itemList;
    private final RecyclerViewClickListener itemListener;

    /**
     * constructor for the the sell adapter
     * @param itemList list of items that the player can sell
     * @param itemListener a listener for selling item via a button
     */
    public MarketplaceSellAdapter (List<Item> itemList, RecyclerViewClickListener itemListener){
        this.itemList = itemList;
        this.itemListener = itemListener;
    }
    class MarketplaceViewHolder extends RecyclerView.ViewHolder{
        private final TextView itemName;
        private final TextView itemPrice;
        private final Button sell;
        private TextView numberOwned;

        MarketplaceViewHolder(View itemView) {
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
    @Override
    public MarketplaceViewHolder onCreateViewHolder( ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.marketplace_sell_item, parent, false);
        return new MarketplaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder( MarketplaceViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemName.setText(item.getName());
        holder.itemPrice.setText(String.valueOf(item.getRegionPrice()));
    }

    @Override
    public int getItemCount() {
        if (itemList == null) {
            return 0;
        }
        return itemList.size();
    }

    /**
     * gets an item from the item list
     * @param position the index of the item in the item list
     * @return a sellable item
     */
    public Item getItemAt(int position) {
        return itemList.get(position);
    }
}

