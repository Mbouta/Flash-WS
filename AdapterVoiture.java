package com.example.lebeaubafouidizo.rapidews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by lebeau BAFOUIDIZO on 11/02/2018.
 */

public class AdapterVoiture extends RecyclerView.Adapter<AdapterVoiture.ViewHolder> {
    Context context;
    List<Voiture> voitureList;

    public AdapterVoiture(Context context, List<Voiture> voitureList) {
        this.context = context;
        this.voitureList = voitureList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_name1);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_voiture,parent ,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Voiture voiture = voitureList.get(position);
        holder.textView.setText(voiture.getSerie()+"-"+voiture.getMarque()+"-"+voiture.getKm());

        Log.e("adap",voiture.getSerie()+"-"+voiture.getMarque()+"-"+voiture.getKm());
    }


    @Override
    public int getItemCount() {
        Log.e("count",voitureList.size()+"");
        return voitureList.size();

    }

}
