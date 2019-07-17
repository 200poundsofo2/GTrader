package com.example.gtraderprototype.views;

import android.view.View;

public interface RecyclerViewClickListener {
    public void recyclerViewListClicked(View v, int position);
    public void recyclerViewListClickedRemove(View v, int position);
}
