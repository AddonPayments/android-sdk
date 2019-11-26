package com.addonpayments.androidsdk.ui.hpp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HppViewModel extends ViewModel  {

    private MutableLiveData<String> mText;

    public HppViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}