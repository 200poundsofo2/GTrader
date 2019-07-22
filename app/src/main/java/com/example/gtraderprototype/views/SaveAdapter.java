package com.example.gtraderprototype.views;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gtraderprototype.R;
import com.example.gtraderprototype.model.Model;

import java.util.ArrayList;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.SavesViewHolder> {
    public ArrayList<String> savesList;
    private RecyclerViewClickListener saveListener;
    public SaveAdapter(ArrayList<String> savesList, RecyclerViewClickListener saveListener){
        this.savesList = savesList;
        this.saveListener = saveListener;
    }
    class SavesViewHolder extends RecyclerView.ViewHolder{
        private TextView savesName;
        private Button open;
        private Button delete;

        public SavesViewHolder(View itemView) {
            super(itemView);
            savesName = itemView.findViewById(R.id.player_name);
            open = itemView.findViewById(R.id.open_save);
            open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveListener.recyclerViewListClicked(v, getLayoutPosition());
                }
            });
            delete = itemView.findViewById(R.id.delete_save);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveListener.recyclerViewListClickedRemove(v, getLayoutPosition());
                }
            });
        }
    }


    public SavesViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.save_item, parent, false);
        return new SavesViewHolder(itemView);
    }

    public void onBindViewHolder(SavesViewHolder holder, int position) {
        String instanceID = savesList.get(position);
        holder.savesName.setText(instanceID);
    }
    public int getItemCount(){
        return savesList.size();
    }

    public String getGameIDAt(int position){
        return savesList.get(position);
    }
}

