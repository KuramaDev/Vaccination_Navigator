package com.example.vacinationnavigator.Ui.Main;

import android.view.View;
import android.widget.TextView;

import com.example.vacinationnavigator.R;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CenterViewHolder extends RecyclerView.ViewHolder {

    public TextView title ;
    public TextView description;
    public TextView city ;
    public TextView address ;

    public CenterViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.tv_title);
        description = (TextView) itemView.findViewById(R.id.tv_desc);
        city = (TextView) itemView.findViewById(R.id.tv_city);
        address = (TextView) itemView.findViewById(R.id.tv_address);

    }
}
