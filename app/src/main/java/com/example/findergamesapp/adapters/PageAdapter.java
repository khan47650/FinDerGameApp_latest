package com.example.findergamesapp.adapters;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.findergamesapp.FinderFragments;
import com.example.findergamesapp.GamesFragment;
import com.example.findergamesapp.QuizFragment;


public class PageAdapter extends FragmentStateAdapter {

    public PageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0: return new GamesFragment();
            case 1: return new QuizFragment();
            case 2: return new FinderFragments();

        }

        return new GamesFragment();

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
