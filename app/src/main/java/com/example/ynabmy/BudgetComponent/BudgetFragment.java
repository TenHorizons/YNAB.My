package com.example.ynabmy.BudgetComponent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ynabmy.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BudgetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BudgetFragment extends Fragment {
    protected RecyclerView recyclerView;
    protected RecyclerView.Adapter adapter;
    protected BudgetList budgetList = new BudgetList();

    public BudgetFragment() {
        super(R.layout.fragment_budget);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = (TextView) view.findViewById(R.id.listview_item);
        }
        public TextView getTextView() {
            return textView;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createSampleList();
    }

    private void createSampleList() {
        for (int i=1;i<11;i++){
            budgetList.addBudgetItem("Item "+i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_budget, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.budget_recycler_view);
        adapter = new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listview_item,parent,false);
                return new ViewHolder(v);
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                ViewHolder vh = (ViewHolder) holder;
                vh.getTextView().setText(budgetList.getBudgetItem(position));
            }

            @Override
            public int getItemCount() {
                return budgetList.getItemCount();
            }
        };
        recyclerView.setAdapter(adapter);
        return rootView;
    }
}