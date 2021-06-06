package com.example.vacinationnavigator.Ui.Main;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vacinationnavigator.Model.Center;
import com.example.vacinationnavigator.R;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CentersAdapter extends RecyclerView.Adapter<CenterViewHolder>  {

    private List<Center> centers ;
    private onItemClickedListener itemListener;

    public CentersAdapter (List<Center> centers,onItemClickedListener listener){
        this.centers = centers;
        this.itemListener = listener;
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
        CardView cv = holder.rv;
        tittle.setText(center.getTitle());
        description.setText(center.getDescription());
        address.setText(center.getAddress());
        city.setText(center.getCity());
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Log.d("Item Clicked","From Adapter");
                    itemListener.onItemClicked(center);

            }
        });
    }

    @Override
    public int getItemCount() {

        return centers.size();

    }



    public interface onItemClickedListener{
        void onItemClicked(Center center);
    }
}
