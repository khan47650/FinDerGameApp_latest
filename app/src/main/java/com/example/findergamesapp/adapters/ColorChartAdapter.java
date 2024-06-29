package com.example.findergamesapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findergamesapp.R;
import com.example.findergamesapp.models.ColorModel;
import com.google.firestore.v1.Precondition;

import org.w3c.dom.Text;

import java.util.List;

public class ColorChartAdapter extends RecyclerView.Adapter<ColorChartAdapter.ViewHolder> {
    List<ColorModel> list;
    Context context;

    public ColorChartAdapter(List<ColorModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_color, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ColorModel model = list.get(position);
        holder.tvColorName.setText(model.getColorName());
        if (model.getColorCode() != 0) {
            int colorResId = model.getColorCode();
            holder.rr_backgroundColor.setBackgroundTintList(ContextCompat.getColorStateList(context, colorResId));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvColorName;
        RelativeLayout rr_backgroundColor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvColorName = itemView.findViewById(R.id.tvColorName);
            rr_backgroundColor = itemView.findViewById(R.id.rr_backgroundColor);

        }
    }
}
