package com.example.mobilalkfejl;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;
import java.util.List;

public class BeverageAdapter extends RecyclerView.Adapter<BeverageAdapter.ViewHolder> {

    private List<Beverage> beverageList;
    private Context context;
    private FirebaseUser usr;

    public BeverageAdapter(List<Beverage> beverageList, Context context, FirebaseUser usr) {
        this.beverageList = beverageList;
        this.context = context;
        this.usr = usr;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView amountTextView;
        TextView priceTextView;
        TextView categoryTextView;
        TextView descTextView;
        ImageView imageView;
        Button purchaseButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            amountTextView = itemView.findViewById(R.id.amountTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            descTextView = itemView.findViewById(R.id.descTextView);
            imageView = itemView.findViewById(R.id.imageView);
            purchaseButton = itemView.findViewById(R.id.buyButton);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_beverage, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Beverage beverage = beverageList.get(position);
        holder.nameTextView.setText(beverage.getName());
        holder.amountTextView.setText("cap.: " + beverage.getAmount());
        holder.priceTextView.setText("cost: " + beverage.getPrice());
        holder.categoryTextView.setText("type: " + beverage.getCategory());
        holder.descTextView.setText(beverage.getDesc());
        // Load image with Glide
        int imgId = context.getResources().getIdentifier(beverage.getImg(), "drawable", context.getPackageName());
        Glide.with(context)
                .load(imgId)
                .override(500, 500)
                .into(holder.imageView);
        holder.purchaseButton.setOnClickListener(v -> {
            usr = FirebaseAuth.getInstance().getCurrentUser();
            if (usr != null) {
                Intent intent = new Intent(context, DeliveryActivity.class);
                context.startActivity(intent);
                NotificationHandler.showPurchaseNotification(context);
            } else {
                showLoginSnackbar(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beverageList.size();
    }

    public void updateList(List<Beverage> currentList, String filter) {
        if (filter.equals("All")) {
            currentList.clear();
            currentList.addAll(BeverageDataSource.getBeverages());
        } else {
            List<Beverage>newList = new ArrayList<>();
            currentList.clear();
            currentList.addAll(BeverageDataSource.getBeverages());
            for (int i = 0; i < currentList.size(); i++) {
                if (currentList.get(i).getCategory().equals(filter)) {
                    newList.add(currentList.get(i));
                }
            }
            currentList.clear();
            currentList.addAll(newList);
        }
        notifyDataSetChanged();
    }

    private void showLoginSnackbar(View view) {
        CharSequence msg = "Login or register to be able to use this feature!";
        int length = 3000;
        Snackbar snackbar = Snackbar.make(view, msg, length);
        snackbar.setAction("Dismiss", v -> snackbar.dismiss());
        snackbar.show();
    }
}

