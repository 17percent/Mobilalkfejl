package com.example.mobilalkfejl;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BeverageAdapter adapter;
    private List<Beverage> beverageList;
    private FirebaseUser current_user;
    private Animation slideIn;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        beverageList = BeverageDataSource.getBeverages();

        adapter = new BeverageAdapter(beverageList, this, current_user);

        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        current_user = FirebaseAuth.getInstance().getCurrentUser();

        slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in);
        findViewById(R.id.main).startAnimation(slideIn);

        backButton = findViewById(R.id.back_to_login);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item_1) {
            adapter.updateList(beverageList, "All");
            return true;
        } else if (id == R.id.menu_item_2) {
            adapter.updateList(beverageList, "Soft");
            return true;
        } else if (id == R.id.menu_item_3) {
            adapter.updateList(beverageList, "Alcohol");
            return true;
        } else if (id == R.id.menu_item_4) {
            adapter.updateList(beverageList, "Juice");
            return true;
        } else if (id == R.id.menu_item_5) {
            adapter.updateList(beverageList, "Tea");
            return true;
        } else if (id == R.id.menu_item_6) {
            adapter.updateList(beverageList, "Coffee");
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Slide-in animation called");
        findViewById(R.id.main).startAnimation(slideIn);
    }

}