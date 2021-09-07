package com.example.pos_mobile.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pos_mobile.activitys.InventoryManagementActivity;
import com.example.pos_mobile.activitys.LoginActivity;
import com.example.pos_mobile.R;


public class MoreFragment extends Fragment {
    Button signOf,itemsAndSubItems;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        signOf = view.findViewById(R.id.logout);
        itemsAndSubItems = view.findViewById(R.id.btnItemsAndSubItems);
        signOf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        itemsAndSubItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getContext(), InventoryManagementActivity.class));
            }
        });

        return view;
    }

    private void logout() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove("email");
        editor.remove("password");
        editor.commit();
        startActivity(new Intent(getContext(), LoginActivity.class));
    }
}