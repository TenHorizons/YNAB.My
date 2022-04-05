package com.example.ynabmy.BudgetComponent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ynabmy.R;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.ViewHolder>{
    private BudgetList budgetList;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.listview_item);
        }
        public TextView getTextView(){
            return textView;
        }
    }

    public BudgetAdapter(BudgetList budgetList){
        this.budgetList = budgetList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(budgetList.getBudgetItem(position));
    }

    @Override
    public int getItemCount() {
        return budgetList.getItemCount();
    }
}
