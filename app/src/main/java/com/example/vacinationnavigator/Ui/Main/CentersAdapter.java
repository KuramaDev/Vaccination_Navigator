package com.example.vacinationnavigator.Ui.Main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vacinationnavigator.Model.Center;
import com.example.vacinationnavigator.R;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CentersAdapter extends RecyclerView.Adapter<CenterViewHolder> {

    private List<Center> centers ;

    public CentersAdapter (List<Center> centers){
        this.centers = centers;
    }

    @NonNull
    @NotNull
    @Override
    public CenterViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.card_centers_row,parent,false);
         CenterViewHolder viewHolder = new CenterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CenterViewHolder holder, int position) {
        Center center = centers.get(position);

        TextView tittle = holder.title;
        TextView description = holder.description;
        TextView address = holder.address;
        TextView city = holder.city;

        tittle.setText(center.getTitle());
        description.setText(center.getDescription());
        address.setText(center.getAddress());
        city.setText(center.getCity());
    }

    @Override
    public int getItemCount() {

        return centers.size();

    }
}
