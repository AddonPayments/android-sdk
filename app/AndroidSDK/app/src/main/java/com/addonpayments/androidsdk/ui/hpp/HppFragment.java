package com.addonpayments.androidsdk.ui.hpp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.addonpayments.androidsdk.R;

public class HppFragment extends Fragment {

    private HppViewModel hppViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hppViewModel =
                ViewModelProviders.of(this).get(HppViewModel.class);
        View root = inflater.inflate(R.layout.fragment_hpp, container, false);
        final TextView textView = root.findViewById(R.id.text_hpp);
        hppViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}