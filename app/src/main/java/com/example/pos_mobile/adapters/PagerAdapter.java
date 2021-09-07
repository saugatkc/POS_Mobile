package com.example.pos_mobile.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pos_mobile.fragments.AllFragment;
import com.example.pos_mobile.fragments.CategoryFragment;
import com.example.pos_mobile.fragments.FavouritesFragment;
import com.example.pos_mobile.fragments.ModifiersFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStateAdapter {
    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


//    public PagerAdapter(InventoryManagement inventoryManagement) {
//        super(inventoryManagement);
//    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new AllFragment();
            case 1:
                return new CategoryFragment();
            case 2:
                return new FavouritesFragment();
            default:
                return new ModifiersFragment();
        }
       // return new AllFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
