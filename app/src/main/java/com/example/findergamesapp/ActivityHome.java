package com.example.findergamesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.findergamesapp.Database.DatabaseHelper;
import com.example.findergamesapp.adapters.PageAdapter;
import com.example.findergamesapp.models.Users;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Stack;

public class ActivityHome extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private PageAdapter adapter;
    DatabaseHelper databaseHelper;
    FirebaseAuth auth;
    private Stack<Integer> fragmentBackStack = new Stack<>(); // Stack to store fragment positions


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        databaseHelper = new DatabaseHelper();
        auth = FirebaseAuth.getInstance();
        ImageView iv_profile = findViewById(R.id.iv_profile);


        databaseHelper.getUser(auth.getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               Users users = snapshot.getValue(Users.class);
                if (users != null && users.getImage() != null && !users.getImage().isEmpty()) {
                    Picasso.get()
                            .load(users.getImage())
                            .into(iv_profile);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        addTabs();

        adapter = new PageAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
                fragmentBackStack.push(position);
            }
        });

        iv_profile.setOnClickListener(view -> {
            Intent intent = new Intent(ActivityHome.this,ActivityProfile.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });


    }

    private void addTabs() {
        tabLayout.addTab(tabLayout.newTab().setText("Games"));
        tabLayout.addTab(tabLayout.newTab().setText("Quiz"));
        tabLayout.addTab(tabLayout.newTab().setText("Finder"));

        for (int i = 0; i < tabLayout.getTabCount() - 1; i++) {
            View tab = ((ViewGroup) tabLayout.getChildAt(0)).getChildAt(i);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) tab.getLayoutParams();
            marginLayoutParams.setMarginEnd(getResources().getDimensionPixelSize(R.dimen.stroke_width));
            tab.requestLayout();
        }
    }
    @Override
    public void onBackPressed() {
        if (!fragmentBackStack.isEmpty()) {
            fragmentBackStack.pop(); // Remove the current fragment position
            if (!fragmentBackStack.isEmpty()) {
                int previousFragmentPosition = fragmentBackStack.peek(); // Peek at the previous fragment position
                viewPager.setCurrentItem(previousFragmentPosition);
                TabLayout.Tab tab = tabLayout.getTabAt(previousFragmentPosition); // Update the selected tab
                if (tab != null) {
                    tab.select();
                }
            } else {
                fragmentBackStack.pop();
                super.onBackPressed();
            }
        } else {
            finishAffinity();
        }
    }

}
