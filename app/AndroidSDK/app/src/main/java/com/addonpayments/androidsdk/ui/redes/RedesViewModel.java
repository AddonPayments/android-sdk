package com.addonpayments.androidsdk.ui.redes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RedesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RedesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Linkedin");
    }

    public LiveData<String> getText() {
        return mText;
    }
}