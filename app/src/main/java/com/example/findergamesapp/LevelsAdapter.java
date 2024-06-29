package com.example.findergamesapp;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findergamesapp.interfaces.OnPassColorLevelNumber;

import java.util.List;

public class LevelsAdapter extends RecyclerView.Adapter<LevelsAdapter.ViewHolder> {
    Context context;
    OnPassColorLevelNumber onColorPass;
    int unlockedLevel;

    public LevelsAdapter(Context context, OnPassColorLevelNumber onColorPass,int unlockedLevel) {
        this.context = context;
        this.onColorPass = onColorPass;
        this.unlockedLevel=unlockedLevel;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_color_level, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int number = position + 1;
        holder.tvLevelsNumber.setText("" + number);
        if (number <= unlockedLevel) {
            holder.itemView.setClickable(true);
            holder.color2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.blue)));
            holder.itemView.setOnClickListener(v -> onColorPass.passColor(number));
            holder.imvLock.setImageResource(R.drawable.icon_unlock);
        } else {
            holder.itemView.setClickable(false);
            holder.color2.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.Seal)));
            holder.imvLock.setImageResource(R.drawable.baseline_lock_24);
        }

    }

    @Override
    public int getItemCount() {
        return 40;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLevelsNumber;
        ImageView imvLock;
        RelativeLayout color2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvLock = itemView.findViewById(R.id.imvLock);
            tvLevelsNumber = itemView.findViewById(R.id.tvLevelsNumber);
            color2=itemView.findViewById(R.id.Color2);
        }
    }
}
