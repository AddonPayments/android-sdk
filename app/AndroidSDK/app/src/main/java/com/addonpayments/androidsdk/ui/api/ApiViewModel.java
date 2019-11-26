package com.addonpayments.androidsdk.ui.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ApiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ApiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}