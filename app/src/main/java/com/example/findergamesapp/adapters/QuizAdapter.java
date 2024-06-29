package com.example.findergamesapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findergamesapp.R;
import com.example.findergamesapp.interfaces.OnRemoveLastQuestion;
import com.example.findergamesapp.models.Options;
import com.example.findergamesapp.models.QuizModel;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {
    Context context;
    List<QuizModel> list;
    boolean isAnswer =false,isCheckAns=false,isAgain=false,isLastIndex=false;
    String storeAnswer1,storeAnswer2;
    OnRemoveLastQuestion onRemoveLastQuestion;

    public QuizAdapter(Context context, List<QuizModel> list, OnRemoveLastQuestion onRemoveLastQuestion) {
        this.context = context;
        this.list = list;
        this.onRemoveLastQuestion=onRemoveLastQuestion;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_quiz, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuizModel quizModel = list.get(position);
        Options options = quizModel.getOptions();
        holder.tvQuestion.setText(quizModel.getQuestion());
        holder.tvOptions1.setText("a: "+options.getOptions1());
        holder.tvOptions2.setText("b: "+options.getOptions2());
        holder.tvOptions3.setText("c: "+options.getOptions3());
        holder.tvOptions4.setText("d: "+options.getOptions4());
        holder.tvDetails.setText(quizModel.getResultDetails());

        if(isLastIndex){
            holder.btnSubmitOrNext.setVisibility(View.VISIBLE);

        }else{
            holder.btnSubmitOrNext.setVisibility(View.GONE);

        }
        if (position==0){
            if(isCheckAns){
                holder.ll_top2.setVisibility(View.VISIBLE);
                if(storeAnswer1==options.getOptions1()){
                    holder.tvOptions1.setBackground(context.getDrawable(R.drawable.bg_fail));
                }else if(storeAnswer1==options.getOptions2()){
                    holder.tvOptions2.setBackground(context.getDrawable(R.drawable.bg_fail));
                }else if(storeAnswer1==options.getOptions3()){
                    holder.tvOptions3.setBackground(context.getDrawable(R.drawable.bg_fail));
                }else if(storeAnswer1==options.getOptions4()){
                    holder.tvOptions4.setBackground(context.getDrawable(R.drawable.bg_fail));
                }
            }
            if(isAnswer) {
                holder.ll_top2.setVisibility(View.VISIBLE);
                if (quizModel.getAnswer().equals(options.getOptions1())) {
                    holder.tvOptions1.setBackground(context.getDrawable(R.drawable.bg_pass));
                } else if (quizModel.getAnswer().equals(options.getOptions2())) {
                    holder.tvOptions2.setBackground(context.getDrawable(R.drawable.bg_pass));
                } else if (quizModel.getAnswer().equals(options.getOptions3())) {
                    holder.tvOptions3.setBackground(context.getDrawable(R.drawable.bg_pass));
                } else {
                    holder.tvOptions4.setBackground(context.getDrawable(R.drawable.bg_pass));
                }
            }
            if(isAgain){
                holder.ll_top2.setVisibility(View.GONE);
                holder.tvOptions1.setBackgroundColor(Color.WHITE);
                holder.tvOptions2.setBackgroundColor(Color.WHITE);
                holder.tvOptions3.setBackgroundColor(Color.WHITE);
                holder.tvOptions4.setBackgroundColor(Color.WHITE);
            }
        }
        if(position==1){
            holder.btnSubmitOrNext.setVisibility(View.VISIBLE);
            if(isCheckAns){
                holder.ll_top2.setVisibility(View.VISIBLE);
                    if(storeAnswer2==options.getOptions1()){
                        holder.tvOptions1.setBackground(context.getDrawable(R.drawable.bg_fail));
                    }else if(storeAnswer2==options.getOptions2()){
                        holder.tvOptions2.setBackground(context.getDrawable(R.drawable.bg_fail));
                    }else if(storeAnswer2==options.getOptions3()){
                        holder.tvOptions3.setBackground(context.getDrawable(R.drawable.bg_fail));
                    }else if(storeAnswer2==options.getOptions4()){
                        holder.tvOptions4.setBackground(context.getDrawable(R.drawable.bg_fail));
                    }
            }
            if(isAnswer) {
                holder.ll_top2.setVisibility(View.VISIBLE);
                if (quizModel.getAnswer().equals(options.getOptions1())) {
                    holder.tvOptions1.setBackground(context.getDrawable(R.drawable.bg_pass));
                } else if (quizModel.getAnswer().equals(options.getOptions2())) {
                    holder.tvOptions2.setBackground(context.getDrawable(R.drawable.bg_pass));
                } else if (quizModel.getAnswer().equals(options.getOptions3())) {
                    holder.tvOptions3.setBackground(context.getDrawable(R.drawable.bg_pass));
                } else if (quizModel.getAnswer().equals(options.getOptions4())) {
                    holder.tvOptions4.setBackground(context.getDrawable(R.drawable.bg_pass));
                }
            }
            if(isAgain){
                holder.ll_top2.setVisibility(View.GONE);
                holder.tvOptions1.setBackgroundColor(Color.WHITE);
                holder.tvOptions2.setBackgroundColor(Color.WHITE);
                holder.tvOptions3.setBackgroundColor(Color.WHITE);
                holder.tvOptions4.setBackgroundColor(Color.WHITE);
            }
        }

        holder.tvOptions1.setOnClickListener(v -> {
            holder.tvOptions1.setBackground(context.getDrawable(R.drawable.bg_answer));
            holder.tvOptions2.setBackgroundColor(Color.WHITE);
            holder.tvOptions3.setBackgroundColor(Color.WHITE);
            holder.tvOptions4.setBackgroundColor(Color.WHITE);
            if(position==0){
                storeAnswer1= options.getOptions1();
            }else{
                storeAnswer2= options.getOptions1();
            }
        });
        holder.tvOptions2.setOnClickListener(v -> {
            holder.tvOptions2.setBackground(context.getDrawable(R.drawable.bg_answer));
            holder.tvOptions1.setBackgroundColor(Color.WHITE);
            holder.tvOptions3.setBackgroundColor(Color.WHITE);
            holder.tvOptions4.setBackgroundColor(Color.WHITE);
            if(position==0){
                storeAnswer1= options.getOptions2();
            }else{
                storeAnswer2= options.getOptions2();
            }
        });
        holder.tvOptions3.setOnClickListener(v -> {
            holder.tvOptions3.setBackground(context.getDrawable(R.drawable.bg_answer));
            holder.tvOptions1.setBackgroundColor(Color.WHITE);
            holder.tvOptions2.setBackgroundColor(Color.WHITE);
            holder.tvOptions4.setBackgroundColor(Color.WHITE);

            if(position==0){
                storeAnswer1= options.getOptions3();
            }else{
                storeAnswer2= options.getOptions3();
            }
        });
        holder.tvOptions4.setOnClickListener(v -> {
            holder.tvOptions4.setBackground(context.getDrawable(R.drawable.bg_answer));
            holder.tvOptions1.setBackgroundColor(Color.WHITE);
            holder.tvOptions2.setBackgroundColor(Color.WHITE);
            holder.tvOptions3.setBackgroundColor(Color.WHITE);

            if(position==0){
                storeAnswer1= options.getOptions4();
            }else{
                storeAnswer2= options.getOptions4();
            }
        });

        holder.btnSubmitOrNext.setOnClickListener(v->{
            if (storeAnswer1 == null || storeAnswer1.isEmpty()) {
                Toast.makeText(context, "please selected an option", Toast.LENGTH_SHORT).show();
                return;
            } else if (storeAnswer2 == null || storeAnswer2.isEmpty()) {
                if(isLastIndex){
                    holder.btnSubmitOrNext.setText("Completed.");
                    isAgain = false;
                    isAnswer = true;
                    isCheckAns = true;
                    notifyDataSetChanged();

                }else{
                    Toast.makeText(context, "please selected an option", Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if(isLastIndex){
                holder.btnSubmitOrNext.setText("Completed.");
                isAgain = false;
                isAnswer = true;
                isCheckAns = true;
                notifyDataSetChanged();
                return;
            }
            else if(holder.btnSubmitOrNext.getText().toString()=="Next Quiz"){
                isAnswer=false;
                isCheckAns=false;
                isAgain=true;
                storeAnswer1="";
                storeAnswer2="";
                holder.btnSubmitOrNext.setText("Submit");
                holder.ll_top2.setVisibility(View.GONE);
                onRemoveLastQuestion.onRemoveLastQuestion();

            }else {
                isAgain = false;
                isAnswer = true;
                isCheckAns = true;
                holder.btnSubmitOrNext.setText("Next Quiz");
                holder.ll_top2.setVisibility(View.VISIBLE);
            }
            notifyDataSetChanged();
        });

    }

    @Override
    public int getItemCount() {
        if (list.size()==0){
            return 0;
        }
        if(list.size()-1==0){
            isLastIndex=true;
            return 1;
        }else{
            return 2;
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuestion, tvOptions1, tvOptions2, tvOptions3, tvOptions4,tvDetails;
        Button btnSubmitOrNext;
        LinearLayout ll_top2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.question);
            tvOptions1 = itemView.findViewById(R.id.option1);
            tvOptions2 = itemView.findViewById(R.id.option2);
            tvOptions3 = itemView.findViewById(R.id.option3);
            tvOptions4 = itemView.findViewById(R.id.option4);
            btnSubmitOrNext = itemView.findViewById(R.id.btnSubmitOrNext);
            tvDetails = itemView.findViewById(R.id.tvDetails);
            ll_top2 = itemView.findViewById(R.id.ll_top2);
        }
    }
}