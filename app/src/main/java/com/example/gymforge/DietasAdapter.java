package com.example.gymforge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DietasAdapter extends RecyclerView.Adapter<DietasAdapter.DietViewHolder> {

    private List<Dieta> dietas = new ArrayList<>();
public void setDietas(List<Dieta> dietas) {
        this.dietas = dietas;
        notifyDataSetChanged();
        }

@NonNull
@Override
public DietViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dieta, parent, false);
        return new DietViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull DietViewHolder holder, int position) {
        Dieta dieta = dietas.get(position);
        holder.tipoComidaTextView.setText(dieta.getTipoComida());
        holder.nombreTextView.setText(dieta.getNombre());
        holder.descripcionTextView.setText(dieta.getDescripcion());
        }

@Override
public int getItemCount() {
        return dietas.size();
        }

public static class DietViewHolder extends RecyclerView.ViewHolder {
    TextView tipoComidaTextView, nombreTextView, descripcionTextView;

    public DietViewHolder(@NonNull View itemView) {
        super(itemView);
        tipoComidaTextView = itemView.findViewById(R.id.tipoComidaTextView);
        nombreTextView = itemView.findViewById(R.id.nombreTextView);
        descripcionTextView = itemView.findViewById(R.id.descripcionTextView);
    }
}
}
