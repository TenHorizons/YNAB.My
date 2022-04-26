package com.example.ynabmy.BudgetComponent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ynabmy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BudgetFragment extends Fragment {
    protected RecyclerView recyclerView;
    protected RecyclerView.Adapter adapter;
    protected BudgetList budgetList = new BudgetList();
    private Budget budget = new Budget();

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
        setupBudget();
    }

    private void createSampleList() {
        for (int i = 1; i < 11; i++) {
            budgetList.addBudgetItem("Item " + i);
        }
    }

    private void setupBudget(){
        setupBudgetCategoriesAndItems();
    }

    private void setupBudgetCategoriesAndItems(){
        int numberOfCategories = getResources().getStringArray(R.array.Default_Budget_Category_Names).length;
        for(int i=0; i<numberOfCategories; i++){
            String categoryName = getResources().getStringArray(R.array.Default_Budget_Category_Names)[i];
            BudgetCategory bc = new BudgetCategory(categoryName, 0, 0);

            //get resources string.xml string array name using Category Name.
            BudgetCategoriesAndItems items = new BudgetCategoriesAndItems();
            int stringArrayName = items.getStringArrayNameUsingCategoryName(categoryName);

            int numberOfItems = getResources().getStringArray(stringArrayName).length;
            for(int j=0; j<numberOfItems; j++){
                String itemName = getResources().getStringArray(stringArrayName)[j];
                BudgetItem bi = new BudgetItem(itemName,0,0);
                bc.addBudgetItem(bi);
            }

            budget.addBudgetCategory(bc);
        }
    }

    private getStringArrayNameUsingCategoryName

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
                        .inflate(R.layout.listview_item, parent, false);
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