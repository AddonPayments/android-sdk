package com.addonpayments.androidsdk.ui.redes;

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

public class RedesFragment extends Fragment {

    private RedesViewModel githubViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        githubViewModel =
                ViewModelProviders.of(this).get(RedesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_redes, container, false);
        final TextView textView = root.findViewById(R.id.text_redes);
        githubViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;

    }

}