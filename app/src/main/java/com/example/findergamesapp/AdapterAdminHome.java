package com.example.findergamesapp;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findergamesapp.Database.DatabaseHelper;
import com.example.findergamesapp.models.QuizModel;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class AdapterAdminHome extends RecyclerView.Adapter<AdapterAdminHome.ViewHolder> {
    List<QuizModel>quizModelsList;
    Context context;
    DatabaseHelper databaseHelper;

    public AdapterAdminHome(List<QuizModel> quizModelsList, Context context) {
        this.quizModelsList = quizModelsList;
        this.context = context;
        databaseHelper = new DatabaseHelper();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_view_admin_home,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        QuizModel quizModel = quizModelsList.get(position);
        holder.detail.setText(quizModel.getQuestion());
        holder.anas.setText(quizModel.getAnswer());

        holder.menu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(holder.menu.getContext(), holder.menu);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                popupMenu.setForceShowIcon(true);
            }
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.menu_edit:
                            context.startActivity(new Intent(context,EditQuestion.class).putExtra("data",quizModel).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                            break;
                        case R.id.menu_delete:
                            showFailedDialogBox(position,quizModel);
                            break;

                    }
                    return false;
                }
            });
            popupMenu.show();
        });

    }

    @Override
    public int getItemCount() {
        return quizModelsList.size();
    }
    private void showFailedDialogBox(int position, QuizModel quizModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.row_view_delete, null);

        Button btnDelete = dialogView.findViewById(R.id.btnDelete);
        Button btnCancel = dialogView.findViewById(R.id.btnCancel);

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        btnDelete.setOnClickListener(v -> {
            quizModelsList.remove(position);
            databaseHelper.deleteQuestion(quizModel.getQuestionId()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(context, "deleted question successfully", Toast.LENGTH_SHORT).show();
                }
            });
            notifyItemRemoved(position);
            alertDialog.dismiss();
        });

        btnCancel.setOnClickListener(v -> alertDialog.dismiss());

        alertDialog.show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView detail,anas;
        ImageView menu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            detail = itemView.findViewById(R.id.detail);
            anas = itemView.findViewById(R.id.answer);
            menu = itemView.findViewById(R.id.menu);
        }
    }
}



